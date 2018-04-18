package com.ymkj.cms.biz.entity.master;

public class BMSLoanBankExt extends BMSProductBaseEntity{

	private Long id;
	private Long loanBankId; 
	private Long offerBankId;
	private String memo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanBankId() {
		return loanBankId;
	}
	public void setLoanBankId(Long loanBankId) {
		this.loanBankId = loanBankId;
	}
	public Long getOfferBankId() {
		return offerBankId;
	}
	public void setOfferBankId(Long offerBankId) {
		this.offerBankId = offerBankId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
