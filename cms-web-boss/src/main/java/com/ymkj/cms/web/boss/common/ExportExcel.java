package com.ymkj.cms.web.boss.common;

import java.util.List;

/***
 * 
 * <pre>
 * 导出Excel 包装类
 * </pre>
 *
 * @author lz
 * @version $Id: ExportExcel.java, v 0.1 2017年1月16日  lz Exp $
 */
public class ExportExcel {
	// sheet名
	String sheet;
	// 数据行 第一行为列头
	List<String[]> rows;

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}
}
