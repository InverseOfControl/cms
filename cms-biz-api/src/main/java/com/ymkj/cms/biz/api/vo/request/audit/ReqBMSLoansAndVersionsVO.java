package com.ymkj.cms.biz.api.vo.request.audit;

import java.io.Serializable;

public class ReqBMSLoansAndVersionsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4083110592066808862L;
	private String loanNo;
	private String rtfNodeStatus;
	private String version;
	private String oldAuditPersonCode;//原审核人员
	
	public String getOldAuditPersonCode() {
		return oldAuditPersonCode;
	}
	public void setOldAuditPersonCode(String oldAuditPersonCode) {
		this.oldAuditPersonCode = oldAuditPersonCode;
	}

	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
