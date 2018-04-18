package com.ymkj.cms.biz.api.vo.request.apply;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqReturnedLoanBoxMessageVO extends Request{

	private static final long serialVersionUID = 128671863830366937L;
	
	public ReqReturnedLoanBoxMessageVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	/**
	 * 员工工号
	 */
	@NotEmpty(message="员工工号不能为空")
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
