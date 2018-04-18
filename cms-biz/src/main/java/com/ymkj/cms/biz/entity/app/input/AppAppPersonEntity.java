package com.ymkj.cms.biz.entity.app.input;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppAppPersonEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name; 
	private String personNo;
	private String idNo;
	private String idType; 
	private Long creatorId; 
	private String creator; 
	private Date createdTime; 
	private Long modifierId; 
	private String modifier; 
	private Date modifiedTime; 
	private Integer isDelete; 
	private Integer verson;
	
	public AppAppPersonEntity(String personNo, Long creatorId, String creator){
		this.personNo = personNo;
		this.creatorId = creatorId;
		this.creator = creator;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonNo() {
		return personNo;
	}
	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
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
