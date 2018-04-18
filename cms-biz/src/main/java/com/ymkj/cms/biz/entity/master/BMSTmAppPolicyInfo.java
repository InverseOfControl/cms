package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 BMS_TM_APP_POLICY_INFO
 */
public class BMSTmAppPolicyInfo extends BaseEntity{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5751332816488251438L;
	
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String loanNo;//申请单编号
	private String org;//机构号
	
	private Long policyId;//保单信息流水号
	

	private BigDecimal insuranceAmt;//保险金额

	private Integer insuranceTerm;//保险年限
	
	private Integer paidTerm;//已缴年限
	
	private Date lastPaymentDate;//最近1次缴纳时间
	
	private String paymentMethod;//缴费方式
	
	private String policyRelation;//与被保险人关系
	
	private BigDecimal yearPaymentAmt;//年缴金额
	
	private String policyCheck;//保单真伪核实方式
	
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	private Integer version ;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public BigDecimal getInsuranceAmt() {
		return insuranceAmt;
	}

	public void setInsuranceAmt(BigDecimal insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}

	public Integer getInsuranceTerm() {
		return insuranceTerm;
	}

	public void setInsuranceTerm(Integer insuranceTerm) {
		this.insuranceTerm = insuranceTerm;
	}

	public Integer getPaidTerm() {
		return paidTerm;
	}

	public void setPaidTerm(Integer paidTerm) {
		this.paidTerm = paidTerm;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPolicyRelation() {
		return policyRelation;
	}

	public void setPolicyRelation(String policyRelation) {
		this.policyRelation = policyRelation;
	}

	public BigDecimal getYearPaymentAmt() {
		return yearPaymentAmt;
	}

	public void setYearPaymentAmt(BigDecimal yearPaymentAmt) {
		this.yearPaymentAmt = yearPaymentAmt;
	}

	public String getPolicyCheck() {
		return policyCheck;
	}

	public void setPolicyCheck(String policyCheck) {
		this.policyCheck = policyCheck;
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

}
