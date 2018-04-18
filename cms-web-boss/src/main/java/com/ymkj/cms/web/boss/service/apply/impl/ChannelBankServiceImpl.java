package com.ymkj.cms.web.boss.service.apply.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.apply.ReqBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResBMSChannelBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.web.boss.facade.apply.ChannelBankFacade;
import com.ymkj.cms.web.boss.service.apply.IChannelBankService;
import com.ymkj.cms.web.boss.service.master.IBankService;
import com.ymkj.cms.web.boss.service.master.IChannelService;

@Service
public class ChannelBankServiceImpl implements IChannelBankService {
	
	private static String IMPORT_SUCCESS = "成功";
	private static String IMPORT_FAIL = "失败";
	@Autowired
	private ChannelBankFacade channelBankFacade;
	@Autowired
	private  IChannelService channelService;
	@Autowired
	private IBankService bankService;

	@Override
	public PageResult<ResBMSChannelBankVO> listPage(ReqBMSChannelBankVO reqDemoVO) {
		return channelBankFacade.listPage(reqDemoVO);
	}

	@Override
	public boolean addChannelBank(ReqBMSChannelBankVO reqDemoVO) {
		int resMsg = channelBankFacade.addChannelBank(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean updateChannelBank(ReqBMSChannelBankVO reqDemoVO) {
		return channelBankFacade.updateChannelBank(reqDemoVO);
	}

	@Override
	public ResBMSChannelBankVO findById(Long id) {
		return channelBankFacade.findById(id);
	}

	@Override
	public List<ResBMSChannelVO> getChannel(ReqBMSChannelVO reqDemoVO) {
		return channelBankFacade.getChannel(reqDemoVO);
	}

	@Override
	public List<ResBMSBankVO> getBank(ReqBMSBankVO reqDemoVO) {
		return channelBankFacade.getBank(reqDemoVO);
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
			List<ReqBMSChannelBankVO> channelBankList = getChannelBankList(wb, Mfile);
			map.put("channelBankList", channelBankList);

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
	public List<ReqBMSChannelBankVO> getChannelBankList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSChannelBankVO> channelBankList = new ArrayList<ReqBMSChannelBankVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("渠道银行模板")) {
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
					ReqBMSChannelBankVO channelBankVo = new ReqBMSChannelBankVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 渠道代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									channelBankVo.setChannelCode(cell.getStringCellValue());
									channelBankVo.setDerivedResult(IMPORT_SUCCESS);
								} else {
									channelBankVo.setChannelCode("");
									channelBankVo.setDerivedResult(IMPORT_FAIL);
									failureCause += "渠道代码为空;";
									sNum++;
								}
							} else if (c == 1) {// 渠道名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									channelBankVo.setChannelName(cell.getStringCellValue());
									ReqBMSChannelVO reqChannelVO = new ReqBMSChannelVO();
									reqChannelVO.setCode(channelBankVo.getChannelCode());
									reqChannelVO.setName(channelBankVo.getChannelName());
									ResBMSChannelVO channelVO = channelService.findOne(reqChannelVO);
									if(channelVO==null){
										channelBankVo.setDerivedResult(IMPORT_FAIL);
										failureCause += "渠道表中不存在该渠道信息;";
										sNum++;
									}else{
										channelBankVo.setChannelId((long)channelVO.getId());
										channelBankVo.setDerivedResult(IMPORT_SUCCESS);
									}
								} else {
									channelBankVo.setChannelName("");
									channelBankVo.setDerivedResult(IMPORT_FAIL);
									failureCause += "渠道名称为空;";
									sNum++;
								}
							} else if (c == 2) {// 银行名称			
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									channelBankVo.setBankName(cell.getStringCellValue());
									ReqBMSBankVO reqBankVO = new ReqBMSBankVO();
									reqBankVO.setName(channelBankVo.getBankName());
									ResBMSBankVO bankVO = bankService.findOne(reqBankVO);
									if(bankVO==null){
										channelBankVo.setDerivedResult(IMPORT_FAIL);
										failureCause += "银行表中不存在该银行信息;";
										sNum++;
									}else{
										channelBankVo.setBankId(bankVO.getId());
										channelBankVo.setDerivedResult(IMPORT_SUCCESS);
									}
									
								} else {
									channelBankVo.setChannelName("");
									channelBankVo.setDerivedResult(IMPORT_FAIL);
									failureCause += "银行名称为空;";
									sNum++;
								}
							}
							channelBankVo.setFailureCause(failureCause);
						}
					}
					if (sNum > 0) {
						channelBankVo.setDerivedResult(IMPORT_FAIL);
					} else {
						channelBankVo.setDerivedResult(IMPORT_SUCCESS);
					}
					channelBankList.add(channelBankVo);
				}
			}
		}

		return channelBankList;

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

	@Override
	public Response<ResBMSCheckIsExitsVO> checkIsExits(ReqBMSChannelBankVO reqBMSChannelBankVO) {
		return channelBankFacade.checkIsExits(reqBMSChannelBankVO);
	}

	@Override
	public Boolean checkParentIsStart(ReqBMSChannelBankVO reqDemoVO) {
		return channelBankFacade.checkParentIsStart(reqDemoVO);
	}
	
	@Override
	public Boolean checkParentIsChanel(ReqBMSChannelBankVO reqDemoVO) {
		return channelBankFacade.checkParentIsChanel(reqDemoVO);
	}
}
