package com.ymkj.cms.biz.entity.master;

public class BMSLoanBank extends BMSProductBaseEntity{

	private Long id;
	private String account;
	private String bankName;
	private String fullName;
	private String bankCode;
	private String branchBankCode;
	private String bankDicType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBranchBankCode() {
		return branchBankCode;
	}
	public void setBranchBankCode(String branchBankCode) {
		this.branchBankCode = branchBankCode;
	}
	public String getBankDicType() {
		return bankDicType;
	}
	public void setBankDicType(String bankDicType) {
		this.bankDicType = bankDicType;
	}
	
	
}
