package com.ymkj.cms.web.boss.service.master.impl;

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

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.web.boss.facade.apply.ContractChannelFacade;
import com.ymkj.cms.web.boss.facade.apply.ContractTemplateFacade;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.IContractChannelService;

@Service
public class ContractChannelServiceImpl implements IContractChannelService {

	@Autowired
	private IChannelService channelService;
	
	@Autowired
	private ContractTemplateFacade contractTemplateFacade;
	
   @Autowired
   private ContractChannelFacade contractChannelFacade;
	
	
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
			List<ReqBMSContractChannelVO> contractChannelList = getContractChannelList(wb, Mfile);
			map.put("contractChannelList", contractChannelList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * 解析读取excel的数据并存入list集合中
	 * @param wb
	 * @param Mfile
	 * @return
	 */
	public List<ReqBMSContractChannelVO> getContractChannelList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSContractChannelVO> contractChannelList = new ArrayList<ReqBMSContractChannelVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("渠道合同模板")) {
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
					ReqBMSContractChannelVO contractChannelVo = new ReqBMSContractChannelVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 渠道代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									contractChannelVo.setChannelCode(cell.getStringCellValue());
									contractChannelVo.setDerivedResult("成功");

								} else {
									contractChannelVo.setChannelCode("");
									contractChannelVo.setDerivedResult("失败");
									failureCause += "渠道代码为空;";
									sNum++;
								}
							} else if (c == 1) {// 渠道名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									contractChannelVo.setChannelName(cell.getStringCellValue());
									ResBMSChannelVO res=new ResBMSChannelVO();//创建渠道resVO类接收信息
									ReqBMSChannelVO channelVO=new ReqBMSChannelVO();//创建渠道reqVO类传入信息
									channelVO.setCode(contractChannelVo.getChannelCode());
									channelVO.setName(contractChannelVo.getChannelName());
									res=channelService.findOne(channelVO);
									
									if(res==null){
										contractChannelVo.setDerivedResult("失败");
										failureCause+="在渠道表中不存在该渠道信息;";
										sNum++;
									}else{
										/*contractChannelVo.setChannelId(Long.parseLong(res.getId()));*/
										contractChannelVo.setDerivedResult("成功");
									}
									
								} else {
									contractChannelVo.setChannelName("");
									contractChannelVo.setDerivedResult("失败");
									failureCause += "渠道名称为空;";
									sNum++;
								}
							} else if (c == 2) {// 合同模板名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									
									contractChannelVo.setTemplateName(cell.getStringCellValue().toString());
								
									contractChannelVo.setDerivedResult("成功");
								} else {
									contractChannelVo.setTemplateName("");
									contractChannelVo.setDerivedResult("失败");
									failureCause += "合同模板名称为空;";
									sNum++;
								}
							}else if (c == 3) {// 打印类型
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									
									contractChannelVo.setPrintType(cell.getStringCellValue().toString());
								
									contractChannelVo.setDerivedResult("成功");
								} else {
									contractChannelVo.setPrintType("");
									contractChannelVo.setDerivedResult("失败");
									failureCause += "打印类型为空;";
									sNum++;
								}
							}else if (c == 4) {// 模板文件（需要提供每个渠道对应的所有模板文件）
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									
									contractChannelVo.setTemplateUrl(cell.getStringCellValue().toString());
									ResBMSContractTemplateVO res=new ResBMSContractTemplateVO();
									ReqBMSContractTemplateVO vo=new ReqBMSContractTemplateVO();
									vo.setName(contractChannelVo.getTemplateName());
									vo.setPrintType(contractChannelVo.getPrintType().equals("套打")?"1":"2");
									vo.setTemplateUrl(contractChannelVo.getTemplateUrl());
									res=contractTemplateFacade.findByVO(vo);
									if(res==null){
										contractChannelVo.setDerivedResult("失败");
										failureCause += "模板文件不存在;";
										sNum++;
									}else{
										contractChannelVo.setDerivedResult("成功");
									}
									
								} else {
									contractChannelVo.setTemplateUrl("");
									contractChannelVo.setDerivedResult("失败");
									failureCause += "模板文件为空;";
									sNum++;
								}
							}
							
							contractChannelVo.setFailureCause(failureCause);
						}
					}
					if (sNum > 0) {
						contractChannelVo.setDerivedResult("失败");
					} else {
						contractChannelVo.setDerivedResult("成功");
					}
					contractChannelList.add(contractChannelVo);
				}
			}
		}

		return contractChannelList;

	}
	
	
	   /**
	    * 判断传入的excel文件是否为excel2007
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
	public PageResult<ResBMSContractChannelVO> listPage(
			ReqBMSContractChannelVO reqDemoVO) {
		
		return contractChannelFacade.listPage(reqDemoVO);
	}

}
