package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;

public class ResReassignmentUpdVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean ifSuccessful = false; //是否成功
	
	private String loanNo;
	
	private String msg;

	public boolean isIfSuccessful() {
		return ifSuccessful;
	}

	public void setIfSuccessful(boolean ifSuccessful) {
		this.ifSuccessful = ifSuccessful;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
