package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPCardLoanInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long perosnId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	private Long cardLoanId;//卡友贷信息流水号
	
	@NotNull(message = "000001,发卡时间不能为空")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date issuerDate;//发卡时间
	@NotNull(message = "000001,额度不能为空")
	private BigDecimal creditLimit;//额度
	@NotNull(message = "000001,近1个月账单金额不能为空")
	private BigDecimal billAmt1;//近1个月账单金额
	@NotNull(message = "000001,近2个月账单金额不能为空")
	private BigDecimal billAmt2;//近2个月账单金额
	@NotNull(message = "000001,近3个月账单金额不能为空")
	private BigDecimal billAmt3;//近3个月账单金额
	@NotNull(message = "000001,近4个月账单金额不能为空")
	private BigDecimal billAmt4;//近4个月账单金额
	private BigDecimal payMonthAmt;//	月均
	 
	 
	private Long creatorId ; 
	
	public Date getIssuerDate() {
		return issuerDate;
	}

	public void setIssuerDate(Date issuerDate) {
		this.issuerDate = issuerDate;
	}

	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer snapVersion=3;//快照版本号
	

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPerosnId() {
		return perosnId;
	}

	public void setPerosnId(Long perosnId) {
		this.perosnId = perosnId;
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
	
	public Long getCardLoanId() {
		return cardLoanId;
	}

	public void setCardLoanId(Long cardLoanId) {
		this.cardLoanId = cardLoanId;
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVerson() {
		return verson;
	}

	public void setVerson(Integer verson) {
		this.verson = verson;
	}

 
 

}
