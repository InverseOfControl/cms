package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppAalaryLoanInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private String org; //机构号
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	
	private String conditionType; //条件类型
	private String creator; //创建人
	private String creatorId; //创建人ID
	
	public AppAalaryLoanInfo(Long loanBaseId,String loanNo,String creator,String creatorId,String org){
		this.loanBaseId = loanBaseId;
		this.loanNo = loanNo;
		this.creator = creator;
		this.creatorId = creatorId;
		this.org = org;
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
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
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
