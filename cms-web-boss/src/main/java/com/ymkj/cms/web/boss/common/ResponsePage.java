package com.ymkj.cms.web.boss.common;

import java.io.Serializable;
import java.util.List;

public class ResponsePage<T> implements Serializable {
	private static final long serialVersionUID = -6200098538119986014L;
	// Fields
	public long total;
	public List<T> rows;
	// Constructors
	/*** default constructor **/
	public ResponsePage() {

	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
