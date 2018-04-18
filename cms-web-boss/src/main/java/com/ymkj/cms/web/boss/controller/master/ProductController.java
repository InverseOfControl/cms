package com.ymkj.cms.web.boss.controller.master;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IProductAuditLimitService;
import com.ymkj.cms.web.boss.service.master.IProductService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;
import com.ymkj.pms.biz.api.service.IOrganizationExecuter;
import com.ymkj.pms.biz.api.vo.request.ReqOrganizationVO;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResOrgNameAreaVO;
import com.ymkj.pms.biz.api.vo.response.ResOrganizationVO;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IOrganizationExecuter OrganizationExecuter;

	
	@Autowired
	private IProductAuditLimitService productAuditLimitService;

	@RequestMapping("view")
	public String view() {
		return "master/product/productList";
	}
	@RequestMapping("views")
	public String views() {
		return "master/product/productAllList";
	}

	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSProductVO> listPage(ReqBMSProductVO reqDemoVO) {
		if (reqDemoVO.getPageNum() == 0 || reqDemoVO.getPageSize() == 0) {
			/* System.out.println("pageNum:"); */
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		PageResult<ResBMSProductVO> pageResult = productService.listPage(reqDemoVO);
		ResponsePage<ResBMSProductVO> pageList = new ResponsePage<ResBMSProductVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}

	@RequestMapping(value = "addProduct")
	@ResponseBody
	public Map<String, Object> addProduct(ReqBMSProductVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		ReqBMSProductVO reqProduct=new ReqBMSProductVO();
		reqProduct.setCode(reqDemoVO.getCode());
		reqProduct.setIsDeleted((long)0);
		ResBMSProductVO vo = productService.findByVo(reqProduct);
		if(null!=vo){
			hashMap.put("isExisted",false);
		}else{
			// 调用新增接口
			if(null!=reqDemoVO.getRate()){
				reqDemoVO.setDebtRadio(reqDemoVO.getDebtRadio().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP));
				reqDemoVO.setRate(reqDemoVO.getRate().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP));	
				reqDemoVO.setPreferentialRate(reqDemoVO.getPreferentialRate().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP));
			}
			boolean addSuccess = productService.addProduct(reqDemoVO);
			hashMap.put("isSuccess", addSuccess ? true : false);
		}
		return hashMap;
	}

	@RequestMapping(value = "deleteProduct")
	@ResponseBody
	public Map<String, Object> deleteProduct(ReqBMSProductVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
//		int count=productService.findProductById(reqDemoVO.getProductId());
//		if(count>0){
//			hashMap.put("notSuccess", false);
//			return hashMap;	
//		}
		reqDemoVO.setIsDeleted((long) 1);
		// 调用新增接口
		boolean addSuccess = productService.updateProduct(reqDemoVO);
		hashMap.put("isSuccess", addSuccess ? true : false);
		return hashMap;
	}

	@RequestMapping(value = "updateProductInit")
	@ResponseBody
	public Map<String, Object> updateBankInit(ReqBMSProductVO reqDemoVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// 调用查询接口接口
		ResBMSProductVO vo = productService.findById((long) reqDemoVO.getProductId());
		hashMap.put("info", vo);
		return hashMap;
	}

	@RequestMapping(value = "updateProduct")
	@ResponseBody
	public Map<String, Object> updateProduct(ReqBMSProductVO reqDemoVO) {
		System.out.println("updateProduct()...");
		Map<String, Object> hashMap = new HashMap<String, Object>();

		ReqBMSProductAuditLimitVO reqProductALimitVO=new ReqBMSProductAuditLimitVO();
		//根据产品ID，查询所对应的产品期限表的数据
		reqProductALimitVO.setProductId(reqDemoVO.getProductId());
		reqProductALimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		List<ResBMSProductAuditLimitVO> bList = productAuditLimitService.findAllProductAuditLimit(reqProductALimitVO);//拿到所有的产品期限
		if(bList!=null && bList.size()>0){
			for(ResBMSProductAuditLimitVO vo: bList){
//				if(vo.getFloorLimit().compareTo(reqDemoVO.getFloorLimit())== -1 || vo.getUpperLimit().compareTo(reqDemoVO.getUpperLimit())==1){
//					reqProductALimitVO.setFloorLimit(reqDemoVO.getFloorLimit());
//					reqProductALimitVO.setUpperLimit(reqDemoVO.getUpperLimit());			
//					productAuditLimitService.updateProductAuditLimitByPID(reqProductALimitVO);
//					break;
//				}
				ReqBMSProductAuditLimitVO req=new ReqBMSProductAuditLimitVO();
				if(reqDemoVO.getUpperLimit().compareTo(vo.getFloorLimit())==-1 ||reqDemoVO.getFloorLimit().compareTo(vo.getUpperLimit())==1){//没有交集
					req.setAuditLimitId(vo.getAuditLimitId());
					//req.setIsDisabled(1L);
					req.setConfigureConflict("Y");
					productAuditLimitService.updateProductAuditLimitByPID(req);//更新产品期限表对应的信息
					
					productAuditLimitService.updateOrgLimitByAuditId(req);//更新网店产品表对应的期限ID对应的信息
					
				}else{//有交集
					req.setAuditLimitId(vo.getAuditLimitId());
					if(reqDemoVO.getFloorLimit().compareTo(vo.getFloorLimit())==-1){
						req.setFloorLimit(vo.getFloorLimit());
					}else{
						req.setFloorLimit(reqDemoVO.getFloorLimit());
					}
					
					if(reqDemoVO.getUpperLimit().compareTo(vo.getUpperLimit())==-1){
						req.setUpperLimit(reqDemoVO.getUpperLimit());
					}else{
						req.setUpperLimit(vo.getUpperLimit());
					}
					req.setConfigureConflict("N");
					productAuditLimitService.updateProductAuditLimitByPID(req);
					
					//根据产品期限ID查询对应的网店产品下面用到的该期限的数据
					List<ResBMSOrgLimitChannelVO> listOrgs=productAuditLimitService.findOutletByAuditLimitId(req);
					if(listOrgs!=null&&listOrgs.size()>0){
						for(ResBMSOrgLimitChannelVO v:listOrgs){//循环网店产品
							
							ReqBMSOrgLimitChannelVO channelVo=new ReqBMSOrgLimitChannelVO();
							if(req.getUpperLimit().compareTo(v.getFloorLimit())==-1 ||req.getFloorLimit().compareTo(v.getUpperLimit())==1){//没有交集
								
								channelVo.setId(v.getId());
								//channelVo.setIsDisabled(1);
								channelVo.setConfigure("Y");
								
								
								productAuditLimitService.updateOrgLimitById(channelVo);//更具ID更新网店产品信息
								
							}else{//有交集
								channelVo.setId(v.getId());
								if(req.getFloorLimit().compareTo(v.getFloorLimit())==-1){
									channelVo.setFloorLimit(v.getFloorLimit());
								}else{
									channelVo.setFloorLimit(req.getFloorLimit());
								}
								
								if(req.getUpperLimit().compareTo(v.getUpperLimit())==-1){
									channelVo.setUpperLimit(req.getUpperLimit());
								}else{
									channelVo.setUpperLimit(v.getUpperLimit());
								}
								channelVo.setConfigure("N");
								productAuditLimitService.updateOrgLimitById(channelVo);
							}
							
							
						}
					}
				}
			}
		}
		if(null!=reqDemoVO.getRate()){
			reqDemoVO.setRate(reqDemoVO.getRate().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP));	
			reqDemoVO.setDebtRadio(reqDemoVO.getDebtRadio().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP));
			reqDemoVO.setPreferentialRate(reqDemoVO.getPreferentialRate().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP));
		}else{
			reqDemoVO.setRate(new BigDecimal(0));
		}
		boolean addSuccess = productService.updateProduct(reqDemoVO);
		hashMap.put("isSuccess", addSuccess ? true : false);

		return hashMap;
	}

	@RequestMapping("importView")
	public String productDataImport() {
		return "master/product/productDataImport";
	}

	/**
	 * 导入excel,下载sql文件
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/productDataImport", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> add(@RequestParam("productUploadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean result = false;
		// 判断文件是否为空
		if (multipartFile == null)
			return null;
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) && size == 0)
			System.out.println("传入的文件为空文件!");// return null;

		Map<String, Object> map = productService.Analytical(name, multipartFile);
		List<ReqBMSProductVO> productList = (List<ReqBMSProductVO>) map.get("productList");

		/* CreateExcel(productList,response); */
		Date nowDate = new Date();
		String fileName = "产品数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		response.reset();//
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));// 设定输出文件头
		response.setContentType("text/x-plain");

		try {
			ServletOutputStream out = response.getOutputStream();
			String path = System.getProperty("java.io.tmpdir") + "\\poem.txt";
			File files = new File(path);
			FileOutputStream fos = new FileOutputStream(files);
			Writer writer = new OutputStreamWriter(fos, "GBK");
			String text = null;
			/* UserSession user= ApplicationContext.getUser(); */
			// 生成插入机构表sql creator_id,creator,created_time,version
			writer.write("/*delete bms_product sql*/ \n");
			writer.write("truncate table bms_product ;\n");
			writer.write("/*insert bms_product sql*/ \n");
			for (ReqBMSProductVO productVo : productList) {
				if (productVo.getDerivedResult().equals("成功")) {
					text = "insert into bms_product(NAME,CODE,FLOOR_LIMIT,UPPER_LIMIT,RATE,ADJUST_BASE,IS_DELETED,CREATOR,CREATOR_ID,CREATOR_DATE,VERSION)"
							+ "values('" + productVo.getName() + "','" + productVo.getCode() + "',"
							+ productVo.getFloorLimit() + "," + productVo.getUpperLimit() + ","+productVo.getRate()+","
							+ productVo.getAdjustBase() + ",0,'admin',1,sysdate(),1)";
					/* System.out.println("sql:"+text); */
					writer.write(text + ";\n");
				}
			}
			;
			if (!text.isEmpty()) {
				result = true;
			}
			writer.close();
			fos.close();
			FileInputStream fis = new java.io.FileInputStream(files);
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);
			byte[] cache = new byte[4096];
			for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
				byteOutputStream.write(cache, 0, offset);
			}
			byte[] bt = null;
			bt = byteOutputStream.toByteArray();
			out.write(bt);
			out.flush();
			out.close();
			fis.close();
			if (files.exists()) {
				files.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	/**
	 * 导入excel,下载excel文件
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/productDataImportDownLoadExcel", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> addDownLoadExcel(@RequestParam("productUploadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean result = false;
		// 判断文件是否为空
		if (multipartFile == null)
			return null;
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) && size == 0)
			System.out.println("传入的文件为空文件!");// return null;

		Map<String, Object> map = productService.Analytical(name, multipartFile);
		List<ReqBMSProductVO> productList = (List<ReqBMSProductVO>) map.get("productList");

		CreateExcel(productList, response);

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	// 创建excel文件
	public void CreateExcel(List<ReqBMSProductVO> productList, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream out = response.getOutputStream();
		Date nowDate = new Date();
		String fileName = "产品数据导入" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
		if (productList.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ReqBMSProductVO idd : productList) {
				String[] row = new String[8];
				// 产品代码
				row[0] = new String(idd.getCode() == null ? "" : idd.getCode().toString());
				// 产品名称
				/* System.out.println("产品名称:"+idd.getName().toString()); */
				row[1] = new String(idd.getName() == null ? "" : idd.getName().toString());
				// 产品额度下限
				/*
				 * System.out.println("产品额度下限:"+idd.getMistakeFloorLimit()+";"+
				 * idd.getFloorLimit().toString());
				 */
				row[2] = new String(idd.getFloorLimit() == null ? ""
						: (idd.getFailureCause().indexOf("产品额度下限类型转换错误") != -1 ? idd.getMistakeFloorLimit()
								: idd.getFloorLimit().toString()));
				// 产品额度上限
				row[3] = new String(idd.getUpperLimit() == null ? ""
						: (idd.getFailureCause().indexOf("产品额度上限类型转换错误") != -1 ? idd.getMistakeUpperLimit()
								: idd.getUpperLimit().toString()));
				// 调整基数
				row[4] = new String(idd.getAdjustBase() == null ? ""
						: (idd.getFailureCause().indexOf("调整基数类型转换错误") != -1 ? idd.getMistakeAdjustBase()
								: idd.getAdjustBase().toString()));
				//产品费率
				row[5] = new String((String) (idd.getRate() == null ? ""
						: (idd.getFailureCause().indexOf("调整基数类型转换错误") != -1 ? idd.getRate()
								: idd.getRate().toString())));
				// 导出结果
				row[6] = new String(idd.getDerivedResult() == null ? "" : idd.getDerivedResult().toString());
				// 失败原因
				row[7] = new String(idd.getFailureCause() == null ? "" : idd.getFailureCause().toString());
				rows.add(row);
			}
			// 创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			// 创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);

			// 设置导出文件的头部标题
			String[] headers = new String[8];
			headers[0] = new String("产品代码");
			headers[1] = new String("产品名称");
			headers[2] = new String("产品额度下限");
			headers[3] = new String("产品额度上限");
			headers[4] = new String("调整基数");
			headers[5] =new String("产品费率");
			headers[6] = new String("导出sql结果");
			headers[7] = new String("错误反馈信息");
			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("产品信息模板");

			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
			/* head_font.setFontHeightInPoints((short) 90); */
			/*
			 * cellStyle_CN.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			 * cellStyle_CN.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			 * // 上下居中 cellStyle_CN.setWrapText(false);// 不换行显示
			 */
			XSSFCellStyle cellStyle_Red = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont font = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			font.setColor(IndexedColors.RED.getIndex());
			cellStyle_Red.setFont(font);
			/*
			 * cellStyle_Red.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			 * cellStyle_Red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER)
			 * ;// 上下居中 cellStyle_Red.setWrapText(false);// 不换行显示
			 */
			XSSFCellStyle cellStyle_errorInfo = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont errorInfoFont = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			errorInfoFont.setColor(IndexedColors.RED.getIndex());
			cellStyle_errorInfo.setFont(errorInfoFont);

			XSSFRow headerRow = sheet.createRow(0);
			// 自适应列宽
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(1, true);
			/*
			 * sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
			 * sheet.getNumMergedRegions();
			 */
			for (int i = 0; i < headers.length; i++) {
				XSSFCell cell = headerRow.createCell(i);
				cell.setCellStyle(cellStyle_CN);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(headers[i]);
			}
			for (int i = 0; i < 1; i++) {
				if (exportExcelList.size() > 0) {
					int rowLength = exportExcelList.get(0).getRows().size();
					// 遍历行
					for (int j = 0; j < rowLength; j++) {
						// 创建行
						xssf_w_row = sheet.createRow(j + 1);

						for (int a = 0; a < exportExcelList.get(i).getRows().get(j).length; a++) {

							// 创建单元格
							xssf_w_cell = xssf_w_row.createCell(a);
							if (exportExcelList.get(i).getRows().get(j)[5].equals("失败")) {
								// 数据显示单元格样式设置

								xssf_w_cell.setCellStyle(cellStyle_errorInfo);

							} else {
								xssf_w_cell.setCellStyle(cellStyle_CN);
							}

							// xssf_w_sheet.autoSizeColumn(a, true);
							// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
							if (exportExcelList.get(i).getRows().get(j)[a] != null
									&& exportExcelList.get(i).getRows().get(j)[a].matches("\\d+(\\.\\d+)?")) {

								if (a != 0 && a != 1 && a != 5 && a != 6) {
									// XSSFCellStyle cellStyle =
									// xssf_w_book.createCellStyle();
									cellStyle_CN.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
									xssf_w_cell.setCellStyle(cellStyle_CN);
									// 列头以及显示的数据 j 第几行 a 第几个单元格
									xssf_w_cell.setCellValue(
											Double.parseDouble(exportExcelList.get(i).getRows().get(j)[a]));
								} else {
									xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
								}

							} else {
								xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
							}

						}

					}

				}
				/*
				 * for (int k = 0; k < headers.length; k++) { // 创建单元格
				 * sheet.autoSizeColumn(k);
				 * 
				 * }
				 */
				sheet.setColumnWidth(1, 256 * 8 + 184);
				sheet.setColumnWidth(2, 256 * 12 + 184);
				sheet.setColumnWidth(3, 256 * 12 + 184);
				sheet.setColumnWidth(4, 256 * 8 + 184);
				sheet.setColumnWidth(5, 256 * 12 + 184);
				sheet.setColumnWidth(6, 256 * 12 + 184);
				sheet.setColumnWidth(7, 256 * 12 + 184);
			}

			xssf_w_book.write(out);
			out.flush();
			out.close();

		}

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
	
	
	
	//在更新产品之前，先判断修改该产品上下线会不会让下级配置冲突
	@RequestMapping(value = "queryUpdateOnOff")
	@ResponseBody
	public Map<String, Object> queryUpdateOnOff(ReqBMSProductVO reqDemoVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		
		ReqBMSProductAuditLimitVO reqProductALimitVO=new ReqBMSProductAuditLimitVO();
		//根据产品ID，查询所对应的产品期限表的数据
		reqProductALimitVO.setProductId(reqDemoVO.getProductId());
		reqProductALimitVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		List<ResBMSProductAuditLimitVO> bList = productAuditLimitService.findAllProductAuditLimit(reqProductALimitVO);//拿到所有的产品期限
		
		String returnStr="";
		Boolean ifTwoConflict=false;
		
		Map<String,String> orgMap=new HashMap<String,String>();//组装网店对应的期限
		
		if(bList!=null && bList.size()>0){
			for(ResBMSProductAuditLimitVO vo: bList){
				if(reqDemoVO.getUpperLimit().compareTo(vo.getFloorLimit())==-1 ||reqDemoVO.getFloorLimit().compareTo(vo.getUpperLimit())==1){//没有交集
					ifTwoConflict=true;
					returnStr=returnStr+vo.getAuditLimit()+"期、";
				}
			}
			if(ifTwoConflict==true){
				returnStr+="配置冲突、确定保存吗？";
			}
		}
		if(bList!=null && bList.size()>0 && ifTwoConflict==false){
			
			for(ResBMSProductAuditLimitVO vo: bList){
				ReqBMSProductAuditLimitVO req=new ReqBMSProductAuditLimitVO();
				
				req.setAuditLimitId(vo.getAuditLimitId());
				req.setAuditLimit(vo.getAuditLimit());
				
				if(reqDemoVO.getFloorLimit().compareTo(vo.getFloorLimit())==-1){
					req.setFloorLimit(vo.getFloorLimit());
				}else{
					req.setFloorLimit(reqDemoVO.getFloorLimit());
				}
				
				if(reqDemoVO.getUpperLimit().compareTo(vo.getUpperLimit())==-1){
					req.setUpperLimit(reqDemoVO.getUpperLimit());
				}else{
					req.setUpperLimit(vo.getUpperLimit());
				}
				
				
				
				//根据产品期限ID查询对应的网店产品下面用到的该期限的数据
				List<ResBMSOrgLimitChannelVO> listOrgs=productAuditLimitService.findOutletByAuditLimitId(req);
				if(listOrgs!=null&&listOrgs.size()>0){
					
					for(ResBMSOrgLimitChannelVO v:listOrgs){//循环网店产品
						if(req.getUpperLimit().compareTo(v.getFloorLimit())==-1 ||req.getFloorLimit().compareTo(v.getUpperLimit())==1){//没有交集
							ifTwoConflict=true;
							Long orgId=v.getOrgId();
							
							ReqOrganizationVO reqOrganizationVO=new ReqOrganizationVO();
							reqOrganizationVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
							reqOrganizationVO.setId(orgId);
							Response<ResOrganizationVO> byOrgIdResponse = OrganizationExecuter.findById(reqOrganizationVO);
							
							if(orgMap.containsKey(byOrgIdResponse.getData().getName())){//如果包含这个KEY
								String orgValue=orgMap.get(byOrgIdResponse.getData().getName());
								if(orgValue.contains(req.getAuditLimit()+"")){
									//包含不做操作
								}else{
									//不包含追加上去
									orgValue=orgValue+req.getAuditLimit()+"期、";
									orgMap.put(byOrgIdResponse.getData().getName(), orgValue);
								}
							}else{//不包含
								orgMap.put(byOrgIdResponse.getData().getName(), req.getAuditLimit()+"期、");
							}
							
						}
						
					}
				}
			}
			
			if(ifTwoConflict==true){
				String orgMapStr=orgMap.toString();
				orgMapStr=orgMapStr.replace("{","");
				orgMapStr=orgMapStr.replace("}","");
				orgMapStr=orgMapStr.replace("=", ":");
				orgMapStr=orgMapStr.replace(",", "<br/>");
				returnStr="以下配置冲突、确定保存吗？  (合计"+orgMap.size()+"家营业部)<br/>"+orgMapStr;
				
			}
		}
		
		
		
		map.put("ifTwoConflict", ifTwoConflict);
		map.put("returnStr", returnStr);
		return map; 
	}

}
