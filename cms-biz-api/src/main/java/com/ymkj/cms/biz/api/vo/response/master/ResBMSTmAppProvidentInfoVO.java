package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSTmAppProvidentInfoVO implements Serializable{

	private static final long serialVersionUID = -7949949497764380601L;
	
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	
	private Long providentId;//公积金信息流水号	
	
	private Date openAccountDate;//开户时间	
	
	private BigDecimal depositBase;//	缴存基数	
	
	private Double depositRate;//		缴存比例	
	
	private BigDecimal monthDepositAmt;//月缴存额	
	
	private String providentInfo;//		公积金材料
	
	private String paymentUnit;//			缴纳单位同申请单位
	
	private Integer paymentMonthNum;//		申请单位已缴月数
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer version ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Long getProvidentId() {
		return providentId;
	}

	public void setProvidentId(Long providentId) {
		this.providentId = providentId;
	}

	public Date getOpenAccountDate() {
		return openAccountDate;
	}

	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
	}

	public BigDecimal getDepositBase() {
		return depositBase;
	}

	public void setDepositBase(BigDecimal depositBase) {
		this.depositBase = depositBase;
	}

	public Double getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(Double depositRate) {
		this.depositRate = depositRate;
	}

	public BigDecimal getMonthDepositAmt() {
		return monthDepositAmt;
	}

	public void setMonthDepositAmt(BigDecimal monthDepositAmt) {
		this.monthDepositAmt = monthDepositAmt;
	}

	public String getProvidentInfo() {
		return providentInfo;
	}

	public void setProvidentInfo(String providentInfo) {
		this.providentInfo = providentInfo;
	}

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public Integer getPaymentMonthNum() {
		return paymentMonthNum;
	}

	public void setPaymentMonthNum(Integer paymentMonthNum) {
		this.paymentMonthNum = paymentMonthNum;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
