package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月12日
 * @Description:还款计划导出 request vo
 */
public class ReqRepaymentExpVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6675408948808781693L;

	/**
	 * 批次号
	 */
	private String bacthNUm;
	
	/**
	 * 操作客服工号
	 */
	private String serviceCode;
	
	/**
	 * 操作客服姓名
	 */
	private String serviceName;
	
	/**
	 * 	操作ip
	 */
	private String ip;

	public String getBacthNUm() {
		return bacthNUm;
	}

	public void setBacthNUm(String bacthNUm) {
		this.bacthNUm = bacthNUm;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	


}
