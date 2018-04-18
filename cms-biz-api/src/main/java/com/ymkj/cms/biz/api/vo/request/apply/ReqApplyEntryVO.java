package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqApplyEntryVO extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long loanBaseId;//借款表ID
	
	private String loanNo;//借款编号

	public Long getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
	

}
