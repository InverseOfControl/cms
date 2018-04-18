package com.ymkj.cms.biz.api.vo.response.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ResLoanReviewVo extends Request {

	private static final long serialVersionUID = 8680071444072706079L;
	
	private String id;
	private String loan_base_id;
	
	public ResLoanReviewVo(){}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoan_base_id() {
		return loan_base_id;
	}
	public void setLoan_base_id(String loan_base_id) {
		this.loan_base_id = loan_base_id;
	}
	
}
