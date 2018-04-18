package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSAduitPersonVo extends Request{

	/**
	 * 序列化对象
	 */
	private static final long serialVersionUID = 8875230681477999L;

	private String loanNo;//借款单号
	
	
	
	public ReqBMSAduitPersonVo() {
		super();
	}
	public ReqBMSAduitPersonVo(String sysCode) {
		this.setSysCode(sysCode);
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
}
