package com.ymkj.cms.biz.entity.sign;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSLuJSInform extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1598019049722660610L;
	private String lujsApplyNo;
	private String loanNo;
	private String informType;
	private String informResult;
	private String informDesc;
	private Date informTime;
	public String getLujsApplyNo() {
		return lujsApplyNo;
	}
	public void setLujsApplyNo(String lujsApplyNo) {
		this.lujsApplyNo = lujsApplyNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getInformType() {
		return informType;
	}
	public void setInformType(String informType) {
		this.informType = informType;
	}
	public String getInformResult() {
		return informResult;
	}
	public void setInformResult(String informResult) {
		this.informResult = informResult;
	}
	public String getInformDesc() {
		return informDesc;
	}
	public void setInformDesc(String informDesc) {
		this.informDesc = informDesc;
	}
	public Date getInformTime() {
		return informTime;
	}
	public void setInformTime(Date informTime) {
		this.informTime = informTime;
	}
	
}
