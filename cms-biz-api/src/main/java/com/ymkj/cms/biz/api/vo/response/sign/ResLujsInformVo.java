package com.ymkj.cms.biz.api.vo.response.sign;

import java.io.Serializable;
import java.util.Date;


public class ResLujsInformVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1842448129613084529L;

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
