package com.ymkj.cms.web.boss.exception;

import java.util.List;

/**
 * <pre>
 * excel上传下载右下角显示信息之用
 * </pre>
 *
 * @author lz
 * @version $Id: ExcelException.java, v 0.1 2017年1月16日 lz Exp $
 */
public class ExcelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;

	private String[] params;

	private String message;

	public ExcelException() {

	}

	public ExcelException(String errCode) {
		this.errCode = errCode;
		if (message == null) {
			this.message = errCode;
		}
	}

	public ExcelException(String errCode, List<String> params) {
		this.errCode = errCode;
		this.params = (String[]) params.toArray();
		if (message == null) {
			this.message = errCode;
		}
	}

	public ExcelException(String errCode, String... params) {
		this.errCode = errCode;
		this.params = params;
		if (message == null) {
			this.message = errCode;
		}
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
		if (message == null) {
			this.message = errCode;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
}