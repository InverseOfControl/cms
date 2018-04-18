package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年6月5日
 * @Description:附近下载 req Vo
 */
public class ReqAccessoryVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1791118177043995816L;

	/**
	 * 操作客服工号
	 */
	private String serviceCode;

	/**
	 * 操作客服姓名
	 */
	private String serviceName;

	/**
	 * 操作ip
	 */
	private String ip;

	/**
	 * 借款编号
	 */
	private String loanNo;

	/**
	 * 附件类型
	 */
	private String type;

	/**
	 * 是否批量下载
	 */
	private boolean isBatch;

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

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBatch() {
		return isBatch;
	}

	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}

}
