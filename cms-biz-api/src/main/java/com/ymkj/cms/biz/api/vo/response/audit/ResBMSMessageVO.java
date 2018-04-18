package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;

public class ResBMSMessageVO implements Serializable{

	/**
	 * 实现自动序列化
	 */
	private static final long serialVersionUID = 8505595513757018454L;
	private String loanNo;//借款单号
	private String repCode; //返回CODE
	private String repMsg;  //返回值
	private Boolean status; //状态
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRepCode() {
		return repCode;
	}
	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}
	public String getRepMsg() {
		return repMsg;
	}
	public void setRepMsg(String repMsg) {
		this.repMsg = repMsg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
	
}
