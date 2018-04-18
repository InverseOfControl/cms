package com.ymkj.cms.web.boss.exception;

/**
 * 自定义Web端异常编码
 */
public enum BusinessErrorCode {
	
	//请求参数校验, 0开头
	
	//系统错误，1开头
	
	//业务错误，2开头
	
	//服务接口错误, 4开头
	
	;
	
	private String code;

	private String defaultMessage;

	BusinessErrorCode(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public String getErrorCode() {
		return this.code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

}
