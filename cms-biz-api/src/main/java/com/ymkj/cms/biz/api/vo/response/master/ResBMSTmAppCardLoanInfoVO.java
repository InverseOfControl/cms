package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSTmAppCardLoanInfoVO implements Serializable{

	private static final long serialVersionUID = 3065141632292366376L;
	

	 private Integer id; //

	 private Integer loanBaseId; //LOAN_BASE_ID

	 private String org; //申请件编号

	 private String appNo; //机构号

	 private String loanNo; //借款编号

	 private Date issuerDate; //发卡时间

	 private BigDecimal creditLimit; //额度

	 private BigDecimal billAmt1; //近1个月账单金额

	 private BigDecimal billAmt2; //近2个月账单金额

	 private BigDecimal billAmt3; //近3个月账单金额

	 private BigDecimal billAmt4; //近4个月账单金额

	 private BigDecimal payMonthAmt; //月均

	 private String creator; //创建用户

	 private Date createdTime; //创建时间

	 private Long creatorId; //创建用户ID

	 private String modifier; //修改用户

	 private Date modifiedTime; //修改时间

	 private Long modifierId; //修改用户id

	 private Integer version; //默认值是1

	 private Integer isDelete; //默认是0,删除是1

	 private Integer snapVersion; //快照版本

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(Integer loanBaseId) {
		this.loanBaseId = loanBaseId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
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

	public Date getIssuerDate() {
		return issuerDate;
	}

	public void setIssuerDate(Date issuerDate) {
		this.issuerDate = issuerDate;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getBillAmt1() {
		return billAmt1;
	}

	public void setBillAmt1(BigDecimal billAmt1) {
		this.billAmt1 = billAmt1;
	}

	public BigDecimal getBillAmt2() {
		return billAmt2;
	}

	public void setBillAmt2(BigDecimal billAmt2) {
		this.billAmt2 = billAmt2;
	}

	public BigDecimal getBillAmt3() {
		return billAmt3;
	}

	public void setBillAmt3(BigDecimal billAmt3) {
		this.billAmt3 = billAmt3;
	}

	public BigDecimal getBillAmt4() {
		return billAmt4;
	}

	public void setBillAmt4(BigDecimal billAmt4) {
		this.billAmt4 = billAmt4;
	}

	public BigDecimal getPayMonthAmt() {
		return payMonthAmt;
	}

	public void setPayMonthAmt(BigDecimal payMonthAmt) {
		this.payMonthAmt = payMonthAmt;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}
	 
	 
	 

}
