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

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IOrgLimitChannelService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("orgLimitChannel")
public class OrgLimitChannelController extends BaseController {

	@Autowired
	private IOrgLimitChannelService orgLimitChannelService;

	@RequestMapping("importView")
	public String orgLimitChannelDataImport() {
		return "master/orgLimitChannel/orgLimitChannelDataImport";
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
	@RequestMapping(value = "/orgLimitChannelDataImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestParam("orgLimitChannelUploadfile") MultipartFile multipartFile,
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

		Map<String, Object> map = orgLimitChannelService.Analytical(name, multipartFile);
		List<ReqBMSOrgLimitChannelVO> orgLimitChannelList = (List<ReqBMSOrgLimitChannelVO>) map
				.get("orgLimitChannelList");

		response.reset();
		Date nowDate = new Date();
		String fileName = "网点产品数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
		/*response.setHeader("Content-disposition", "attachment; filename=" + fileName);*/// 设定输出文件头
		response.setContentType("text/x-plain");

		try {
			ServletOutputStream out = response.getOutputStream();
			String path = System.getProperty("java.io.tmpdir") + "\\poem.txt";
			File files = new File(path);
			FileOutputStream fos = new FileOutputStream(files);
			Writer writer = new OutputStreamWriter(fos, "GBK");
			String text = null;
			/* UserSession user= ApplicationContext.getUser(); */
			// 生成插入门店审批期限渠道表sql
			writer.write("/*delete bms_org_limit_channel sql*/ \n");
			writer.write("truncate table bms_org_limit_channel ;\n");
			writer.write("/*insert bms_org_limit_channel sql*/ \n");
			if (orgLimitChannelList != null) {
				for (ReqBMSOrgLimitChannelVO orgLimitChannelVo : orgLimitChannelList) {
					if (orgLimitChannelVo.getDerivedResult().equals("成功")) {
						text = "insert into bms_org_limit_channel(AUDIT_LIMIT_ID,ORG_ID,IS_DELETED,CHANNEL_ID,CREATOR,CREATOR_ID,CREATOR_DATE,IS_DISABLED,UPPER_LIMIT,FLOOR_LIMIT)"
								+ "values('" + orgLimitChannelVo.getAuditLimitId() + "',"
								+ orgLimitChannelVo.getOrgId() + ",0," + orgLimitChannelVo.getChannelId()
								+ ",'admin',1,sysdate(),0,'"+orgLimitChannelVo.getUpperLimit()+","+orgLimitChannelVo.getFloorLimit()+"')";
						System.out.println("sql:" + text);
						writer.write(text + ";\n");
					}
				}
				;
			}
			if (text != null) {
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
	@RequestMapping(value = "/orgLimitChannelDataImportDownLoadExcel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDownLoadExcel(@RequestParam("orgLimitChannelUploadfile") MultipartFile multipartFile,
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

		Map<String, Object> map = orgLimitChannelService.Analytical(name, multipartFile);
		List<ReqBMSOrgLimitChannelVO> orgLimitChannelList = (List<ReqBMSOrgLimitChannelVO>) map
				.get("orgLimitChannelList");

		CreateExcel(orgLimitChannelList, response, multipartFile);

		hashMap.put("isSuccess", result);

		return hashMap;
	}

	// 创建excel文件
	public void CreateExcel(List<ReqBMSOrgLimitChannelVO> orgLimitChannelList, HttpServletResponse response,
			MultipartFile Mfile) throws IOException {
		if (orgLimitChannelList.size() > 0) {
			List<String[]> rows = new ArrayList<String[]>();
			for (ReqBMSOrgLimitChannelVO idd : orgLimitChannelList) {
				String[] row = new String[10];
				// 网店名称
				row[0] = new String(idd.getOrgName() == null ? "" : idd.getOrgName().toString());
				// 渠道代码
				row[1] = new String(idd.getChannelCode() == null ? "" : idd.getChannelCode().toString());
				// 渠道名称
				row[2] = new String(idd.getChannelName() == null ? "" : idd.getChannelName());
				// 产品代码
				row[3] = new String(idd.getProductCode() == null ? "" : idd.getProductCode());
				// 产品名称
				row[4] = new String(idd.getProductName() == null ? "" : idd.getProductName());
				// 产品期限
				row[5] = new String(idd.getProductAuditLimit() == null ? "" : idd.getProductAuditLimit());
				// 产品额度下限
				row[6] = new String(idd.getFloorLimit() == null ? "" : idd.getFloorLimit().toString());
				// 产品额度上限
				row[7] = new String(idd.getUpperLimit() == null ? "" : idd.getUpperLimit().toString());
				// 导出结果
				row[8] = new String(idd.getDerivedResult() == null ? "" : idd.getDerivedResult().toString());
				// 失败原因
				row[9] = new String(idd.getFailureCause() == null ? "" : idd.getFailureCause().toString());
				rows.add(row);
			}
			// 创建excel导出帮助类的对象
			ExportExcel exportExcel = new ExportExcel();
			exportExcel.setRows(rows);
			// 创建ExportExcel的List集合对象
			List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
			exportExcelList.add(exportExcel);

			// 设置导出文件的头部标题
			String[] headers = new String[10];
			headers[0] = new String("网店名称");
			headers[1] = new String("渠道代码");
			headers[2] = new String("渠道名称");
			headers[3] = new String("产品代码");
			headers[4] = new String("产品名称");
			headers[5] = new String("产品期限");
			headers[6] = new String("产品额度下限");
			headers[7] = new String("产品额度上限");
			headers[8] = new String("导出sql结果");
			headers[9] = new String("错误反馈信息");
			// 创建workbook
			XSSFWorkbook xssf_w_book = new XSSFWorkbook();
			XSSFRow xssf_w_row = null;// 创建一行
			XSSFCell xssf_w_cell = null;// 创建每个单元格
			XSSFSheet sheet = xssf_w_book.createSheet("网点产品模板");

			XSSFCellStyle cellStyle_CN = xssf_w_book.createCellStyle();// 创建一个单元格样式
			XSSFFont head_font = xssf_w_book.createFont(); // 设置字体样式
			cellStyle_CN.setFont(head_font);
			/*
			 * cellStyle_CN.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			 * cellStyle_CN.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			 * // 上下居中 cellStyle_CN.setWrapText(false);
			 */// 不换行显示

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

			XSSFRow headerRow = sheet.createRow(0);
			// 自适应列宽
			/*
			 * sheet.autoSizeColumn(1); sheet.autoSizeColumn(1, true);
			 */

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
							if (exportExcelList.get(i).getRows().get(j)[8].equals("失败")) {
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
				// 设置列宽
				sheet.setColumnWidth(0, 256 * 12 + 184);
				sheet.setColumnWidth(1, 256 * 12 + 184);
				sheet.setColumnWidth(2, 256 * 12 + 184);
				sheet.setColumnWidth(3, 256 * 12 + 184);
				sheet.setColumnWidth(4, 256 * 12 + 184);
				sheet.setColumnWidth(5, 256 * 12 + 184);
				sheet.setColumnWidth(6, 256 * 12 + 184);
				sheet.setColumnWidth(7, 256 * 12 + 184);
				sheet.setColumnWidth(8, 256 * 12 + 184);
				sheet.setColumnWidth(9, 256 * 12 + 184);
				sheet.setColumnWidth(9, 256 * 14 + 184);
			}
			response.setContentType("application/vnd.ms-excel");
			ServletOutputStream out = response.getOutputStream();

			Date nowDate = new Date();
			String fileName = "网店产品数据导入" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
			xssf_w_book.write(out);
			out.flush();
			out.close();

		}

	}
	@RequestMapping("view")
	public String view() {
		return "master/orgLimitChannel/orgLimitChannelList";

	}
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResBMSOrgLimitChannelVO>listPage(ReqBMSOrgLimitChannelVO ReqOrglcVO){
		if(ReqOrglcVO.getPageSize()==0 || ReqOrglcVO.getPageNum()==0){
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		PageResult<ResBMSOrgLimitChannelVO> pageResult = orgLimitChannelService.listPage(ReqOrglcVO);
		ResponsePage<ResBMSOrgLimitChannelVO> pageList = new ResponsePage<ResBMSOrgLimitChannelVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
		
	}
	

}
