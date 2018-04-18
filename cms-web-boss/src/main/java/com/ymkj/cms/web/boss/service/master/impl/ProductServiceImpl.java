package com.ymkj.cms.web.boss.service.master.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.facade.apply.ProductFacade;
import com.ymkj.cms.web.boss.service.master.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductFacade productFacade;

	@Override
	public PageResult<ResBMSProductVO> listPage(ReqBMSProductVO reqDemoVO) {
		PageResult<ResBMSProductVO> pageResult = productFacade.listPage(reqDemoVO);
		return pageResult;
	}

	@Override
	public boolean addProduct(ReqBMSProductVO reqDemoVO) {
		int resMsg = productFacade.addProduct(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean deleteProduct(String productId) {
		return productFacade.deleteProduct(productId);
	}

	@Override
	public ResBMSProductVO findById(Long productId) {
		return productFacade.findById(productId);
	}

	@Override
	public boolean updateProduct(ReqBMSProductVO reqDemoVO) {
		return productFacade.updateProduct(reqDemoVO);
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
			List<ReqBMSProductVO> productList=getProductList(wb,Mfile);
			map.put("productList", productList);
			
		} catch (Exception e) {
			e.printStackTrace();  
		}
		
		return map;
	}
	
	public List<ReqBMSProductVO> getProductList(Workbook wb ,MultipartFile Mfile){
		List<ReqBMSProductVO> productList=new ArrayList<ReqBMSProductVO>();
			//第一个sheet 获取机构信息的sheet
		Sheet sheet=null;//定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("产品信息模板")) {
				sheet = wb.getSheetAt(i);
			} else{
				ss++;
			}
		}
		if(sheet==null&&ss!=0){
			sheet=wb.getSheetAt(0);
		}
			
				 for(int i = sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
					 Row row = sheet.getRow(i);
					 int sNum=0;
					 String failureCause="";
					 if(i!=0){
	                 if(null != row && row.getFirstCellNum() >-1){
	                	 int rowNum=row.getLastCellNum();
	                	 ReqBMSProductVO product=new ReqBMSProductVO();
	                	 for(int c = 0; c <rowNum; c++)
	     				{ 
	                		 String count=null;
	     					Cell cell = row.getCell(c); 
	     					if (null != cell){
	     						if(c==0){//产品代码
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
											product.setCode(cell.getStringCellValue());
											product.setDerivedResult("成功");
										
	    							}else{
	    								product.setCode("");
	    								product.setDerivedResult("失败");
	    								failureCause+="产品代码为空;";
	    								sNum++;
	    							}
	     						}else if(c==1){//产品名称
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	    								product.setName(cell.getStringCellValue());
	    								product.setDerivedResult("成功");
	    							}else{
	    								product.setName("");
	    								product.setDerivedResult("失败");
	    								failureCause+="产品名称为空;";
	    								sNum++;
	    							}
	     						}else if(c==2){//产品额度下限
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
											product.setFloorLimit(new BigDecimal(cell.getStringCellValue()));
											product.setDerivedResult("成功");
										} catch (Exception e) {
											product.setFloorLimit(BigDecimal.ZERO);
											product.setDerivedResult("失败");
		    								failureCause+="产品额度下限类型转换错误;";
		    								product.setFailureCause(failureCause);
											product.setMistakeFloorLimit(cell.getStringCellValue());
											
											
											sNum++;
										}
	    							}else{
	    								product.setFloorLimit(null);
	    								product.setDerivedResult("失败");
	    								failureCause+="产品额度下限为空;";
	    								sNum++;
	    							}
	     						}else if(c==3){//产品额度上限
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
											product.setUpperLimit(new BigDecimal(cell.getStringCellValue()));
											product.setDerivedResult("成功");
										} catch (Exception e) {
											product.setUpperLimit(BigDecimal.ZERO);
											product.setDerivedResult("失败");
		    								failureCause+="产品额度上限类型转换错误;";
		    								product.setFailureCause(failureCause);
											product.setMistakeUpperLimit(cell.getStringCellValue());
											
											sNum++;
										}
	    							}else{
	    								product.setUpperLimit(null);
	    								product.setDerivedResult("失败");
	    								failureCause+="产品额度上限为空;";
	    								sNum++;
	    							}
	     						}else if(c==4){//调整基数
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
											product.setAdjustBase(new BigDecimal(cell.getStringCellValue()));
											product.setDerivedResult("成功");
										} catch (Exception e) {
											product.setAdjustBase(BigDecimal.ZERO);
											product.setDerivedResult("失败");
		    								failureCause+="调整基数类型转换错误;";
		    								product.setFailureCause(failureCause);
											product.setMistakeAdjustBase(cell.getStringCellValue());
											
											sNum++;
										}
	    							}else{
	    								product.setAdjustBase(null);
	    								product.setDerivedResult("失败");
	    								failureCause+="调整基数为空;";
	    								sNum++;
	    							}
	     						}else if(c==5){//产品费率
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							if(!cell.getStringCellValue().isEmpty()){
	     								try {
	     									product.setRate(new BigDecimal(cell.getStringCellValue()).divide(new BigDecimal(1),4,BigDecimal.ROUND_HALF_UP));
	     									product.setDerivedResult("成功");
										} catch (Exception e) {
											product.setRate(BigDecimal.ZERO);
											product.setDerivedResult("失败");
		    								failureCause+="调整基数类型转换错误;";
		    								product.setFailureCause(failureCause);
											product.setMistakeAdjustBase(cell.getStringCellValue());
											sNum++;
										}
	     								
	     							}else{
	     								product.setRate(null);
	     								product.setDerivedResult("失败");
	    								failureCause+="调整基数为空;";
	    								sNum++;
	     							}
	     						}
	     						if(sNum>0){
	     							product.setDerivedResult("失败");
	     						}else{
	     							product.setDerivedResult("成功");
	     						}
	     						product.setFailureCause(failureCause);
	     					}
	     				}
	                	 productList.add(product);
	                 }
					 }
	                }
	             
			 
			
		return productList;

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
	public ResBMSProductVO findByVo(ReqBMSProductVO VO) {
		
		return productFacade.findByCode(VO);
	}

	@Override
	public Integer findProductById(Long productId) {
		
		return productFacade.findProductById(productId);
	}

}
