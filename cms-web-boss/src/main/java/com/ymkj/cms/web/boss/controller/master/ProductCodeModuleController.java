package com.ymkj.cms.web.boss.controller.master;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductCodeModuleVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.service.master.IProductCodeModuleService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("productCodeModule")
public class ProductCodeModuleController extends BaseController {

	@Autowired
	private IProductCodeModuleService productCodeModuleService;

	@RequestMapping("importView")
	public String productDataImport() {
		return "master/productCodeModule/productCodeModuleDataImport";
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
	@RequestMapping(value = "/productCodeModuleDataImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestParam("productCodeModuleUploadfile") MultipartFile multipartFile,
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
			System.out.println("传入的文件为空文件!");

		// 从service层获取数据
		Map<String, Object> map = productCodeModuleService.Analytical(name, multipartFile);
		List<ReqBMSProductCodeModuleVO> productCodeModuleList = (List<ReqBMSProductCodeModuleVO>) map
				.get("productCodeModuleList");
		/*
		 * List<ReqBMSProductAuditLimitVO> productAuditLimitList=new
		 * ArrayList<ReqBMSProductAuditLimitVO>();
		 */
		Date nowDate = new Date();
		String fileName = "产品必填数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		response.reset();
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
			writer.write("/*delete bms_product_code_module sql*/ \n");
			writer.write("truncate table bms_product_code_module ;\n");
			writer.write("/*insert bms_product_code_module sql*/ \n");
			for (ReqBMSProductCodeModuleVO productCodeModuleVO : productCodeModuleList) {

				if (productCodeModuleVO.getDerivedResult().equals("成功")) {
					text = "insert into bms_product_code_module(PRODUCT_ID,CODE_ID,IS_DELETED,CREATOR,CREATOR_ID,CREATOR_DATE,VERSION)"
							+ "values('" + productCodeModuleVO.getProductId() + "'," + productCodeModuleVO.getCodeId()
							+ ",0,'admin',1,sysdate(),1)";
					System.out.println("sql:" + text);
					writer.write(text + ";\n");
				}
			}
			;
			/*
			 * if(!text.isEmpty()){ result=true; }
			 */
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
	 * 导入excel,下载excel文件反馈信息
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/productCodeModuleDataImportDownLoadExcel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDownLoadExcel(
			@RequestParam("productCodeModuleUploadfile") MultipartFile multipartFile, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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

		// 从service层获取数据
		Map<String, Object> map = productCodeModuleService.Analytical(name, multipartFile);
		List<ReqBMSProductCodeModuleVO> productCodeModuleList = (List<ReqBMSProductCodeModuleVO>) map
				.get("productCodeModuleList");

		CreateExcel(productCodeModuleList, response);

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	// 创建excel文件
	public void CreateExcel(List<ReqBMSProductCodeModuleVO> productCodeModuleList, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream out = response.getOutputStream();
		Date nowDate = new Date();
		String fileName = "产品必填数据导入" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
		if (productCodeModuleList.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ReqBMSProductCodeModuleVO idd : productCodeModuleList) {
				String[] row = new String[6];
				// 产品代码
				row[0] = new String(idd.getProductCodeExcel() == null ? "" : idd.getProductCodeExcel().toString());
				// 产品名称
				row[1] = new String(idd.getProductNameExcel() == null ? ""
						: (idd.getFailureCause().indexOf("在产品表中不存在该产品名称") != -1 ? idd.getProductNameExcel()
								: idd.getProductNameExcel().toString()));
				// 模块名称
				row[2] = new String(idd.getCodeNameExcel() == null ? ""
						: (idd.getFailureCause().indexOf("在产品表中不存在该模块名称") != -1 ? idd.getCodeNameExcel()
								: idd.getCodeNameExcel().toString()));
				// 排序
				row[3] = new String(idd.getSort() == null ? "" : idd.getSort().toString());
				// 导出结果
				row[4] = new String(idd.getDerivedResult() == null ? "" : idd.getDerivedResult().toString());
				// 失败原因
				row[5] = new String(idd.getFailureCause() == null ? "" : idd.getFailureCause().toString());
				rows.add(row);
			}
			// 创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			// 创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);

			// 设置导出文件的头部标题
			String[] headers = new String[7];
			headers[0] = new String("产品代码");
			headers[1] = new String("产品名称");
			headers[2] = new String("模块名称");
			headers[3] = new String("排序");
			headers[4] = new String("导出sql结果");
			headers[5] = new String("错误反馈信息");
			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("产品必填模块");

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
			cellStyle_Red.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			cellStyle_Red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
			cellStyle_Red.setWrapText(false);// 不换行显示

			XSSFCellStyle cellStyle_errorInfo = xssf_w_book.createCellStyle();// 创建失败样式
			XSSFFont errorInfoFont = xssf_w_book.createFont();
			/* font.setColor(HSSFColor.RED.index); */// 设置字体为红色
			errorInfoFont.setColor(IndexedColors.RED.getIndex());
			cellStyle_errorInfo.setFont(errorInfoFont);
			// 自适应列宽
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(1, true);
			XSSFRow headerRow = sheet.createRow(0);

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
							if (exportExcelList.get(i).getRows().get(j)[4].equals("失败")) {
								// 数据显示单元格样式设置

								xssf_w_cell.setCellStyle(cellStyle_errorInfo);

							} else {
								xssf_w_cell.setCellStyle(cellStyle_CN);
							}

							// xssf_w_sheet.autoSizeColumn(a, true);
							// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
							if (exportExcelList.get(i).getRows().get(j)[a] != null
									&& exportExcelList.get(i).getRows().get(j)[a].matches("\\d+(\\.\\d+)?")) {
								xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
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
				sheet.setColumnWidth(3, 256 * 5 + 184);
				sheet.setColumnWidth(4, 256 * 12 + 184);
				sheet.setColumnWidth(5, 256 * 12 + 184);
			}

			xssf_w_book.write(out);
			out.flush();
			out.close();

		}

	}

}
