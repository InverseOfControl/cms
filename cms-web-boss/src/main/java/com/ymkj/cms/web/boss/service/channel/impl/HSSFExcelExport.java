package com.ymkj.cms.web.boss.service.channel.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.ymkj.cms.biz.api.constant.BmsConstant;
import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;
import com.ymkj.cms.web.boss.common.ExcelUtil;
import com.ymkj.cms.web.boss.common.NumberUtil;
import com.ymkj.cms.web.boss.common.StringUtils;
import com.ymkj.cms.web.boss.service.channel.IExcelExport;

@Service
public class HSSFExcelExport implements IExcelExport {

	public void createLoanCheckExcel(HttpServletResponse response, List<?> datas, Map<String, String[]> fieldMap,
			Class<?> objClass, ExcelObjVo excelVo) throws Exception {
		Workbook workBook=buildWork(datas, fieldMap, objClass, excelVo);
		wirteExcel(workBook, response, excelVo.getExcFileName());

	}

	@Override
	public void createLoanExcel(HttpServletResponse response, List<?> datas, Map<String, String[]> fieldMap,
			Class<?> objClass, ExcelObjVo excelVo) throws Exception {
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet();
		String fieldNames[] = fieldMap.get("fieldNames");
		String fieldCodes[] = fieldMap.get("fieldCodes");
		Row row01 = sheet.createRow(0);
		Cell cell01 = row01.createCell(0);
		cell01.setCellValue("证大投资咨询有限公司放款通知书");
		Row row02 = sheet.createRow(1);
		Cell cell02 = row02.createCell(0);
		cell02.setCellValue("申明：以下批次贷款我公司已按照内部审批程序完成审核工作，同意推荐发放，并同意为该笔贷款提供代偿。");
		Row row03 = sheet.createRow(2);
		Cell cell03 = row03.createCell(0);
		cell03.setCellValue("上海证大投资咨询有限公司");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, fieldCodes.length - 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, fieldCodes.length - 1));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, fieldCodes.length - 1));
		buildHead(fieldNames, sheet.createRow(excelVo.getBegRow() - 1));
		buildBody(sheet, datas, fieldMap, objClass, fieldCodes, excelVo);
		wirteExcel(workBook, response, excelVo.getExcFileName());

	}

	@Override
	public void createtransferAppExcel(HttpServletResponse response, String tmpPath, Map<String, Object> map,
			ExcelObjVo excelVo) throws Exception {
		InputStream instream = this.getClass().getResourceAsStream(tmpPath);
		Workbook workbook = new HSSFWorkbook(instream);
		// 放款笔数
		Integer quantity = Integer.parseInt(String.valueOf(map.get("quantity")));
		// 贷款本金总额
		BigDecimal money = new BigDecimal(String.valueOf((map.get("money") == null ? 0.0 : map.get("money"))));
		// 调减金额
		BigDecimal diffMoney = (BigDecimal) map.get("diffMoney");
		// 系统日期
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		String requestDate = year + "-" + month + "-" + day;
		String stampDate = year + "年" + month + "月" + day + "日";

		// 金额格式化
		String loanMoney = NumberUtil.formatAmount(money);
		// 实际申请金额
		String applyMoney = NumberUtil.formatAmount(money.subtract(diffMoney));
		Sheet sheet = workbook.getSheetAt(0);
		// 设置申请时间
		Cell cell = sheet.getRow(4).getCell(0);
		cell.setCellValue(requestDate);
		// 设置放款本金
		cell = sheet.getRow(16).getCell(1);
		cell.setCellValue(loanMoney);
		// 设置放款笔数
		cell = sheet.getRow(16).getCell(2);
		cell.setCellValue(quantity.toString());
		// 设置调减金额
		cell = sheet.getRow(16).getCell(3);
		cell.setCellValue(NumberUtil.formatAmount(diffMoney));
		// 设置实际申请金额
		cell = sheet.getRow(16).getCell(4);
		cell.setCellValue(applyMoney);
		// 设置盖章时间
		cell = sheet.getRow(30).getCell(2);
		cell.setCellValue(stampDate);
		// 设置复核确认时间
		cell = sheet.getRow(37).getCell(2);
		cell.setCellValue(stampDate);
		wirteExcel(workbook, response, excelVo.getExcFileName());
	}

	public Workbook buildWork(List<?> datas, Map<String, String[]> fieldMap,
			Class<?> objClass, ExcelObjVo excelVo) throws Exception {
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet();
		String fieldNames[] = fieldMap.get("fieldNames");
		String fieldCodes[] = fieldMap.get("fieldCodes");
		buildHead(fieldNames, sheet.createRow(excelVo.getBegRow()-1));
		buildBody(sheet, datas, fieldMap, objClass, fieldCodes, excelVo);
		return workBook;

	}
	
	public Workbook buildWork(Workbook workBook,List<?> datas, Map<String, String[]> fieldMap,
			Class<?> objClass, ExcelObjVo excelVo) throws Exception {
		Sheet sheet = workBook.getSheetAt(0);
		String fieldNames[] = fieldMap.get("fieldNames");
		String fieldCodes[] = fieldMap.get("fieldCodes");
		buildHead(fieldNames, sheet.createRow(excelVo.getBegRow()-1));
		buildBody(sheet, datas, fieldMap, objClass, fieldCodes, excelVo);
		return workBook;

	}

	public Workbook buildWorkFormat(List<?> datas, Map<String, String[]> fieldMap,
			Class<?> objClass, ExcelObjVo excelVo) throws Exception {
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet();
		String fieldNames[] = fieldMap.get("fieldNames");
		String fieldCodes[] = fieldMap.get("fieldCodes");
		
		buildHead(fieldNames, sheet.createRow(excelVo.getBegRow()-1));
		
		buildBodyFormat(sheet, datas, fieldMap, objClass, fieldCodes, excelVo);
		
		return workBook;

	}
	/**
	 * 数据构建
	 * @param sheet
	 * @param datas
	 * @param fieldMap
	 * @param objClass
	 * @param excelVo
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void buildBody(Sheet sheet, List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass,
			String[] fieldCodes, ExcelObjVo excelVo)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field.setAccessible(objClass.getDeclaredFields(), true);
		for (int i = 0; i < datas.size(); i++) {
			Row row = sheet.createRow(excelVo.getBegRow() + i);
			for (int j = 0; j < fieldCodes.length; j++) {
				Cell cell = row.createCell(excelVo.getBegCell() + j);
				Field field = objClass.getDeclaredField(fieldCodes[j]);
				if(field==null){
					System.out.println(fieldCodes[j]);
					continue;
				}
				field.setAccessible(true);
				String data = String.valueOf(field.get(datas.get(i)));
				cell.setCellValue(data);
			}
		}
	}
	
	/**
	 * 数据构建并格式化（&dm 数据类型为decimal）
	 * @param sheet
	 * @param datas
	 * @param fieldMap
	 * @param objClass
	 * @param excelVo
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void buildBodyFormat(Sheet sheet, List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass,
			String[] fieldCodes, ExcelObjVo excelVo)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field.setAccessible(objClass.getDeclaredFields(), true);
		for (int i = 0; i < datas.size(); i++) {
			Row row = sheet.createRow(excelVo.getBegRow() + i);
			for (int j = 0; j < fieldCodes.length; j++) {
				String data =null;
				Cell cell = row.createCell(excelVo.getBegCell() + j);
				String fileCodeDataType=fieldCodes[j];
				String fileCode=null;
				String dataType =null;
				if(fileCodeDataType.indexOf("&")>0){
					fileCode=fileCodeDataType.split("&")[0];
					dataType=fileCodeDataType.split("&")[1];
				}else{
					fileCode=fileCodeDataType;
				}
				Field field = objClass.getDeclaredField(fileCode);
				if(field==null){
					System.out.println(fieldCodes[j]);
					continue;
				}
				field.setAccessible(true);
				if("dm".equals(dataType)){
					 data = field.get(datas.get(i))==null?"0.00":String.valueOf(field.get(datas.get(i)));
				}else{
					 data = String.valueOf(field.get(datas.get(i)));
				}
				cell.setCellValue(data);
			}
		}
	}

	/**
	 * 表头构建
	 * 
	 * @param fieldNames
	 * @param row
	 */
	public static void buildHead(String[] fieldNames, Row row) {
		for (int i = 0; i < fieldNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(fieldNames[i]);
		}
	}

	/**
	 * 生成excel 文件
	 * 
	 * @param work
	 * @param response
	 * @param fileName
	 * @throws IOException
	 */
	public  void wirteExcel(Workbook work, HttpServletResponse response, String fileName) throws IOException {
		fileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		response.setCharacterEncoding("utf-8");
		response.setContentType("octets/stream");//"plication/vnd.ms-excel"
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		OutputStream ouputStream = new BufferedOutputStream(response.getOutputStream());
		work.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@Override
	public List<Map<String, String>> importOfferExcel(InputStream inputStream) throws Exception {
		List<Map<String,String>> dataList=new ArrayList<Map<String,String>>();
		Map<String,String> obj=null;
		Workbook workBook=new HSSFWorkbook(inputStream);
		Sheet sheet=workBook.getSheetAt(0);
		for(int i=sheet.getLastRowNum();i>2;i--){
			Row row=sheet.getRow(i);
			if(row!=null){
				obj=new HashMap<String,String>();
				Cell cell01=row.getCell(18);
				Cell cell02=row.getCell(19);
				Cell cell03=row.getCell(20);
				obj.put("remark",cell01.getStringCellValue());
				obj.put("feedbackCode",cell02.getStringCellValue());
				obj.put("reason",cell03.getStringCellValue());
			}
			dataList.add(obj);
		}
		return dataList;
	}

	@Override
	public List<ReqImportExcelVO> importExcel(Workbook workBook) throws Exception {
		// TODO Auto-generated method stub
		List<ReqImportExcelVO> dataList=new ArrayList<ReqImportExcelVO>();
		Sheet sheet=workBook.getSheetAt(0);
		for(int i=3;i<=sheet.getLastRowNum();i++){
			Row row=sheet.getRow(i);
			if(row!=null){
				ReqImportExcelVO lxxdExcelVo = new ReqImportExcelVO();
				lxxdExcelVo.setId(ExcelUtil.getCellString(row.getCell(0)));
				lxxdExcelVo.setContractNum(ExcelUtil.getCellString(row.getCell(1)));
				lxxdExcelVo.setBorrowerName(ExcelUtil.getCellString(row.getCell(2)));
				lxxdExcelVo.setIdNum(ExcelUtil.getCellString(row.getCell(3)));
				lxxdExcelVo.setRequestPlace(ExcelUtil.getCellString(row.getCell(4)));
				lxxdExcelVo.setLoanDate(ExcelUtil.getCellString(row.getCell(5)));
				lxxdExcelVo.setTime(ExcelUtil.getCellString(row.getCell(6)));
				String pactMoney = ExcelUtil.getCellString(row.getCell(7));
				lxxdExcelVo.setPactMoney(StringUtils.isNotBlank(pactMoney) ? new BigDecimal(pactMoney) : null);
				lxxdExcelVo.setResult(ExcelUtil.getCellString(row.getCell(8)));
				lxxdExcelVo.setDesc(ExcelUtil.getCellString(row.getCell(9)));
				dataList.add(lxxdExcelVo);
			}
		}
		return dataList;
	}
	@Override
	public void addLxxdDataToExcel(Workbook work, List<ReqImportExcelVO> excelVO)
			throws Exception {
		// TODO Auto-generated method stub
		CellStyle cellStyle =work.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置字体居中
		Sheet sheet = work.getSheetAt(0);
		sheet.setColumnWidth(10, BmsConstant.FEED_BACK.getBytes().length*512);//设置第10列宽度自适应
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				if (i == 2) {
					// 追加反馈结果一栏
					Cell tailCell = row.createCell(row.getLastCellNum());
					tailCell.setCellValue(BmsConstant.FEED_BACK);
					tailCell.setCellStyle(cellStyle);
				} else {
					// 追加反馈结果内容
					row.createCell(row.getLastCellNum()).setCellValue(excelVO.get(i - 3).getFeedBack());
				}

			}
		}

	}

	public static void main(String[] args) {
		String fileCode=null;
		String dataType=null;
		String test ="ewrqrqwer";
		System.out.println(test.indexOf("&"));
		if(test.indexOf("&")>0){
			fileCode=test.split("&")[0];
			dataType=test.split("&")[1];
		}
		System.out.println(fileCode+"#"+dataType);
	}
}
