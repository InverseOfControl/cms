package com.ymkj.cms.biz.entity.apply;

import java.util.Date;


import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class LoanBaseRelaEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private Long bmsAppPersonInfoId;// 主卡申请人信息表主键
	private Long tmAppContatcInfoId;// 联系人表主键
	private Long tmAppEstateInfoId;// 房产信息表主键
	private Long tmAppProvidentInfoId;// 公积金信息表主键
	private Long tmAppPolicyInfoId;// 保单信息表主键
	private Long tmAppMasterLoanInfoId;// 淘宝达人贷信息表主键
	private Long tmAppMerchanLoanInfoId;// 	淘宝商户贷信息表主键
	private Long tmAppCarInfoId;// 车辆信息表主键
	private Long tmAppSalaryLoanInfoId;// 随薪贷信息表主键
	private Long tmAppCardLoanInfoId;//卡友贷信息表主键
		 
	
    private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;

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

	public Long getBmsAppPersonInfoId() {
		return bmsAppPersonInfoId;
	}

	public void setBmsAppPersonInfoId(Long bmsAppPersonInfoId) {
		this.bmsAppPersonInfoId = bmsAppPersonInfoId;
	}

	

	public Long getTmAppContatcInfoId() {
		return tmAppContatcInfoId;
	}

	public void setTmAppContatcInfoId(Long tmAppContatcInfoId) {
		this.tmAppContatcInfoId = tmAppContatcInfoId;
	}

	public Long getTmAppEstateInfoId() {
		return tmAppEstateInfoId;
	}

	public void setTmAppEstateInfoId(Long tmAppEstateInfoId) {
		this.tmAppEstateInfoId = tmAppEstateInfoId;
	}

	public Long getTmAppProvidentInfoId() {
		return tmAppProvidentInfoId;
	}

	public void setTmAppProvidentInfoId(Long tmAppProvidentInfoId) {
		this.tmAppProvidentInfoId = tmAppProvidentInfoId;
	}

	public Long getTmAppPolicyInfoId() {
		return tmAppPolicyInfoId;
	}

	public void setTmAppPolicyInfoId(Long tmAppPolicyInfoId) {
		this.tmAppPolicyInfoId = tmAppPolicyInfoId;
	}

	public Long getTmAppMasterLoanInfoId() {
		return tmAppMasterLoanInfoId;
	}

	public void setTmAppMasterLoanInfoId(Long tmAppMasterLoanInfoId) {
		this.tmAppMasterLoanInfoId = tmAppMasterLoanInfoId;
	}

	public Long getTmAppMerchanLoanInfoId() {
		return tmAppMerchanLoanInfoId;
	}

	public void setTmAppMerchanLoanInfoId(Long tmAppMerchanLoanInfoId) {
		this.tmAppMerchanLoanInfoId = tmAppMerchanLoanInfoId;
	}

	public Long getTmAppCarInfoId() {
		return tmAppCarInfoId;
	}

	public void setTmAppCarInfoId(Long tmAppCarInfoId) {
		this.tmAppCarInfoId = tmAppCarInfoId;
	}

	public Long getTmAppSalaryLoanInfoId() {
		return tmAppSalaryLoanInfoId;
	}

	public void setTmAppSalaryLoanInfoId(Long tmAppSalaryLoanInfoId) {
		this.tmAppSalaryLoanInfoId = tmAppSalaryLoanInfoId;
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

	public Long getTmAppCardLoanInfoId() {
		return tmAppCardLoanInfoId;
	}

	public void setTmAppCardLoanInfoId(Long tmAppCardLoanInfoId) {
		this.tmAppCardLoanInfoId = tmAppCardLoanInfoId;
	}
	
	

}
