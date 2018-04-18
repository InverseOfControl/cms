package com.ymkj.cms.biz.common.util.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymkj.cms.biz.common.util.StringUtils;

public class ResultModel {
	
	public static final int SUCCESS_CODE = 000000;
	public static final String SUCCESS_MSG = "接口响应正常！";
	
	public static final int FAIL_CODE = 111111;
	public static final String FAIL_MSG = "接口响应异常！";
	
	/*
	 * 处理状态
	 */
	private int code;
	
	/*
	 * 消息说明
	 */
	private String msg;
	
	/*
	 * 异常消息
	 */
	private String exceptionDesc;
	
	/*
	 * 请求的Url地址
	 */
	private String requestUrl;
	
	/*
	 * 返回的数据集
	 */
	private Object result;
	
	/*
	 * 请求参数，主要是为了记录接口调用时传入参数值
	 */
	private String inPara;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@JsonInclude(Include.NON_NULL)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}
	@JsonIgnore
	public String getExceptionDesc() {
		return exceptionDesc;
	}
	
	@JsonInclude(Include.NON_NULL)
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	@JsonIgnore
	public String getRequestUrl() {
		return requestUrl;
	}

	public void setInPara(String inPara) {
		this.inPara = inPara;
	}
	
	@JsonIgnore
	public String getInPara() {
		return inPara;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		sb.append("测试调用地址："+requestUrl+"\r\n");
		if(StringUtils.isNotBlank(inPara)){
			sb.append("请求参数："+inPara+"\r\n");
		}
		if(code == FAIL_CODE){
			sb.append("异常追踪："+exceptionDesc+"\r\n");
		}
		if(result!=null){
			try {
				sb.append("响应结果："+new ObjectMapper().writeValueAsString(this.getResult())+"\r\n");
			} catch (JsonProcessingException e) {
				sb.append("响应结果：--解析异常--\r\n");
			}
		}
		return sb.toString();
	}
}
