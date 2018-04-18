package com.ymkj.cms.biz.api.vo.request.audit;

import java.io.Serializable;

public class ReqCsRefuseUpdStatusVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanNo;//借款编号
	private Long version;//版本号
	
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
	
	
}
