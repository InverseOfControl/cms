package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPProvidentInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	
	private Long providentId;//公积金信息流水号	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
//	@NotNull(message = "000001,开户时间不能为空")
	private Date openAccountDate;//开户时间	
	@NotNull(message = "000001,缴存基数不能为空")
	private BigDecimal depositBase;//	缴存基数	
	@NotNull(message = "000001,缴存比例不能为空")
	private Double depositRate;//		缴存比例	
	@NotNull(message = "000001,月缴存额不能为空")
	private BigDecimal monthDepositAmt;//月缴存额	
	@NotNull(message = "000001,	公积金材料不能为空")
	private String providentInfo;//		公积金材料
	@NotNull(message = "000001,缴纳单位同申请单位不能为空")
	private String paymentUnit;//			缴纳单位同申请单位
	@NotNull(message = "000001,申请单位已缴月数不能为空")
	private Integer paymentMonthNum;//		申请单位已缴月数
	
	private Long creatorId ; 
	
	private String creator ; 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer snapVersion=3;//快照版本号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
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

	public Integer getVerson() {
		return verson;
	}

	public void setVerson(Integer verson) {
		this.verson = verson;
	}

 

}
