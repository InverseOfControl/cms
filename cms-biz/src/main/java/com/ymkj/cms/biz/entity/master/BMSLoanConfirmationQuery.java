package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSLoanConfirmationQuery extends BaseEntity{

	private Long id;
	private Double accLmt;
	private Double pactMoney;
	private String loanNo;
	private String contractBranchId;
	private String contractBranch;
	private Date applyDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(Double accLmt) {
		this.accLmt = accLmt;
	}
	public Double getPactMoney() {
		return pactMoney;
	}
	public void setPactMoney(Double pactMoney) {
		this.pactMoney = pactMoney;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	
}
