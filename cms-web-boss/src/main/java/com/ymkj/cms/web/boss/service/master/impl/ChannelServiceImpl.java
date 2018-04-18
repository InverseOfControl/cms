package com.ymkj.cms.web.boss.service.master.impl;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.web.boss.facade.apply.ChannelFacade;
import com.ymkj.cms.web.boss.service.master.IChannelService;

@Service
public class ChannelServiceImpl implements IChannelService {

	private static String IMPORT_SUCCESS = "成功";
	private static String IMPORT_FAIL = "失败";
	@Autowired
	private ChannelFacade channelFacade;

	@Override
	public ResBMSChannelVO findById(Long id) {
		return channelFacade.findById(id);
	}

	@Override
	public PageResult<ResBMSChannelVO> listPage(ReqBMSChannelVO reqDemoVO) {
		PageResult<ResBMSChannelVO> pageResult = channelFacade.listPage(reqDemoVO);
		return pageResult;
	}

	@Override
	public boolean addChannel(ReqBMSChannelVO reqDemoVO) {
		int resMsg = this.channelFacade.addChannel(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean deleteChannel(String id) {
		return this.channelFacade.deleteChannel(id);
	}

	@Override
	public boolean updateChannel(ReqBMSChannelVO reqDemoVO) {
		return this.channelFacade.updateChannel(reqDemoVO);
	}

	@Override
	public List<ResBMSChannelVO> getAllChannel(ReqBMSChannelVO reqDemoVO) {
		return channelFacade.getAllChannel(reqDemoVO);
	}

	@Override
	public List<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO) {
		return channelFacade.getChannel(reqDemoVO);
	}

	@Override
	public ResBMSChannelVO findOne(ReqBMSChannelVO reqDemoVO) {
		return channelFacade.findOne(reqDemoVO);
	}
	@Override
	public Response<ResBMSCheckIsExitsVO> checkIsChennelExits(ReqBMSChannelVO reqDemoVO) {
		return channelFacade.checkIsChennelExits(reqDemoVO);
	}
	@Override
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile) {
		InputStream is = null;
		boolean isExcel2003 = true;
		if (isExcel2007(fileName)) {
			isExcel2003 = false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			is = (Mfile.getInputStream());
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			List<ReqBMSChannelVO> channelList = getChannelList(wb, Mfile);
			map.put("channelList", channelList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * 解析excel文件,返回实体list
	 * @param wb
	 * @param Mfile
	 * @return
	 */
	public List<ReqBMSChannelVO> getChannelList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSChannelVO> channelList = new ArrayList<ReqBMSChannelVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("渠道模板")) {
				sheet = wb.getSheetAt(i);
			} else{
				ss++;
			}
		}
		if(sheet==null&&ss!=0){
			sheet=wb.getSheetAt(0);
		}

		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			int sNum = 0;
			String failureCause = "";
			if (i != 0) {
				if (null != row && row.getFirstCellNum() > -1) {
					int rowNum = row.getLastCellNum();
					//创建vo类对象
					ReqBMSChannelVO channelVo = new ReqBMSChannelVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 渠道代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									channelVo.setCode(cell.getStringCellValue());
									channelVo.setDerivedResult(IMPORT_SUCCESS);
								} else {
									channelVo.setCode("");
									channelVo.setDerivedResult(IMPORT_FAIL);
									failureCause += "渠道代码为空;";
									sNum++;
								}
							} else if (c == 1) {// 渠道名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									channelVo.setName(cell.getStringCellValue());
									channelVo.setDerivedResult(IMPORT_SUCCESS);
								} else {
									channelVo.setName("");
									channelVo.setDerivedResult(IMPORT_FAIL);
									failureCause += "渠道名称为空;";
									sNum++;
								}
							} else if (c == 2) {// 起售时间			
									String startTime=getCell(cell);
								if (!StringUtils.isEmpty(startTime)) {
									if (isValidDate(startTime)||isValidDateTwo(startTime)) {
										channelVo.setStartSalesTime(startTime);
										channelVo.setDerivedResult(IMPORT_SUCCESS);
									}else{
										channelVo.setStartSalesTime("noDate");
										channelVo.setMistakeStartSalesTime(startTime);
										channelVo.setDerivedResult(IMPORT_FAIL);
										failureCause += "起售时间类型不正确;";
										sNum++;
									}	
								} else {
									channelVo.setStartSalesTime("");
									failureCause += "起售时间为空;";
									sNum++;
								}
							}else if (c == 3) {// 停售时间
								String stopTime=getCell(cell);
								if (!StringUtils.isEmpty(stopTime)) {
									if (isValidDate(stopTime)||isValidDateTwo(stopTime)) {
										channelVo.setStopSalesTime(stopTime);
										channelVo.setDerivedResult(IMPORT_SUCCESS);
									}else{
										channelVo.setStopSalesTime("noDate");
										channelVo.setMistakeStopSalesTime(stopTime);
										channelVo.setDerivedResult(IMPORT_FAIL);
										failureCause += "停售时间类型不正确;";
										sNum++;
									}	
								} else {
									channelVo.setStartSalesTime("");
									failureCause += "停售时间为空;";
									sNum++;
								}
							}
							channelVo.setFailureCause(failureCause);
						}
					}
					if (sNum > 0) {
						channelVo.setDerivedResult(IMPORT_FAIL);
					} else {
						channelVo.setDerivedResult(IMPORT_SUCCESS);
					}
					channelList.add(channelVo);
				}
			}
		}

		return channelList;

	}

	/**
	 * 判断传入的文件是否为excel2007
	 * @param fileName
	 * @return
	 */
	public boolean isExcel2007(String fileName) {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return false;
		} else if ("xlsx".equals(extension)) {
			return true;
		}
		return false;

	}
	
	/**
	 * 判断日期有效性 格式:yyyy-mm-dd
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(sDate);
		return m.matches();
	}
	/**
	 * 判断日期有效性 格式:yyyy/mm/dd
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDateTwo(String sDate) {    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
        try{  
            Date date = (Date)formatter.parse(sDate);  
            return sDate.equals(formatter.format(date));  
        }catch(Exception e){  
            return false;  
        }  
    }  
	
	
	/**
	 * 获取日期格式并返回string
	 * @param cell
	 * @return
	 */
	public String getCell(Cell cell) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case Cell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}

	@Override
	public boolean deleteChannelCheck(ReqBMSChannelVO reqBMSChannelVO) {
		return this.channelFacade.deleteChannelCheck(reqBMSChannelVO);
	}

	@Override
	public List<ResBMSChannelVO> findChannelEqDate(ReqBMSChannelVO reqDemoVO) {
		return channelFacade.findChannelEqDate(reqDemoVO);
	}

	@Override
	public List<ResBMSEnumCodeVO> findContractTypeList(ReqLoanContractSignVo reqLoanContractSignVo) {
		return channelFacade.findContractTypeList(reqLoanContractSignVo);
	}

	@Override
	public List<ResBMSChannelVO> findChannelByOrgIds(ReqBMSChannelVO reqBMSChannelVO) {
		return channelFacade.findChannelByOrgIds(reqBMSChannelVO);
	}

}
