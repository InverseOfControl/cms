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
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.facade.apply.LimitChannelFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductAuditLimitFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductFacade;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.ILimitChannelService;
import com.ymkj.cms.web.boss.service.master.IProductAuditLimitService;

@Service
public class LimitChannelServiceImpl implements ILimitChannelService {

	@Autowired
	private LimitChannelFacade limitChannelFacade;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private ProductFacade productFacade;
	@Autowired
	private ProductAuditLimitFacade productAuditLimitFacade;
	
	@Override
	public boolean logicalDelete(ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO) {
		if(reqOrgLimitChannelVO!=null){
			int resMsg = limitChannelFacade.logicalDelete(reqOrgLimitChannelVO);
			return resMsg > 0 ? true : false;
			}else{
				return false;
			}
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
			List<ReqBMSLimitChannelVO> limitChannelList = getLimitChannelList(wb, Mfile);
			map.put("limitChannelList", limitChannelList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public List<ReqBMSLimitChannelVO> getLimitChannelList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSLimitChannelVO> limitChannelList = new ArrayList<ReqBMSLimitChannelVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("渠道产品期限模板")) {
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
					ReqBMSLimitChannelVO limitChannelVo = new ReqBMSLimitChannelVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 渠道代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									limitChannelVo.setChannelCode(cell.getStringCellValue());
									limitChannelVo.setDerivedResult("成功");

								} else {
									limitChannelVo.setChannelCode("");
									limitChannelVo.setDerivedResult("失败");
									failureCause += "渠道代码为空;";
									sNum++;
								}
							} else if (c == 1) {// 渠道名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									limitChannelVo.setChannelName(cell.getStringCellValue());
									ResBMSChannelVO res=new ResBMSChannelVO();//创建渠道实体类
									ReqBMSChannelVO channelVO=new ReqBMSChannelVO();
									channelVO.setCode(limitChannelVo.getChannelCode());
									channelVO.setName(limitChannelVo.getChannelName());
									res=channelService.findOne(channelVO);
									if(res==null){
										/*limitChannelVo.setChannelId(Long.parseLong("0"));*/
										limitChannelVo.setDerivedResult("失败");
										failureCause+="在渠道表中不存在该渠道信息;";
										sNum++;
									}else{
										limitChannelVo.setChannelId((long)res.getId());
										limitChannelVo.setDerivedResult("成功");
									}
									
									//按渠道代码和渠道名称查询渠道ID
								} else {
									limitChannelVo.setChannelName("");
									limitChannelVo.setDerivedResult("失败");
									failureCause += "渠道名称为空;";
									sNum++;
								}
							} else if (c == 2) {// 产品代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									
									limitChannelVo.setProductCode(cell.getStringCellValue().toString());
								
									limitChannelVo.setDerivedResult("成功");
								} else {
									limitChannelVo.setProductCode("");
									limitChannelVo.setDerivedResult("失败");
									failureCause += "产品代码为空;";
									sNum++;
								}
							}else if(c == 3){//产品名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									limitChannelVo.setProductName(cell.getStringCellValue());
									ReqBMSProductVO product=new ReqBMSProductVO();
     								product.setName(limitChannelVo.getProductName());
     								product.setCode(limitChannelVo.getProductCode());
     								//按产品代码和产品名称查询渠道ID
     								ResBMSProductVO res=productFacade.findByVO(product);
     								if(res==null){
     									/*limitChannelVo.setProductId(Long.parseLong("0"));*/
     									limitChannelVo.setProductId(Long.parseLong("-1"));
     									limitChannelVo.setDerivedResult("失败");
	    								failureCause+="在产品表中不存在该产品信息;";
	    								sNum++;
     								}else{
     								
     									limitChannelVo.setProductId(res.getProductId());
     									limitChannelVo.setDerivedResult("成功");
     								}
									limitChannelVo.setDerivedResult("成功");
									
								} else {
									limitChannelVo.setProductId(Long.parseLong("-1"));
									limitChannelVo.setProductName("");
									limitChannelVo.setDerivedResult("失败");
									failureCause += "产品名称为空;";
									sNum++;
								}
								
							}else if(c == 4){//产品期限
								cell.setCellType(Cell.CELL_TYPE_STRING);
     							if(!cell.getStringCellValue().isEmpty()){
     								try {
     									limitChannelVo.setAuditLimit(Integer.parseInt(cell.getStringCellValue()));
     									//创建产品期限VO类的对象
     								
     									ReqBMSProductAuditLimitVO productAuditLimitVO=new ReqBMSProductAuditLimitVO();
     									productAuditLimitVO.setProductId(limitChannelVo.getProductId());
     									productAuditLimitVO.setAuditLimit(Long.parseLong(limitChannelVo.getAuditLimit().toString()));
     									ResBMSProductAuditLimitVO res=productAuditLimitFacade.findByVO(productAuditLimitVO);
     									if(res==null){
     										/*limitChannelVo.setAuditLimitId(Long.parseLong("0"));*/
         									limitChannelVo.setDerivedResult("失败");
    	    								failureCause+="在产品表中不存在该产品期限;";
    	    								sNum++;
     									}else{
     										limitChannelVo.setAuditLimitId(res.getAuditLimitId());
         									limitChannelVo.setDerivedResult("成功");
     									}
     									limitChannelVo.setDerivedResult("成功");
									} catch (Exception e) {
										limitChannelVo.setAuditLimit(Integer.parseInt(cell.getStringCellValue()));
										limitChannelVo.setDerivedResult("失败");
	    								failureCause+="产品期限类型转换错误;";
	    								/*productAuditLimit.setFailureCause(failureCause);*/
	    								/*productAuditLimit.setMistakeAuditLimit(cell.getStringCellValue());*/
										sNum++;
									}
    							}else{
    								limitChannelVo.setAuditLimit(null);
    								limitChannelVo.setDerivedResult("失败");
    								failureCause+="产品期限为空;";
    								sNum++;
    							}
								
							}
							limitChannelVo.setFailureCause(failureCause);
						}
					}
					if (sNum > 0) {
						limitChannelVo.setDerivedResult("失败");
					} else {
						limitChannelVo.setDerivedResult("成功");
					}
					limitChannelList.add(limitChannelVo);
				}
			}
		}

		return limitChannelList;

	}

	// 判断传进来的文件类型
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
	public boolean updateByAuLimitId(ReqBMSLimitChannelVO reqLimitChannelVO) {
		if(reqLimitChannelVO!=null){
			int resMsg = limitChannelFacade.updateLimitChannelById(reqLimitChannelVO);
			return resMsg > 0 ? true : false;
			}else{
				return false;
			}
	}



	@Override
	public PageResult<ResBMSLimitChannelVO> listPage(
			ReqBMSLimitChannelVO reqLimitChannelVO) {
		
		return limitChannelFacade.listPage(reqLimitChannelVO);
	}
	

}
