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
import org.springframework.util.SocketUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.web.boss.facade.master.LineNumberSetFacade;
import com.ymkj.cms.web.boss.service.master.ILineNumberSetService;

@Service
public class LineNumberSetServiceImpl implements ILineNumberSetService{
	
	@Autowired
	private LineNumberSetFacade lineNumberSetFacade;

	@Override
	public PageResult<ResLineNumberSetVO> listPage(ReqLineNumberSetVO reqLineNumberSetVO) {
		PageResult<ResLineNumberSetVO> pageResult = lineNumberSetFacade.listPage(reqLineNumberSetVO);
		return pageResult;
	}

	@Override
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO) {
		return lineNumberSetFacade.updateLineNumber(reqLineNumberSetVO);
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
			List<ResLineNumberUploadExcelResultVO> lineNumberList=getLineNumberList(wb,Mfile);
			map.put("lineNumberList", lineNumberList);
			
		} catch (Exception e) {
			e.printStackTrace();  
		}
		
		return map;
	}
	
	public List<ResLineNumberUploadExcelResultVO> getLineNumberList(Workbook wb ,MultipartFile Mfile){
		List<ResLineNumberUploadExcelResultVO> productList=new ArrayList<ResLineNumberUploadExcelResultVO>();
		//第一个sheet 获取机构信息的sheet
		Sheet sheet=null;//定义工作表
		//int ss=0;
		int sheetNumber=wb.getNumberOfSheets();
		// 获取工作表的个数,并循环
//		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
//			if (wb.getSheetAt(i).getSheetName().equals("产品信息模板")) {
//				sheet = wb.getSheetAt(i);
//			} else{
//				ss++;
//			}
//		}
		if(sheetNumber==0){
			throw new RuntimeException("导入的EXCEL有问题");
		}
		sheet=wb.getSheetAt(0);
//		if(sheet==null&&ss!=0){
//			sheet=wb.getSheetAt(0);
//		}
//			
				 for(int i = sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
					 Row row = sheet.getRow(i);
					 int sNum=0;
					 String failureCause="";
					 if(i!=0){
	                 if(null != row && row.getFirstCellNum() >-1){
	                	 //int a=row.getFirstCellNum();  -->0
	                	 int rowNum=row.getLastCellNum();
	                	 ResLineNumberUploadExcelResultVO product=new ResLineNumberUploadExcelResultVO();
	                	 product.setReturnStatus("成功");
	                	 for(int c = 0; c <rowNum; c++)
	     				{ 
	                		String count=null;
	     					Cell cell = row.getCell(c); 
	     					System.out.println(cell);
	     					System.out.println(cell.getStringCellValue());
	     					if (null != cell && cell.getStringCellValue()!=""&&cell.getStringCellValue().length()>0){
	     						if(c==0){//银行编号
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							product.setBankCode(cell.getStringCellValue());
	     						}else if(c==1){//银行名称
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							product.setBankName(cell.getStringCellValue());
	     						}else if(c==2){//行别
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							product.setBankType(cell.getStringCellValue());
	     						}else if(c==3){//行号
	     							cell.setCellType(Cell.CELL_TYPE_STRING);
	     							product.setBankNo(cell.getStringCellValue());
	     						}
	     						
	     					}else{
	     						if(c==0){
	     							product.setReturnStatus("失败");
	     							product.setReturnMessage("银行编号必填,");
	     						}else if(c==1){
	     							product.setReturnStatus("失败");
	     							product.setReturnMessage(product.getReturnMessage()+"银行名称必填,");
	     						}else if(c==2){
	     							product.setReturnStatus("失败");
	     							product.setReturnMessage(product.getReturnMessage()+"行别必填,");
	     						}else{
	     							product.setReturnStatus("失败");
	     							product.setReturnMessage(product.getReturnMessage()+"行号必填");
	     						}
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
	public void insertOrUpdateDic(List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs,String importExcelAreaType) {
		lineNumberSetFacade.insertOrUpdateDic(LineNumberUploadVOs,importExcelAreaType);
	}

}
