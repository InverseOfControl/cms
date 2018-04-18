package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPMasterLoanInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	private Long masterLoadId;//淘宝达人贷信息流水号
	
	@NotNull(message = "000001,账户注册时间不能为空")
	private Date acctRegisterDate;//账户注册时间
	@NotNull(message = "000001,买家信用等级不能为空")
	private String buyerCreditLevel;//买家信用等级 
	@NotNull(message = "000001,买家信用类型不能为空")
	private String buyerCreditType;//买家信用类型
	@NotNull(message = "000001,上一年度支出额不能为空")
	private BigDecimal lastYearPayAmt;//上一年度支出额
	@NotNull(message = "000001,好评率不能为空")
	private BigDecimal  goodRate ;//好评率
	@NotNull(message = "000001,芝麻信用分不能为空")
	private Integer  sesameCreditValue ;//芝麻信用分
	@NotNull(message = "000001,近1个月支出额不能为空")
	private BigDecimal payAmt1;//近1个月支出额
	@NotNull(message = "000001,近2个月支出额不能为空")
	private BigDecimal payAmt2;//近2个月支出额
	@NotNull(message = "000001,近3个月支出额不能为空")
	private BigDecimal payAmt3;//近3个月支出额
	@NotNull(message = "000001,月均支出额不能为空")
	private BigDecimal payMonthAmt;//月均支出额
	@NotNull(message = "000001,京东用户等级不能为空")
	private String  jiDongUserLevel ;//京东用户等级
	@NotNull(message = "000001,近一年消费总量不能为空")
	private BigDecimal  pastYearShoppingAmount ;//近一年消费总量[京东]
	@NotNull(message = "000001,小白信用分不能为空")
	private Integer  whiteCreditValue ;//小白信用分
	
	private BigDecimal shoppingAmt1;//近1个月购物额	
	private BigDecimal shoppingAmt2;//近2个月购物额
	private BigDecimal shoppingAmt3;//近3个月购物额	
	private BigDecimal shoppingMonthAmt ;//月均购物额 
	private BigDecimal lastYearShoppingAmt ;//上一年度购物额[淘宝]
	private String  deliveryAddress ;//收货地址 
		 
	
	private String onlineAWithin3MonthsAddress;//网购达人贷 3个月内收货地址   00001 同地址  00002同司址 00003其他
	private String onlineAWithin6MonthsAddress;//网购达人贷 3个月内收货地址    00001 同地址  00002同司址 00003其他
	private String onlineAWithin12MonthsAddress;//网购达人贷 3个月内收货地址  00001 同地址  00002同司址 00003其他
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer snapVersion=3;//快照版本号

	
	
	public String getOnlineAWithin3MonthsAddress() {
		return onlineAWithin3MonthsAddress;
	}

	public void setOnlineAWithin3MonthsAddress(String onlineAWithin3MonthsAddress) {
		this.onlineAWithin3MonthsAddress = onlineAWithin3MonthsAddress;
	}

	public String getOnlineAWithin6MonthsAddress() {
		return onlineAWithin6MonthsAddress;
	}

	public void setOnlineAWithin6MonthsAddress(String onlineAWithin6MonthsAddress) {
		this.onlineAWithin6MonthsAddress = onlineAWithin6MonthsAddress;
	}

	public String getOnlineAWithin12MonthsAddress() {
		return onlineAWithin12MonthsAddress;
	}

	public void setOnlineAWithin12MonthsAddress(String onlineAWithin12MonthsAddress) {
		this.onlineAWithin12MonthsAddress = onlineAWithin12MonthsAddress;
	}

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}

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
	
	public Long getMasterLoadId() {
		return masterLoadId;
	}

	public void setMasterLoadId(Long masterLoadId) {
		this.masterLoadId = masterLoadId;
	}

	public Date getAcctRegisterDate() {
		return acctRegisterDate;
	}

	public void setAcctRegisterDate(Date acctRegisterDate) {
		this.acctRegisterDate = acctRegisterDate;
	}

	 

	public BigDecimal getLastYearPayAmt() {
		return lastYearPayAmt;
	}

	public void setLastYearPayAmt(BigDecimal lastYearPayAmt) {
		this.lastYearPayAmt = lastYearPayAmt;
	}

	public BigDecimal getGoodRate() {
		return goodRate;
	}

	public void setGoodRate(BigDecimal goodRate) {
		this.goodRate = goodRate;
	}

	public Integer getSesameCreditValue() {
		return sesameCreditValue;
	}

	public void setSesameCreditValue(Integer sesameCreditValue) {
		this.sesameCreditValue = sesameCreditValue;
	}

	public BigDecimal getPayAmt1() {
		return payAmt1;
	}

	public void setPayAmt1(BigDecimal payAmt1) {
		this.payAmt1 = payAmt1;
	}

	public BigDecimal getPayAmt2() {
		return payAmt2;
	}

	public void setPayAmt2(BigDecimal payAmt2) {
		this.payAmt2 = payAmt2;
	}

	public BigDecimal getPayAmt3() {
		return payAmt3;
	}

	public void setPayAmt3(BigDecimal payAmt3) {
		this.payAmt3 = payAmt3;
	}

	public BigDecimal getPayMonthAmt() {
		return payMonthAmt;
	}

	public void setPayMonthAmt(BigDecimal payMonthAmt) {
		this.payMonthAmt = payMonthAmt;
	}

	 
	public String getBuyerCreditLevel() {
		return buyerCreditLevel;
	}

	public void setBuyerCreditLevel(String buyerCreditLevel) {
		this.buyerCreditLevel = buyerCreditLevel;
	}

	public String getBuyerCreditType() {
		return buyerCreditType;
	}

	public void setBuyerCreditType(String buyerCreditType) {
		this.buyerCreditType = buyerCreditType;
	}

	public String getJiDongUserLevel() {
		return jiDongUserLevel;
	}

	public void setJiDongUserLevel(String jiDongUserLevel) {
		this.jiDongUserLevel = jiDongUserLevel;
	}

	public BigDecimal getPastYearShoppingAmount() {
		return pastYearShoppingAmount;
	}

	public void setPastYearShoppingAmount(BigDecimal pastYearShoppingAmount) {
		this.pastYearShoppingAmount = pastYearShoppingAmount;
	}

	public Integer getWhiteCreditValue() {
		return whiteCreditValue;
	}

	public void setWhiteCreditValue(Integer whiteCreditValue) {
		this.whiteCreditValue = whiteCreditValue;
	}

	public BigDecimal getShoppingAmt1() {
		return shoppingAmt1;
	}

	public void setShoppingAmt1(BigDecimal shoppingAmt1) {
		this.shoppingAmt1 = shoppingAmt1;
	}

	public BigDecimal getShoppingAmt2() {
		return shoppingAmt2;
	}

	public void setShoppingAmt2(BigDecimal shoppingAmt2) {
		this.shoppingAmt2 = shoppingAmt2;
	}

	public BigDecimal getShoppingAmt3() {
		return shoppingAmt3;
	}

	public void setShoppingAmt3(BigDecimal shoppingAmt3) {
		this.shoppingAmt3 = shoppingAmt3;
	}

	public BigDecimal getShoppingMonthAmt() {
		return shoppingMonthAmt;
	}

	public void setShoppingMonthAmt(BigDecimal shoppingMonthAmt) {
		this.shoppingMonthAmt = shoppingMonthAmt;
	}

	public BigDecimal getLastYearShoppingAmt() {
		return lastYearShoppingAmt;
	}

	public void setLastYearShoppingAmt(BigDecimal lastYearShoppingAmt) {
		this.lastYearShoppingAmt = lastYearShoppingAmt;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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
