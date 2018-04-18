package com.ymkj.cms.web.boss.service.master.impl;


import java.io.InputStream;
import java.math.BigDecimal;
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
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.facade.apply.OrgLimitChannelFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductAuditLimitFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductFacade;
import com.ymkj.cms.web.boss.service.master.IChannelService;
import com.ymkj.cms.web.boss.service.master.IOrgLimitChannelService;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Service
public class OrgLimitChannelServiceImpl implements IOrgLimitChannelService {

	@Autowired
	private OrgLimitChannelFacade orgLimitChannelFacade;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private ProductFacade productFacade;
	@Autowired
	private ProductAuditLimitFacade productAuditLimitFacade;
 

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
			List<ReqBMSOrgLimitChannelVO> orgLimitChannelList = getBankList(wb, Mfile);
			map.put("orgLimitChannelList", orgLimitChannelList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	} 
	
	/**
	 * 解析excel工作表,并返回list集合
	 * @param wb
	 * @param Mfile
	 * @return
	 */
	public List<ReqBMSOrgLimitChannelVO> getBankList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSOrgLimitChannelVO> orgLimitChannelList = new ArrayList<ReqBMSOrgLimitChannelVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("网点产品模板")) {
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
					ReqBMSOrgLimitChannelVO orgLimitChannelVO= new ReqBMSOrgLimitChannelVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 网点名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									orgLimitChannelVO.setOrgName(cell.getStringCellValue());
									//创建实体类用于接收数据
									ReqOrganizationVO reqVo=new ReqOrganizationVO();
									reqVo.setName(orgLimitChannelVO.getOrgName());
									ResOrganizationVO resVo=orgLimitChannelFacade.findByName(reqVo);
									if(resVo==null){
										orgLimitChannelVO.setDerivedResult("失败");
										failureCause+="在营业部主表中不存在该营业部信息;";
										sNum++;
									}else{
										orgLimitChannelVO.setOrgId(resVo.getId());
										//需要调用接口查询营业部ID
										orgLimitChannelVO.setDerivedResult("成功");
									}
								} else {
									orgLimitChannelVO.setOrgName("");
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "网点名称为空;";
									sNum++;
								}
							} else if (c == 1) {// 渠道代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									orgLimitChannelVO.setChannelCode(cell.getStringCellValue());
									orgLimitChannelVO.setDerivedResult("成功");
								} else {
									orgLimitChannelVO.setChannelCode("");
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "渠道代码为空;";
									sNum++;
								}
							} else if (c == 2) {// 渠道名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									orgLimitChannelVO.setChannelName(cell.getStringCellValue().toString());
									ResBMSChannelVO res=new ResBMSChannelVO();//创建渠道实体类
									ReqBMSChannelVO channelVO=new ReqBMSChannelVO();
									channelVO.setCode(orgLimitChannelVO.getChannelCode());
									channelVO.setName(orgLimitChannelVO.getChannelName());
									res=channelService.findOne(channelVO);
									if(res==null){
										/*limitChannelVo.setChannelId(Long.parseLong("0"));*/
										orgLimitChannelVO.setDerivedResult("失败");
										failureCause+="在渠道表中不存在该渠道信息;";
										sNum++;
									}else{
										orgLimitChannelVO.setChannelId((long)res.getId());
										orgLimitChannelVO.setDerivedResult("成功");
									}
									orgLimitChannelVO.setDerivedResult("成功");
								} else {
									orgLimitChannelVO.setChannelName("");
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "渠道名称为空;";
									sNum++;
								}
							}else if (c == 3) {// 产品代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									orgLimitChannelVO.setProductCode(cell.getStringCellValue().toString());
									orgLimitChannelVO.setDerivedResult("成功");
								} else {
									orgLimitChannelVO.setProductCode("");
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "产品代码为空;";
									sNum++;
								}
							}else if (c == 4) {// 产品名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {	
									orgLimitChannelVO.setProductName(cell.getStringCellValue().toString());	
									ReqBMSProductVO product=new ReqBMSProductVO();
     								product.setName(orgLimitChannelVO.getProductName());
     								product.setCode(orgLimitChannelVO.getProductCode());
     								//按产品代码和产品名称查询渠道ID
     								ResBMSProductVO res=productFacade.findByVO(product);
     								if(res==null){
     									/*limitChannelVo.setProductId(Long.parseLong("0"));*/
     									orgLimitChannelVO.setProductId(Long.parseLong("-1"));
     									orgLimitChannelVO.setDerivedResult("失败");
	    								failureCause+="在产品表中不存在该产品信息;";
	    								sNum++;
     								}else{
     									orgLimitChannelVO.setProductId(res.getProductId());
     									orgLimitChannelVO.setDerivedResult("成功");
     								}
									
								} else {
									orgLimitChannelVO.setProductId(Long.parseLong("-1"));
									orgLimitChannelVO.setProductName("");	
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "产品名称为空;";
									sNum++;
								}
							}else if (c == 5) {// 产品期限
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									orgLimitChannelVO.setAuditLimit(Integer.parseInt(cell.getStringCellValue().toString()));
									orgLimitChannelVO.setProductAuditLimit(cell.getStringCellValue().toString());
									//创建产品期限VO类的对象
     								
 									ReqBMSProductAuditLimitVO productAuditLimitVO=new ReqBMSProductAuditLimitVO();
 									productAuditLimitVO.setProductId(orgLimitChannelVO.getProductId());
 									productAuditLimitVO.setAuditLimit(Long.parseLong(orgLimitChannelVO.getAuditLimit().toString()));
 									ResBMSProductAuditLimitVO res=productAuditLimitFacade.findByVO(productAuditLimitVO);
 									if(res==null){
 										/*limitChannelVo.setAuditLimitId(Long.parseLong("0"));*/
 										orgLimitChannelVO.setDerivedResult("失败");
	    								failureCause+="在期限表中不存在该产品期限;";
	    								sNum++;
 									}else{
 										orgLimitChannelVO.setAuditLimitId(res.getAuditLimitId());
 										orgLimitChannelVO.setUpperLimit(res.getUpperLimit());
 										orgLimitChannelVO.setFloorLimit(res.getFloorLimit());
 										orgLimitChannelVO.setDerivedResult("成功");
 									}
								} else {
									orgLimitChannelVO.setProductAuditLimit("");
									orgLimitChannelVO.setAuditLimit(Integer.parseInt("0"));
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "产品期限为空;";
									sNum++;
								}
							}/*else if (c == 6) {// 产品额度下限
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									try {
										orgLimitChannelVO.setFloorLimit(new BigDecimal(cell.getStringCellValue()));
										orgLimitChannelVO.setDerivedResult("成功");
									} catch (Exception e) {
										orgLimitChannelVO.setFloorLimit(BigDecimal.ZERO);
										orgLimitChannelVO.setDerivedResult("失败");
	    								failureCause+="产品额度下限类型转换错误;";
	    								orgLimitChannelVO.setFailureCause(failureCause);
	    								orgLimitChannelVO.setMistakeFloorLimit(cell.getStringCellValue());
										sNum++;
									}
								} else {
									orgLimitChannelVO.setFloorLimit(null);
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "产品额度下限为空;";
									sNum++;
								}
							}else if (c == 7) {// 产品额度上限
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {	
									try {
										orgLimitChannelVO.setUpperLimit(new BigDecimal(cell.getStringCellValue()));
										orgLimitChannelVO.setDerivedResult("成功");
									} catch (Exception e) {
										orgLimitChannelVO.setUpperLimit(BigDecimal.ZERO);
										orgLimitChannelVO.setDerivedResult("失败");
	    								failureCause+="产品额度上限类型转换错误;";
	    								orgLimitChannelVO.setFailureCause(failureCause);
	    								orgLimitChannelVO.setMistakeUpperLimit(cell.getStringCellValue());
										sNum++;
									}
								} else {
									orgLimitChannelVO.setUpperLimit(null);
									orgLimitChannelVO.setDerivedResult("失败");
									failureCause += "产品额度上限为空;";
									sNum++;
								}
							}*/
							
							orgLimitChannelVO.setFailureCause(failureCause);
						}
					}
					if (sNum > 0) {
						orgLimitChannelVO.setDerivedResult("失败");
					} else {
						orgLimitChannelVO.setDerivedResult("成功");
					}
					orgLimitChannelList.add(orgLimitChannelVO);
				}
			}
		}

		return orgLimitChannelList;

	}
	
	   /**
	    * 判断传入的类型是否为excel2007
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
	public List<ResOrganizationVO> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		//接口参数封装
		
		return orgLimitChannelFacade.findOrgByProductCodeListIntersect(reqBMSOrgLimitChannelVO);
		
	}

	@Override
	public Map<String, Object> branchProductRelevanceCheck(List<ReqBMSOrgLimitChannelVO> reqBMSOrgLimitChannelVOList) {
		
		return orgLimitChannelFacade.branchProductRelevanceCheck(reqBMSOrgLimitChannelVOList);
	}

	@Override
	public boolean updateByOrgLimitId(
			ReqBMSOrgLimitChannelVO reqOrgLimitChannelVO) {
		if(reqOrgLimitChannelVO!=null){
			int resMsg = orgLimitChannelFacade.updateLimitChannelById(reqOrgLimitChannelVO);
			return resMsg > 0 ? true : false;
			}else{
				return false;
			}
	}

	@Override
	public PageResult<ResBMSOrgLimitChannelVO> listPage(
			ReqBMSOrgLimitChannelVO reqDemoVO) {
		
		return orgLimitChannelFacade.listPage(reqDemoVO);
	}
	@Override
	public boolean deleteProductTerm(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {

		int result= orgLimitChannelFacade.updateProductTerm(reqBMSOrgLimitChannelVO);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
}
