package com.ymkj.cms.biz.api.vo.request.audit;

import java.io.Serializable;

public class ReqCsUpdStatusVo implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 9002508870497489669L;
	private String loanNo;
	private String status;
	private String rtfNodeStatus;
	private String version;
	private String blackCode;//黑名单
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBlackCode() {
		return blackCode;
	}
	public void setBlackCode(String blackCode) {
		this.blackCode = blackCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
