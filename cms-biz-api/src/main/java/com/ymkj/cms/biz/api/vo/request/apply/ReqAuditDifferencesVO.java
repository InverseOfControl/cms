package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqAuditDifferencesVO   extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loanNo; //借款编号 

	private String version;//1初审对比   2 终审对比
	//排序号
	private String sequenceNum;
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

	public String getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	
}
