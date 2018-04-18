package com.ymkj.cms.biz.api.vo.request.integratedsearch;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

public class ReqQueryLoanDetailVo extends Request{

	private static final long serialVersionUID = 5803514340977644072L;

	public ReqQueryLoanDetailVo(){
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}
	
	@NotEmpty(message="借款编号不能为空")
	private String loanNo;
	
	@NotEmpty(message="用户编码不能为空")
	private String userCode;

	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
