package com.ymkj.cms.web.boss.common;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ExcelUtil {
	
	public static String getCellString(Cell cell){
		String result = "";
		if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                /** 布尔型 **/
            	result = cell.getBooleanCellValue()+"";
                break;
            case Cell.CELL_TYPE_ERROR:
                /** 错误 **/
                break;
            case Cell.CELL_TYPE_FORMULA:
                /** 公式类型 **/
                break;
            case Cell.CELL_TYPE_NUMERIC:
                /** 数值型 **/
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    Date theDate = cell.getDateCellValue();
                    result = DateUtil.defaultFormatDay(theDate);
                } else {
                	result = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                /** 字符串型 **/
            	result = cell.getRichStringCellValue().getString();
                break;
            default:
            }
        }
        return result;
	}

}
