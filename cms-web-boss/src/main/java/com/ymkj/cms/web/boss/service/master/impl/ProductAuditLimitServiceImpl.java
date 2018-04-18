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
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.facade.apply.ProductAuditLimitFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductFacade;
import com.ymkj.cms.web.boss.service.master.IProductAuditLimitService;

@Service("productAuditLimitService")
public class ProductAuditLimitServiceImpl implements IProductAuditLimitService {
	@Autowired
	private ProductAuditLimitFacade productAuditLimitFacade;
	
	@Autowired
	private ProductFacade productFacade;

	@Override
	public ResBMSProductAuditLimitVO findByAuditLimitId(Long id) {
		return productAuditLimitFacade.findByAuditLimitId(id);
	}

	@Override
	public boolean addProductAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO) {
		int resMsg =productAuditLimitFacade.addProductAuditLimit(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean updateProductAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO) {
		return productAuditLimitFacade.updateProductAuditLimit(reqDemoVO);
	}

	@Override
	public List<ResBMSProductAuditLimitVO> findAllProductAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO) {
		return productAuditLimitFacade.findLimitByProductId(reqDemoVO);
	}

	@Override
	public PageResult<ResBMSProductAuditLimitVO> listPage(ReqBMSProductAuditLimitVO reqDemoVO) {
		PageResult<ResBMSProductAuditLimitVO> pageResult = productAuditLimitFacade.listPage(reqDemoVO);
		return pageResult;
	}

	@Override
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile) {
		InputStream is = null;  
		boolean isExcel2003 = true;
		if(isExcel2007(fileName)){
			isExcel2003 = false;  
		}
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			is = (Mfile.getInputStream());
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if(isExcel2003){
				wb = new HSSFWorkbook(is); 
			}
			else{
				wb = new XSSFWorkbook(is); 
			}
			List<ReqBMSProductAuditLimitVO> productAuditLimitList=getProductAuditLimitList(wb,Mfile);
			map.put("productAuditLimitList", productAuditLimitList);
			
		} catch (Exception e) {
			e.printStackTrace();  
		}
		
		return map;
	}
	
	public List<ReqBMSProductAuditLimitVO> getProductAuditLimitList(Workbook wb ,MultipartFile Mfile){
		int sNum;//定义变量用于存储导入excel中数据每行出现的错误个数
		List<ReqBMSProductAuditLimitVO> productAuditLimitList=new ArrayList<ReqBMSProductAuditLimitVO>();//定义实体list接收数据
		Sheet sheet=null;//定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("产品期限模板")) {
				sheet = wb.getSheetAt(i);
			} else{
				ss++;
			}
		}
		if(sheet==null&&ss!=0){
			sheet=wb.getSheetAt(0);
		}
		/*System.out.println("--sheet.getLastRowNum():"+sheet.getLastRowNum());*/
		//循环sheet中的row
				 for(int i = sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
					 Row row = sheet.getRow(i);
					
					 String failureCause="";//定义错误信息初始值
					 if(i!=0){
	                 if(null != row && row.getFirstCellNum() >-1){
	                	 sNum=0;//每行错误个数初始值
	                	 int rowNum=row.getLastCellNum();
	                	 ReqBMSProductAuditLimitVO productAuditLimit=new ReqBMSProductAuditLimitVO();
	                	 //循环row中的cell
	                	 for(int c = 0; c <rowNum; c++)
	     				{ 
	     					Cell cell = row.getCell(c); 
	     					if (null != cell){
	     						if(c==0){//产品代码
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								
	     								productAuditLimit.setProductCode(cell.getStringCellValue());
	     								productAuditLimit.setDerivedResult("成功");
										
	    							}else{
	    								productAuditLimit.setProductCode("");
	    								productAuditLimit.setDerivedResult("失败");
	    								failureCause+="产品代码为空;";
	    								sNum++;
	    							}
	     						}else if(c==1){//根据产品名称获取产品ID
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								
	     								String productName=cell.getStringCellValue();
	     								ReqBMSProductVO product=new ReqBMSProductVO();
	     								product.setName(productName);
	     								product.setCode(productAuditLimit.getProductCode());
	     								
	     								ResBMSProductVO res=productFacade.findByVO(product);
	     								if(res==null){
	     									productAuditLimit.setProductId(Long.parseLong("0"));
		    								productAuditLimit.setDerivedResult("失败");
		    								failureCause+="在产品表中不存在该产品名称;";
		    								productAuditLimit.setProductNameExcel(productName);
		    						
		    								sNum++;
	     								}else{
	     								productAuditLimit.setProductId(res.getProductId());
	     								productAuditLimit.setProductNameExcel(productName);
	     								productAuditLimit.setDerivedResult("成功");
	     								}
	    							}else{
	    								productAuditLimit.setProductId(null);
	    								productAuditLimit.setDerivedResult("失败");
	    								failureCause+="产品名称为空;";
	    								sNum++;
	    							}
	     						}else if(c==2){//产品期限
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
	     									productAuditLimit.setAuditLimit(Long.parseLong(cell.getStringCellValue()));
	     									productAuditLimit.setDerivedResult("成功");
										} catch (Exception e) {
											productAuditLimit.setAuditLimit(Long.parseLong("0"));
											productAuditLimit.setDerivedResult("失败");
		    								failureCause+="产品额度下限类型转换错误;";
		    								/*productAuditLimit.setFailureCause(failureCause);*/
		    								productAuditLimit.setMistakeAuditLimit(cell.getStringCellValue());
											sNum++;
										}
	    							}else{
	    								productAuditLimit.setAuditLimit(null);
	    								productAuditLimit.setDerivedResult("失败");
	    								failureCause+="产品期限为空;";
	    								sNum++;
	    							}
	     						}else if(c==3){//产品额度下限
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
	     									productAuditLimit.setFloorLimit(new BigDecimal(cell.getStringCellValue()));
	     									productAuditLimit.setDerivedResult("成功");
										} catch (Exception e) {
											productAuditLimit.setFloorLimit(BigDecimal.ZERO);
											productAuditLimit.setDerivedResult("失败");
		    								failureCause+="产品额度下限类型转换错误;";
		    								/*productAuditLimit.setFailureCause(failureCause);*/
		    								productAuditLimit.setMistakeFloorLimit(cell.getStringCellValue());
											
											sNum++;
										}
	    							}else{
	    								productAuditLimit.setFloorLimit(null);
	    								productAuditLimit.setDerivedResult("失败");
	    								failureCause+="产品额度下限为空;";
	    								sNum++;
	    							}
	     						}else if(c==4){//产品额度上限
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
	     									productAuditLimit.setUpperLimit(new BigDecimal(cell.getStringCellValue()));
	     									productAuditLimit.setDerivedResult("成功");
										} catch (Exception e) {
											productAuditLimit.setUpperLimit(BigDecimal.ZERO);
											productAuditLimit.setDerivedResult("失败");
		    								failureCause+="产品额度上限类型转换错误;";
		    								productAuditLimit.setFailureCause(failureCause);
		    								productAuditLimit.setMistakeUpperLimit(cell.getStringCellValue());
											
											sNum++;
										}
	    							}else{
	    								productAuditLimit.setUpperLimit(null);
	    								productAuditLimit.setDerivedResult("失败");
	    								failureCause+="产品额度上限为空;";
	    								sNum++;
	    							}
	     						}
	     	
	     						if(sNum>0){
	     							productAuditLimit.setDerivedResult("失败");
	     						}else{
	     							productAuditLimit.setDerivedResult("成功");
	     						}
	     						productAuditLimit.setFailureCause(failureCause);
	     					}
	     				}
	                	 productAuditLimitList.add(productAuditLimit);
	                 }
					 }
	                }
	             /*}*/
			 
			
		return productAuditLimitList;

	}

	//判断传进来的文件类型
		public boolean isExcel2007(String fileName){
			String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
					.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(extension)) {
			/*	System.out.println("read2003Excel(file)");*/
				return false;//read2003Excel(file);//
			} else if ("xlsx".equals(extension)) {
			/*	System.out.println("read2007Excel(file)");*/
				return true;//read2007Excel(file);//
			} 
			return false;
					
		}

		@Override
		public boolean updateProductAuditLimitByPID(
				ReqBMSProductAuditLimitVO reqDemoVO) {
			return productAuditLimitFacade.updateProductAuditLimitByPID(reqDemoVO);
		}
		@Override
		public boolean deleteProductTerm(ReqBMSProductAuditLimitVO reqDemoVO) {

			return productAuditLimitFacade.deleteProductTerm(reqDemoVO);
		}

		@Override
		public List<ResBMSOrgLimitChannelVO> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req) {
			return productAuditLimitFacade.findOutletByAuditLimitId(req);
		}

		@Override
		public boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req) {
			return productAuditLimitFacade.updateOrgLimitByAuditId(req);
		}

		@Override
		public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo) {
			return productAuditLimitFacade.updateOrgLimitById(channelVo);
			
		}

		@Override
		public List<ResBMSProductAuditLimitVO> listAuditLimit(ReqBMSProductAuditLimitVO reqDemoVO) {
			return productAuditLimitFacade.listAuditLimit(reqDemoVO);
		}
}
