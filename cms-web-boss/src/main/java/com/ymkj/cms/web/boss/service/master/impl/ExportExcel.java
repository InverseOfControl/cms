package com.ymkj.cms.web.boss.service.master.impl;

import java.util.List;

public class ExportExcel {
	//sheet名
	String sheet;
	//数据行 
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
