package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppCardLoanInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String org; //机构号
	
	private String issuerData; //发卡时间
	private String creditLimit; //额度
	private String billAmt1; //购买时间
	private String billAmt2; //裸车价
	private String billAmt3; //购买价
	private String billAmt4; //贷款剩余期数
	
	private String creator;
	private String creatorId;
	
	public AppCardLoanInfo(Long loanBaseId, String loanNo, String org, String creator, String creatorId){
		this.loanBaseId = loanBaseId;
		this.loanNo = loanNo;
		this.org = org;
		this.creator = creator;
		this.creatorId = creatorId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getIssuerData() {
		return issuerData;
	}
	public void setIssuerData(String issuerData) {
		this.issuerData = issuerData;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getBillAmt1() {
		return billAmt1;
	}
	public void setBillAmt1(String billAmt1) {
		this.billAmt1 = billAmt1;
	}
	public String getBillAmt2() {
		return billAmt2;
	}
	public void setBillAmt2(String billAmt2) {
		this.billAmt2 = billAmt2;
	}
	public String getBillAmt3() {
		return billAmt3;
	}
	public void setBillAmt3(String billAmt3) {
		this.billAmt3 = billAmt3;
	}
	public String getBillAmt4() {
		return billAmt4;
	}
	public void setBillAmt4(String billAmt4) {
		this.billAmt4 = billAmt4;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

}
