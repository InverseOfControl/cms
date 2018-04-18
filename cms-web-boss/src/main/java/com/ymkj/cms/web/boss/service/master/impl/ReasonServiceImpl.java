package com.ymkj.cms.web.boss.service.master.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.web.boss.service.master.IReasonService;

@Service
public class ReasonServiceImpl implements IReasonService {

	@Override
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile, String ss) {
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
			
			if (ss.equals("取消")) {
				List<ReqBMSReasonVO> cancelReasonList = getCancelReasonList(wb, Mfile);
				map.put("cancelReasonList", cancelReasonList);
			} else if (ss.equals("退回")) {
				List<ReqBMSReasonVO> returnReasonList = getReturnReasonList(wb, Mfile);
				map.put("returnReasonList", returnReasonList);
			} else if (ss.equals("拒绝")) {
				List<ReqBMSReasonVO> rejectReasonList = getRejectReasonList(wb, Mfile);
				map.put("rejectReasonList", rejectReasonList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 解析excel工作表,得到取消原因集合list
	 * 
	 * @param wb
	 * @param Mfile
	 * @return
	 */
	public List<ReqBMSReasonVO> getCancelReasonList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSReasonVO> cancelReasonList = new ArrayList<ReqBMSReasonVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss = 0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("取消原因")) {
				sheet = wb.getSheetAt(i);
			} else {
				ss++;
			}
		}
		if (sheet == null && ss != 0) {
			sheet = wb.getSheetAt(0);
		}
		String UReason = null;// 定义字段来接收二级原因的上一级原因
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			int sNum = 0;
			String failureCause = "";
			if (i != 0) {
				if (null != row && row.getFirstCellNum() > -1) {
					int rowNum = row.getLastCellNum();
					ReqBMSReasonVO cancelReasonVo = new ReqBMSReasonVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 一级原因
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setTheFirstCellReason(cell.getStringCellValue().toString());
									UReason = cancelReasonVo.getTheFirstCellReason();
								}
							} else if (c == 1) {// 二级原因
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setReason(cell.getStringCellValue().toString());
									cancelReasonVo.setTheUpperLevelReason(UReason);
									/*
									 * System.out.println(
									 * "returnReasonVo.getReason():"+
									 * returnReasonVo.getReason());
									 */
								}
							
							} else if (c == 2) {// 限制再申请天数
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setCanRequestDays(cell.getStringCellValue().toString());
								}

							} else if (c == 3) {// 是否属于申请录入操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setShenQingLuRu(cell.getStringCellValue().toString());
								}
							} else if (c == 4) {// 是否属于录入复核操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setLuRuFuHe(cell.getStringCellValue().toString());
								}
							} else if (c == 5) {// 是否属于信审初审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setXinShenChuShen(cell.getStringCellValue().toString());
								}
							} else if (c == 6) {// 是否属于信审终审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setXinShenZhongShen(cell.getStringCellValue().toString());
								}
							} else if (c == 7) {// 是否属于合同签约操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setHeTonQianYue(cell.getStringCellValue().toString());
								}
							} else if (c == 8) {// 是否属于合同确认操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setHeTonQueRen(cell.getStringCellValue().toString());
								}
							} else if (c == 9) {// 是否属于放款审核操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setFangKuanShenHe(cell.getStringCellValue().toString());
								}
							} else if (c == 10) {// 是否属于放款确认操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setFangKuanQueRen(cell.getStringCellValue().toString());
								}
							} else if (c == 11) {// 备注
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setRemark(cell.getStringCellValue().toString());
								} else {
									cancelReasonVo.setRemark("");
								}
							}else if (c == 12) {// 备注
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									cancelReasonVo.setCode(cell.getStringCellValue().toString());
								} else {
									cancelReasonVo.setCode("");
								}
							}

							/* cancelReasonVo.setFailureCause(failureCause); */
						}
					}
					/*
					 * if (sNum > 0) { cancelReasonVo.setDerivedResult("失败"); }
					 * else { cancelReasonVo.setDerivedResult("成功"); }
					 */
					cancelReasonList.add(cancelReasonVo);
				}
			}
		}

		return cancelReasonList;

	}

	/**
	 * 解析excel工作表,得到退回原因集合list
	 * 
	 * @param wb
	 * @param Mfile
	 * @return
	 */
	public List<ReqBMSReasonVO> getReturnReasonList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSReasonVO> returnReasonList = new ArrayList<ReqBMSReasonVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss = 0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("退回原因")) {
				sheet = wb.getSheetAt(i);
			} else {
				ss++;
			}
		}
		if (sheet == null && ss != 0) {
			sheet = wb.getSheetAt(0);
		}
		int sheetMergeCount = sheet.getNumMergedRegions();
		/* System.out.println("sheet中和合并单元格的个数:"+sheetMergeCount); */
		String UReason = null;// 定义字段来接收二级原因的上一级原因
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			int sNum = 0;
			String failureCause = "";
			if (i != 0) {
				if (null != row && row.getLastCellNum() > -1) {
					int rowNum = row.getLastCellNum();
					ReqBMSReasonVO returnReasonVo = new ReqBMSReasonVO();

					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 一级原因
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setTheFirstCellReason(cell.getStringCellValue().toString());
									UReason = returnReasonVo.getTheFirstCellReason();
								}
							} else if (c == 1) {// 二级原因
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setReason(cell.getStringCellValue().toString());
									returnReasonVo.setTheUpperLevelReason(UReason);
									/*
									 * System.out.println(
									 * "returnReasonVo.getReason():"+
									 * returnReasonVo.getReason());
									 */
								}
							} else if (c == 2) {// 是否属于申请录入操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setShenQingLuRu(cell.getStringCellValue().toString());
								}
							} else if (c == 3) {// 是否属于录入复核操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setLuRuFuHe(cell.getStringCellValue().toString());
								}
							} else if (c == 4) {// 是否属于信审初审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setXinShenChuShen(cell.getStringCellValue().toString());
								}
							/*} else if (c == 5) {// 是否属于信审终审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setXinShenZhongShen(cell.getStringCellValue().toString());
								}*/
							} else if (c == 5) {// 是否属于信审终审退回门店操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setZhongShenReturnMD((cell.getStringCellValue().toString()));;
								}
							}  else if (c == 6) {// 是否属于信审终审退回初审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setZhongShenReturnCS((cell.getStringCellValue().toString()));;
								}
								
							} else if (c == 7) {// 是否属于合同签约操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setHeTonQianYue(cell.getStringCellValue().toString());
								}
							} else if (c == 8) {// 是否属于合同确认操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setHeTonQueRen(cell.getStringCellValue().toString());
								}
							} else if (c == 9) {// 是否属于放款审核操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setFangKuanShenHe(cell.getStringCellValue().toString());
								}
							} else if (c == 10) {// 是否属于放款确认操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setFangKuanQueRen(cell.getStringCellValue().toString());
								}
							} else if(c==11){ //初审挂起
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setChuShenGuaQi((cell.getStringCellValue().toString()));;
								}
							}else if(c==12){  //终审挂起
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setZhongShenGuaQi((cell.getStringCellValue().toString()));;
								}
							}else if (c == 13) {// 备注
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setRemark(cell.getStringCellValue().toString());
								} else {
									returnReasonVo.setRemark("");
								}
							}else if(c==14){ //CODE
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									returnReasonVo.setCode(cell.getStringCellValue().toString());
								} else {
									returnReasonVo.setCode("");
								}
							}

							/* cancelReasonVo.setFailureCause(failureCause); */
						}
					}
					/*
					 * if (sNum > 0) { cancelReasonVo.setDerivedResult("失败"); }
					 * else { cancelReasonVo.setDerivedResult("成功"); }
					 */
					returnReasonList.add(returnReasonVo);
				}
			}
		}

		return returnReasonList;

	}

	/**
	 * 解析excel工作表,得到拒绝原因集合list
	 * 
	 * @param wb
	 * @param Mfile
	 * @return
	 */
	public List<ReqBMSReasonVO> getRejectReasonList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSReasonVO> rejectReasonList = new ArrayList<ReqBMSReasonVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss = 0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("拒绝原因")) {
				sheet = wb.getSheetAt(i);
			} else {
				ss++;
			}
		}
		if (sheet == null && ss != 0) {
			sheet = wb.getSheetAt(0);
		}
		int sheetMergeCount = sheet.getNumMergedRegions();
		/* System.out.println("sheet中和合并单元格的个数:"+sheetMergeCount); */
		String UReason = null;// 定义字段来接收二级原因的上一级原因
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			int sNum = 0;
			String failureCause = "";
			if (i != 0) {
				if (null != row && row.getLastCellNum() > -1) {
					int rowNum = row.getLastCellNum();
					ReqBMSReasonVO rejectReasonVo = new ReqBMSReasonVO();

					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 一级原因
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setTheFirstCellReason(cell.getStringCellValue().toString());
									UReason = rejectReasonVo.getTheFirstCellReason();
								}
							} else if (c == 1) {// 二级原因
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setReason(cell.getStringCellValue().toString());
									rejectReasonVo.setTheUpperLevelReason(UReason);
								}
							}else if(c == 2){//限制再申请天数
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setCanRequestDays(cell.getStringCellValue().toString());
								}
							} else if (c == 3) {// 是否属于申请录入操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setShenQingLuRu(cell.getStringCellValue().toString());
								}
							} else if (c == 4) {// 是否属于录入复核操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setLuRuFuHe(cell.getStringCellValue().toString());
								}
							} else if (c == 5) {// 是否属于信审初审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setXinShenChuShen(cell.getStringCellValue().toString());
								}
							} else if (c == 6) {// 是否属于信审终审操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setXinShenZhongShen(cell.getStringCellValue().toString());
								}
							}else if(c==7){   //申请件信息维护
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if(!cell.getStringCellValue().isEmpty()){
									rejectReasonVo.setSqjxiwh(cell.getStringCellValue().toString());
								}
							}else if (c == 8) {// 是否属于合同签约操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setHeTonQianYue(cell.getStringCellValue().toString());
								}
							} else if (c == 9) {// 是否属于合同确认操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setHeTonQueRen(cell.getStringCellValue().toString());
								}
							} else if (c == 10) {// 是否属于放款审核操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setFangKuanShenHe(cell.getStringCellValue().toString());
								}
							} else if (c == 11) {// 是否属于放款确认操作模块
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setFangKuanQueRen(cell.getStringCellValue().toString());
								}
							} else if (c == 12) {// 备注
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setRemark(cell.getStringCellValue().toString());
								} else {
									rejectReasonVo.setRemark("");
								}
						   } else if(c==13){  //CODE
							   cell.setCellType(Cell.CELL_TYPE_STRING);
							   if (!cell.getStringCellValue().isEmpty()) {
									rejectReasonVo.setCode(cell.getStringCellValue().toString());
								} else {
									rejectReasonVo.setCode("");
								}
						   }

							/* cancelReasonVo.setFailureCause(failureCause); */
						}
					}
					/*
					 * if (sNum > 0) { cancelReasonVo.setDerivedResult("失败"); }
					 * else { cancelReasonVo.setDerivedResult("成功"); }
					 */
					rejectReasonList.add(rejectReasonVo);
				}
			}
		}

		return rejectReasonList;

	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 判断合并了行
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean isMergedRow(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell) {

		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}

	// 判断传进来的文件类型
	public boolean isExcel2007(String fileName) {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return false;
		} else if ("xlsx".equals(extension)) {
			return true;
		}
		return false;

	}

}
