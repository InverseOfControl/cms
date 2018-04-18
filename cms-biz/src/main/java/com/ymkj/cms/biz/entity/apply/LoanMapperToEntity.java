package com.ymkj.cms.biz.entity.apply;

import java.io.Serializable;
import java.util.Date;

public class LoanMapperToEntity implements Serializable{

	private String createTime;
	private String id;
	private String loanNo;
	private String loanState;
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	
	
}
