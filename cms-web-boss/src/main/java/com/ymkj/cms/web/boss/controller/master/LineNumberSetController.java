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
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.master.IContractTemplateService;
import com.ymkj.cms.web.boss.service.master.ILineNumberSetService;
import com.ymkj.cms.web.boss.service.master.impl.ExportExcel;

@Controller
@RequestMapping("lineNumberSet")
public class LineNumberSetController extends BaseController{
	@Autowired
	private ILineNumberSetService iLineNumberSetService;
	
	@RequestMapping("view")
	public String view() {
		return "master/lineNumberSet/lineNumberSetList";
	}
	
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResLineNumberSetVO> listPage(ReqLineNumberSetVO reqLineNumberSetVO) {
		if (reqLineNumberSetVO.getPageNum() == 0 || reqLineNumberSetVO.getPageSize() == 0) {
			// 数组参数 必须 与 错误枚举消息中的 占位符 个数 一致
			throw new BusinessException(CoreErrorCode.REQUEST_PARAM_ERROR, new Object[] { "pageNum | pageSize" });
		}
		reqLineNumberSetVO.setChannelName("00014");
		// 必须 设置请求编码
		reqLineNumberSetVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		PageResult<ResLineNumberSetVO> pageResult = iLineNumberSetService.listPage(reqLineNumberSetVO);
		ResponsePage<ResLineNumberSetVO> pageList = new ResponsePage<ResLineNumberSetVO>();
		pageList.setTotal(pageResult.getTotalCount());
		pageList.setRows(pageResult.getRecords());
		return pageList;
	}
	
	@RequestMapping(value = "updateLineNumber")
	@ResponseBody
	public Map<String, Object> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		reqLineNumberSetVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		Response<Integer> isSuccess = iLineNumberSetService.updateLineNumber(reqLineNumberSetVO);
		hashMap.put("isSuccess",isSuccess.getData());
		hashMap.put("returnMsg", isSuccess.getRepMsg());
		return hashMap;
	}
	
	
	@RequestMapping(value = "/LineNumberUploadFile", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> add(@RequestParam("productUploadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String importExcelAreaType=request.getParameter("importExcelAreaType");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean result = true;
		// 判断文件是否为空
		if (multipartFile == null){
			hashMap.put("nullFile",true);
			return hashMap;
		}
			
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
	    name=new String(name.getBytes("ISO-8859-1"), "UTF-8");  
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) && size == 0){
			hashMap.put("nullFile",true);
			return hashMap;
		}
			

		Map<String, Object> map = iLineNumberSetService.Analytical(name, multipartFile);
		List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs = (List<ResLineNumberUploadExcelResultVO>) map.get("lineNumberList");
		try {
			iLineNumberSetService.insertOrUpdateDic(LineNumberUploadVOs,importExcelAreaType);
			
			CreateExcel(LineNumberUploadVOs, response,importExcelAreaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

		//hashMap.put("isSuccess", result);

		return null;
	}
	
	// 创建excel文件
		public void CreateExcel(List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs, HttpServletResponse response,String importExcelAreaType) throws IOException {
			response.setContentType("application/vnd.ms-excel");
			ServletOutputStream out = response.getOutputStream();
			Date nowDate = new Date();
			String fileName = importExcelAreaType+"导入数据结果表" + DateUtil.defaultFormatDay(nowDate) + ".xlsx";
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
			if (LineNumberUploadVOs.size() > 0) {
				List<String[]> rows = new ArrayList<String[]>();
				for (ResLineNumberUploadExcelResultVO vo : LineNumberUploadVOs) {
					String[] row = new String[6];
					// 银行编号
					row[0] = new String(vo.getBankCode()==null?"":vo.getBankCode());
					// 银行名称
					row[1] = new String(vo.getBankName()==null?"":vo.getBankName());
					//行别
					row[2] = new String(vo.getBankType()==null?"":vo.getBankType());
					// 行号
					row[3] = new String(vo.getBankNo()==null?"":vo.getBankNo());
					//状态
					row[4] = new String(vo.getReturnStatus()==null?"":vo.getReturnStatus());
					//返回消息
					row[5] = new String(vo.getReturnMessage()==null?"":vo.getReturnMessage());
					rows.add(row);
				}
				// 创建excel导出帮助类的对象
				ExportExcel exportExcel = new ExportExcel();
				exportExcel.setRows(rows);
				// 创建ExportExcel的List集合对象
				List<ExportExcel> exportExcelList = new ArrayList<ExportExcel>();
				exportExcelList.add(exportExcel);

				// 设置导出文件的头部标题
				String[] headers = new String[4];
				headers[0] = new String("银行编号");
				headers[1] = new String("银行名称");
				headers[2] = new String("行别");
				headers[3] = new String("行号");
				
				// 创建workbook
				XSSFWorkbook xssf_w_book = new XSSFWorkbook();
				XSSFRow xssf_w_row = null;// 创建一行
				XSSFCell xssf_w_cell = null;// 创建每个单元格
				XSSFSheet sheet = xssf_w_book.createSheet("线下报盘银行导入结果");

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
								if (exportExcelList.get(i).getRows().get(j)[4].equals("失败")) {
									// 数据显示单元格样式设置

									xssf_w_cell.setCellStyle(cellStyle_errorInfo);

								} else {
									xssf_w_cell.setCellStyle(cellStyle_CN);
								}

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

			}

		}

}
