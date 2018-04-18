package com.ymkj.cms.biz.api.vo.request.audit;

import java.io.Serializable;

public class ReqBMSLoansChackVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loanNo;
	private Long version;
	private String oldAuditPersonCode;//原审核人员Code
	private String oldAuditPersonName;//原审核人员Name
	
	
	public String getOldAuditPersonName() {
		return oldAuditPersonName;
	}
	public void setOldAuditPersonName(String oldAuditPersonName) {
		this.oldAuditPersonName = oldAuditPersonName;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getOldAuditPersonCode() {
		return oldAuditPersonCode;
	}
	public void setOldAuditPersonCode(String oldAuditPersonCode) {
		this.oldAuditPersonCode = oldAuditPersonCode;
	}
}
