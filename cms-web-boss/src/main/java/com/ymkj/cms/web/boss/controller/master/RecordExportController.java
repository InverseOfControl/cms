package com.ymkj.cms.web.boss.controller.master;

import java.io.IOException;
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

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportSZVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IRecordExportHomeTownService;
import com.ymkj.cms.web.boss.service.master.IRecordExportService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("recordExport")
public class RecordExportController extends BaseController{
	//深圳本地
	@Autowired
	private IRecordExportService iRecordExportService;
	//异地
	@Autowired
	private IRecordExportHomeTownService iRecordExportHomeTownService;
	
	
	@RequestMapping("view")
	public String view() {
		return "master/recordExport/recordExportList";
	}
	
	//深圳地区
	@RequestMapping(value = "listPageSZ")
	@ResponseBody
	public ResponsePage<ResBMSRecordExportSZVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		if (reqBMSRecordExportVO.getPageNum() == 0 || reqBMSRecordExportVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqBMSRecordExportVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqBMSRecordExportVO.setIsDeleted((long) 0);
		reqBMSRecordExportVO.setChannelName("00014");
		if(reqBMSRecordExportVO.getQueryAreaType()==null||reqBMSRecordExportVO.getQueryAreaType().length()==0){
			reqBMSRecordExportVO.setQueryAreaType("01");
		}
		PageResult<ResBMSRecordExportSZVO> pageResult = iRecordExportService.listPage(reqBMSRecordExportVO);
		ResponsePage<ResBMSRecordExportSZVO> pageList = new ResponsePage<ResBMSRecordExportSZVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	//异地查询
	@RequestMapping(value = "listPageYD")
	@ResponseBody
	public ResponsePage<ResBMSRecordExportYDVO> listPageYd(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		if (reqBMSRecordExportVO.getPageNum() == 0 || reqBMSRecordExportVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		// 必须 设置请求编码
		reqBMSRecordExportVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqBMSRecordExportVO.setIsDeleted((long) 0);
		reqBMSRecordExportVO.setChannelName("00014");
		PageResult<ResBMSRecordExportYDVO> pageResult = iRecordExportHomeTownService.listPageYd(reqBMSRecordExportVO);
		ResponsePage<ResBMSRecordExportYDVO> pageList = new ResponsePage<ResBMSRecordExportYDVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	
	//导出深圳地区
	@RequestMapping(value = "/uploadExcelSZ") 
	@ResponseBody
	public void uploadExcelSZ(ReqBMSRecordExportVO reqBMSRecordExportVO,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		reqBMSRecordExportVO.setChannelName("00014");
		reqBMSRecordExportVO.setQueryAreaType("01");
		reqBMSRecordExportVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<List<ResBMSRecordExportSZVO>> list=iRecordExportService.uploadExcelSZ(reqBMSRecordExportVO);
		CreateUploadExcelSZExcel(list.getData(), response,reqBMSRecordExportVO.getQueryAreaType());

	}
	
			// 创建深圳地区excel文件
			public void CreateUploadExcelSZExcel(List<ResBMSRecordExportSZVO> resBMSRecordExportSZVOs, HttpServletResponse response,String importExcelAreaType) throws IOException {
				response.setContentType("application/vnd.ms-excel");
				ServletOutputStream out = response.getOutputStream();
				Date nowDate = new Date();
				if(importExcelAreaType.equals("01")){
					importExcelAreaType="深圳地区";
				}else{
					importExcelAreaType="异地";
				}
				String fileName = importExcelAreaType+"导出数据" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
				if (resBMSRecordExportSZVOs.size() > 0) {
					List<String[]> rows = new ArrayList<String[]>();
					for (ResBMSRecordExportSZVO vo : resBMSRecordExportSZVOs) {
						String[] row = new String[5];
						// 商户代码
						row[0] = new String(vo.getMerchantCode()==null?"":vo.getMerchantCode());
						// 费项
						row[1] = new String(vo.getExpenditure()==null?"":vo.getExpenditure());
						//行别
						row[2] = new String(vo.getBankType()==null?"":vo.getBankType());
						// 账号
						row[3] = new String(vo.getAccountNumber()==null?"":vo.getAccountNumber());
						//户名
						row[4] = new String(vo.getName()==null?"":vo.getName());
						rows.add(row);
					}
					// 创建excel导出帮助类的对象
					ExportExcel exportExcel = new ExportExcel();
					exportExcel.setRows(rows);
					// 创建ExportExcel的List集合对象
					List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
					exportExcelList.add(exportExcel);

					// 设置导出文件的头部标题
					String[] headers = new String[5];
					headers[0] = new String("商户代码");
					headers[1] = new String("费项");
					headers[2] = new String("行别");
					headers[3] = new String("账号");
					headers[4] = new String("户名");
					// 创建workbook
					XSSFWorkbook xssf_w_book = new XSSFWorkbook();
					XSSFRow xssf_w_row = null;// 创建一行
					XSSFCell xssf_w_cell = null;// 创建每个单元格
					XSSFSheet sheet = xssf_w_book.createSheet("深圳地区导出");

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
//									if (exportExcelList.get(i).getRows().get(j)[4].equals("失败")) {
//										// 数据显示单元格样式设置
//
//										xssf_w_cell.setCellStyle(cellStyle_errorInfo);
//
//									} else {
										xssf_w_cell.setCellStyle(cellStyle_CN);
//									}

									// xssf_w_sheet.autoSizeColumn(a, true);
									// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
									 
									xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
									

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
					}

					xssf_w_book.write(out);
					out.flush();
					out.close();

				}else{

					// 设置导出文件的头部标题
					String[] headers = new String[1];
					headers[0] = new String("暂无数据");
					
					// 创建workbook
					XSSFWorkbook xssf_w_book = new XSSFWorkbook();
					XSSFRow xssf_w_row = null;// 创建一行
					XSSFCell xssf_w_cell = null;// 创建每个单元格
					XSSFSheet sheet = xssf_w_book.createSheet("深圳地区导出");

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

					xssf_w_book.write(out);
					out.flush();
					out.close();
				}

			}
			
			
			//导出异地
			@RequestMapping(value = "/uploadExcelYD") 
			@ResponseBody
			public void uploadExcelYD(ReqBMSRecordExportVO reqBMSRecordExportVO,
					HttpServletRequest request, HttpServletResponse response) throws IOException {
				reqBMSRecordExportVO.setChannelName("00014");
				reqBMSRecordExportVO.setQueryAreaType("99");
				reqBMSRecordExportVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
				Response<List<ResBMSRecordExportYDVO>> list=iRecordExportHomeTownService.uploadExcelYD(reqBMSRecordExportVO);
				CreateUploadExcelYDExcel(list.getData(), response,reqBMSRecordExportVO.getQueryAreaType());

			}
			
			// 创建异地excel文件
			public void CreateUploadExcelYDExcel(List<ResBMSRecordExportYDVO> resBMSRecordExportYDVOs, HttpServletResponse response,String importExcelAreaType) throws IOException {
				response.setContentType("application/vnd.ms-excel");
				ServletOutputStream out = response.getOutputStream();
				Date nowDate = new Date();
				if(importExcelAreaType.equals("01")){
					importExcelAreaType="深圳地区";
				}else{
					importExcelAreaType="异地";
				}
				String fileName = importExcelAreaType+"导出数据" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
				if (resBMSRecordExportYDVOs.size() > 0) {
					List<String[]> rows = new ArrayList<String[]>();
					for (ResBMSRecordExportYDVO vo : resBMSRecordExportYDVOs) {
						String[] row = new String[7];
						// 商户代码
						row[0] = new String(vo.getMerchantCode()==null?"":vo.getMerchantCode());
						// 费项
						row[1] = new String(vo.getExpenditure()==null?"":vo.getExpenditure());
						//行别
						row[2] = new String(vo.getBankType()==null?"":vo.getBankType());
						// 行号
						row[3] = new String(vo.getBankNo()==null?"":vo.getBankNo());
						//账号
						row[4] = new String(vo.getAccountNumber()==null?"":vo.getAccountNumber());
						//户名
						row[5] = new String(vo.getName()==null?"":vo.getName());
						//备注
						row[6] = new String(vo.getRemark()==null?"":vo.getRemark());
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
					headers[0] = new String("商户代码");
					headers[1] = new String("费项");
					headers[2] = new String("行别");
					headers[3] = new String("行号");
					headers[4] = new String("账号");
					headers[5] = new String("户名");
					headers[6] = new String("备注");
					// 创建workbook
					XSSFWorkbook xssf_w_book = new XSSFWorkbook();
					XSSFRow xssf_w_row = null;// 创建一行
					XSSFCell xssf_w_cell = null;// 创建每个单元格
					XSSFSheet sheet = xssf_w_book.createSheet("异地导出");

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
//												if (exportExcelList.get(i).getRows().get(j)[4].equals("失败")) {
//													// 数据显示单元格样式设置
			//
//													xssf_w_cell.setCellStyle(cellStyle_errorInfo);
			//
//												} else {
										xssf_w_cell.setCellStyle(cellStyle_CN);
//												}

												// xssf_w_sheet.autoSizeColumn(a, true);
												// 列头以及显示的数据 j 第几行 a 第几个单元格 如果为数字
												 
									    xssf_w_cell.setCellValue(exportExcelList.get(i).getRows().get(j)[a]);
												

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
					}

					xssf_w_book.write(out);
					out.flush();
					out.close();

				}else{
					
				
					// 设置导出文件的头部标题
					String[] headers = new String[1];
					headers[0] = new String("暂无数据");
					// 创建workbook
					XSSFWorkbook xssf_w_book = new XSSFWorkbook();
					XSSFRow xssf_w_row = null;// 创建一行
					XSSFCell xssf_w_cell = null;// 创建每个单元格
					XSSFSheet sheet = xssf_w_book.createSheet("异地导出");

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
					

					xssf_w_book.write(out);
					out.flush();
					out.close();
				}

			}
			
	
}
