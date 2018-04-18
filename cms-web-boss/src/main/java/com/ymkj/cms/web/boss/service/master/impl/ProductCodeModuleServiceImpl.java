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

import com.alibaba.dubbo.remoting.exchange.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductCodeModuleVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.facade.apply.EnumCodeFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductCodeModuleFacade;
import com.ymkj.cms.web.boss.facade.apply.ProductFacade;
import com.ymkj.cms.web.boss.service.master.IProductCodeModuleService;

@Service
public class ProductCodeModuleServiceImpl implements IProductCodeModuleService {

	@Autowired
	private ProductCodeModuleFacade productCodeModuleFacade;
	
	@Autowired
	private ProductFacade productFacade;
	
	@Autowired
	private EnumCodeFacade enumCodeFacade;

	@Override
	public boolean saveProductCodeModules(ReqBMSProductCodeModuleVO reqProductCodeModuleVO) {
		boolean isSuccess = productCodeModuleFacade.saveProductCodeModules(reqProductCodeModuleVO);
		return isSuccess;
	}

	@Override
	public ResProductBaseListVO findProCodeModulesByProId(ReqBMSProductCodeModuleVO reqDemoVO) {
		return productCodeModuleFacade.findProCodeModulesByProId(reqDemoVO);
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
			List<ReqBMSProductCodeModuleVO> productCodeModuleList = getProductCodeModuleList(wb, Mfile);
			map.put("productCodeModuleList", productCodeModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public List<ReqBMSProductCodeModuleVO> getProductCodeModuleList(Workbook wb, MultipartFile Mfile) {
		int sNum;//定义变量来接收错误个数
		List<ReqBMSProductCodeModuleVO> productCodeModuleList = new ArrayList<ReqBMSProductCodeModuleVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		// 获取工作表的个数,并循环得到产品必填模块
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("产品必填模块")) {
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
			
			String failureCause = "";
			if (i != 0) {
				if (null != row && row.getFirstCellNum() > -1) {
					sNum = 0;
					int rowNum = row.getLastCellNum();
					ReqBMSProductCodeModuleVO productCodeModule = new ReqBMSProductCodeModuleVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 产品代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									productCodeModule.setProductCodeExcel(cell.getStringCellValue());
								
									productCodeModule.setDerivedResult("成功");

								} else {
									productCodeModule.setProductCodeExcel("");
									productCodeModule.setDerivedResult("失败");
									failureCause += "产品代码为空;";
									sNum++;
								}
							} else if (c == 1) {// 产品名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									String productName=cell.getStringCellValue();
									ReqBMSProductVO product=new ReqBMSProductVO();
									product.setName(productName);
     								product.setCode(productCodeModule.getProductCodeExcel());
     								ResBMSProductVO res=productFacade.findByVO(product);
     								if(res==null){
     									productCodeModule.setProductId(Long.parseLong("0"));
     									productCodeModule.setDerivedResult("失败");
	    								failureCause+="在产品表中不存在该产品名称;";
	    								productCodeModule.setProductNameExcel(productName);
	    						
	    								sNum++;
     								}else{
     									productCodeModule.setProductId(res.getProductId());
     									productCodeModule.setProductNameExcel(productName);
     									productCodeModule.setDerivedResult("成功");
     								}
								} else {
									productCodeModule.setProductNameExcel("");
									productCodeModule.setDerivedResult("失败");
									failureCause += "产品名称为空;";
									sNum++;
								}
							} else if (c == 2) {// 模块名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
                                    String nameCN=cell.getStringCellValue();
                                    ReqBMSEnumCodeVO enumVo=new ReqBMSEnumCodeVO();
                                    enumVo.setNameCN(nameCN);
                                    ResBMSEnumCodeVO res=enumCodeFacade.findByVO(enumVo);
                                    if(res==null){
                                    	productCodeModule.setCodeId(Long.parseLong("0"));
                                    	productCodeModule.setDerivedResult("失败");
	    								failureCause+="在产品表中不存在该模块名称;";
	    								productCodeModule.setCodeNameExcel(nameCN);
	    						
	    								sNum++;
                                    }else{
                                    	productCodeModule.setCodeId(res.getCodeId());
                                    	productCodeModule.setCodeNameExcel(nameCN);
    									productCodeModule.setDerivedResult("成功");
                                    }
									

								} else {
									productCodeModule.setCodeNameExcel("");
									productCodeModule.setDerivedResult("失败");
									failureCause += "产品额度下限为空;";
									sNum++;
								}
							} else if (c == 3) {// 排序
								cell.setCellType(Cell.CELL_TYPE_STRING);
									productCodeModule.setSort(cell.getStringCellValue());
									productCodeModule.setDerivedResult("成功");

								
							}
							if (sNum > 0) {
								productCodeModule.setDerivedResult("失败");
							} else {
								productCodeModule.setDerivedResult("成功");
							}
							productCodeModule.setFailureCause(failureCause);
						}
					}
					productCodeModuleList.add(productCodeModule);
				}
			}
		}

		return productCodeModuleList;

	}

	// 判断传进来的文件类型
	public boolean isExcel2007(String fileName) {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			/* System.out.println("read2003Excel(file)"); */
			return false;// read2003Excel(file);//
		} else if ("xlsx".equals(extension)) {
			/* System.out.println("read2007Excel(file)"); */
			return true;// read2007Excel(file);//
		}
		return false;

	}

}
