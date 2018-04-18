package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqLoanUrgentVO extends Request{
	//营业部ID
	private Long owningBranchId;
	//系统当前对应的年月(借款系统给值)
	private String curttenDate;
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getCurttenDate() {
		return curttenDate;
	}
	public void setCurttenDate(String curttenDate) {
		this.curttenDate = curttenDate;
	}
	
	
}
