package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqLoanNumberVO extends Request{
	//借款编号
	private String loanNo;
	//姓名
	private String name;
	//省份证号
	private String idNo;
	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	
}
