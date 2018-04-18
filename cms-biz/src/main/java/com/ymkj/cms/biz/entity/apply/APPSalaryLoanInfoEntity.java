package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPSalaryLoanInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long perosnId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	private Long salaryLoanId;//随薪贷信息流水号
	
	@NotNull(message = "000001,条件类型不能为空")
	private String conditionType;//条件类型
	private Integer ifOwnHouseProperty;//个人名下有房产
	private Integer ifRelativesHouseProperty;//直系亲属有房产
	private Integer ifLocalRegister;//本地户籍
	
	private Long creatorId ; 
	
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
	
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Long getSalaryLoanId() {
		return salaryLoanId;
	}

	public void setSalaryLoanId(Long salaryLoanId) {
		this.salaryLoanId = salaryLoanId;
	}

	 

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public Integer getIfOwnHouseProperty() {
		return ifOwnHouseProperty;
	}

	public void setIfOwnHouseProperty(Integer ifOwnHouseProperty) {
		this.ifOwnHouseProperty = ifOwnHouseProperty;
	}

	public Integer getIfRelativesHouseProperty() {
		return ifRelativesHouseProperty;
	}

	public void setIfRelativesHouseProperty(Integer ifRelativesHouseProperty) {
		this.ifRelativesHouseProperty = ifRelativesHouseProperty;
	}

	public Integer getIfLocalRegister() {
		return ifLocalRegister;
	}

	public void setIfLocalRegister(Integer ifLocalRegister) {
		this.ifLocalRegister = ifLocalRegister;
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
