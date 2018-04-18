package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPContactInfoVO    implements Serializable {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
 
	private String ifKnowLoan;//是否知晓贷款
	 
	private String contactName;//姓名
 
	private String contactRelation;//与申请人关系
	private String contactCellPhone;//手机号
	private Integer sequenceNum;//排序号
	private String contactEmpName;//公司名称
	private String contactCorpPhone;//公司电话号码
	private String contactCorpPost;//职务
	
 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 

	public Integer getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

 
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	 

	 

	public String getIfKnowLoan() {
		return ifKnowLoan;
	}

	public void setIfKnowLoan(String ifKnowLoan) {
		this.ifKnowLoan = ifKnowLoan;
	}

	public String getContactRelation() {
		return contactRelation;
	}

	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}

	 
	 

	public String getContactCellPhone() {
		return contactCellPhone;
	}

	public void setContactCellPhone(String contactCellPhone) {
		this.contactCellPhone = contactCellPhone;
	}

	public void setContactCorpPost(String contactCorpPost) {
		this.contactCorpPost = contactCorpPost;
	}

	 
	public String getContactEmpName() {
		return contactEmpName;
	}

	public void setContactEmpName(String contactEmpName) {
		this.contactEmpName = contactEmpName;
	}

	public String getContactCorpPhone() {
		return contactCorpPhone;
	}

	public void setContactCorpPhone(String contactCorpPhone) {
		this.contactCorpPhone = contactCorpPhone;
	}

	 
	 

	public String getContactCorpPost() {
		return contactCorpPost;
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

	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;

 
	

}
