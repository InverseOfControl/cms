package com.ymkj.cms.biz.api.vo.request.app;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600001 extends Request{
	
	private static final long serialVersionUID = -4693205736573973772L;
	
	private String appCurrentTime;
	
	private String loanNo;
	
	private String serviceCode;
	
	private String serviceName;
	
	public String getAppCurrentTime() {
		return appCurrentTime;
	}

	public void setAppCurrentTime(String appCurrentTime) {
		this.appCurrentTime = appCurrentTime;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
