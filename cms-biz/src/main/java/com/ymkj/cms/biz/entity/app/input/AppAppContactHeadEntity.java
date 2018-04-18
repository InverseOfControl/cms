package com.ymkj.cms.biz.entity.app.input;

import org.apache.commons.lang.ObjectUtils;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppAppContactHeadEntity extends BaseEntity {

	private static final long serialVersionUID = 5995522646568046062L;
	
	private String id;
	private String loanNo;
	private String loanBaseId;
	private String personId;
	private String creator;
	private String createdTime;
	private String creatorId;
	
	public AppAppContactHeadEntity(Long loanBaseId,Long personId,String loanNo,Long creatorId,String creator){
		this.loanBaseId = ObjectUtils.toString(loanBaseId);
		this.personId = ObjectUtils.toString(personId);
		this.loanNo = loanNo;
		this.creatorId = ObjectUtils.toString(creatorId);
		this.creator = creator;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(String loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
}
