package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPContactInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
 
	private Long id;
	private Long loanBaseId;
	private Long personId;
	private Long headId;//联系人主表Id
	private String appNo;//申请件编号
	private String org;//机构号
	private String loanNo;//申请单编号
	private Long contactId;//	联系人流水
	
	@NotNull(message = "000001,是否知晓贷款不能为空")
	private String ifKnowLoan;//是否知晓贷款
	@NotNull(message = "000001,姓名不能为空")
	private String contactName;//姓名
	@NotNull(message = "000001,与申请人关系不能为空")
	private String contactRelation;//与申请人关系
	private String contacGender;//性别
	@NotNull(message = "000001,手机号不能为空")
	private String contactCellPhone;//手机号
	private String contactCellPhone_1;//手机号2
	@NotNull(message = "000001,排序号不能为空")
	private Integer sequenceNum;//排序号
	private String contactIdNo;//身份证号码
	private String contactEmpName;//公司名称
	private String contactCorpPhone;//公司电话号码
	private String contactCorpPhone_1;//公司电话号码2
	private String contactCorpFax;//公司传真
	private String contactCorpPost;//职务
	
	private Integer snapVersion=3;//快照版本号
	private String ifForeignPenple;//是否外籍人士
	private String contactCellphone; //推送核心 联系人手机号码与json定义
	private String channelPushNo; //渠道流水号

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
	}

	public Long getHeadId() {
		return headId;
	}

	public void setHeadId(Long headId) {
		this.headId = headId;
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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
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

	public String getContacGender() {
		return contacGender;
	}

	public void setContacGender(String contacGender) {
		this.contacGender = contacGender;
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

	public String getContactCorpFax() {
		return contactCorpFax;
	}

	public void setContactCorpFax(String contactCorpFax) {
		this.contactCorpFax = contactCorpFax;
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


	public String getContactCellPhone_1() {
		return contactCellPhone_1;
	}

	public void setContactCellPhone_1(String contactCellPhone_1) {
		this.contactCellPhone_1 = contactCellPhone_1;
	}

	public String getContactCorpPhone_1() {
		return contactCorpPhone_1;
	}

	public void setContactCorpPhone_1(String contactCorpPhone_1) {
		this.contactCorpPhone_1 = contactCorpPhone_1;
	}

	public String getIfForeignPenple() {
		return ifForeignPenple;
	}

	public void setIfForeignPenple(String ifForeignPenple) {
		this.ifForeignPenple = ifForeignPenple;
	}

	public String getContactCellphone() {
		return contactCellphone;
	}

	public void setContactCellphone(String contactCellphone) {
		this.contactCellphone = contactCellphone;
	}

	public String getChannelPushNo() {
		return channelPushNo;
	}

	public void setChannelPushNo(String channelPushNo) {
		this.channelPushNo = channelPushNo;
	}

	
	

}
