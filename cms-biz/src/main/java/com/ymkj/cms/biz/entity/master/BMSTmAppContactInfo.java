package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 BMS_TM_APP_CONTACT_INFO
 */
public class BMSTmAppContactInfo extends BaseEntity {

 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5900174738383836515L;
	
	private Integer id; //

	
	 private Integer headId; //联系人主表id

	
	 private Integer loanBaseId; //LOAN_BASE_ID

	
	 private Integer personId; //

	 
	 private String appNo; //申请件编号

	
	 private String loanNo; //借款编号

	
	 private String org; //机构号

	
	 private Integer contactId; //联系人流水

	
	 private Integer sequenceNum; //排序号

	
	 private String ifKnowLoan; //是否知晓贷款

	
	 private String contactName; //姓名

	
	 private String contactRelation; //与申请人关系

	
	 private String contactGender; //性别

	
	 private String contactCellphone; //手机号

	
	 private String contactCellphone1; //手机号2

	
	 private String contactIdNo; //身份证号码

	
	 private String contactEmpName; //公司名称

	
	 private String contactCorpPhone; //公司电话号码

	
	 private String contactCorpPhone1; //公司电话号码2


	 private String contactCorpFax; //公司传真

	
	 private String contactCorpPost; //职务

	 
	 private String creator; //创建用户

	
	 private Date createdTime; //创建时间

	
	 private Long creatorId; //创建用户ID

	
	 private String modifier; //修改用户

	 
	 private Date modifiedTime; //修改时间

	 
	 private Long modifierId; //修改用户id

	
	 private Integer version; //默认值是1

	
	 private Integer isDelete; //默认是0,删除是1

	
	 private Integer snapVersion; //快照版本号


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getHeadId() {
		return headId;
	}


	public void setHeadId(Integer headId) {
		this.headId = headId;
	}


	public Integer getLoanBaseId() {
		return loanBaseId;
	}


	public void setLoanBaseId(Integer loanBaseId) {
		this.loanBaseId = loanBaseId;
	}


	public Integer getPersonId() {
		return personId;
	}


	public void setPersonId(Integer personId) {
		this.personId = personId;
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


	public Integer getContactId() {
		return contactId;
	}


	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}


	public Integer getSequenceNum() {
		return sequenceNum;
	}


	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}


	public String getIfKnowLoan() {
		return ifKnowLoan;
	}


	public void setIfKnowLoan(String ifKnowLoan) {
		this.ifKnowLoan = ifKnowLoan;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getContactRelation() {
		return contactRelation;
	}


	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}


	public String getContactGender() {
		return contactGender;
	}


	public void setContactGender(String contactGender) {
		this.contactGender = contactGender;
	}


	public String getContactCellphone() {
		return contactCellphone;
	}


	public void setContactCellphone(String contactCellphone) {
		this.contactCellphone = contactCellphone;
	}


	public String getContactCellphone1() {
		return contactCellphone1;
	}


	public void setContactCellphone1(String contactCellphone1) {
		this.contactCellphone1 = contactCellphone1;
	}


	public String getContactIdNo() {
		return contactIdNo;
	}


	public void setContactIdNo(String contactIdNo) {
		this.contactIdNo = contactIdNo;
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


	public String getContactCorpPhone1() {
		return contactCorpPhone1;
	}


	public void setContactCorpPhone1(String contactCorpPhone1) {
		this.contactCorpPhone1 = contactCorpPhone1;
	}


	public String getContactCorpFax() {
		return contactCorpFax;
	}


	public void setContactCorpFax(String contactCorpFax) {
		this.contactCorpFax = contactCorpFax;
	}


	public String getContactCorpPost() {
		return contactCorpPost;
	}


	public void setContactCorpPost(String contactCorpPost) {
		this.contactCorpPost = contactCorpPost;
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


	public Long getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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


	public Long getModifierId() {
		return modifierId;
	}


	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Integer getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


	public Integer getSnapVersion() {
		return snapVersion;
	}


	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}
	 
	 


}
