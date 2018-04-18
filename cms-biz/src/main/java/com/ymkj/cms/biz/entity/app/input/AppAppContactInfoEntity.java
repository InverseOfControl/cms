package com.ymkj.cms.biz.entity.app.input;

import org.apache.commons.lang.ObjectUtils;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.cms.biz.api.enums.EnumConstants;

public class AppAppContactInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 5995522646568046062L;
	
	private String id;
	private String loanBaseId;
	private String personId;
	private String loanNo;
	private String org;
	private String headId;
	private String sequenceNum;
	
	private String contactName; //联系人姓名
	private String contactRelation; //与申请人关系
	private String idNo; //身份证号码
	private String contactGender; //性别
	private String contactCellphone1; //手机
	private String contactCellphone2; //手机
	private String ifKnowLoan; //是否知晓贷款code
	private String ifKnowLoan1; //是否知晓贷款
	private String contactEmpName; //公司名称
	private String contactCorpPost; //职务
	private String contactCorpPhone; //公司电话号码
	private String contactCorpFax; //公司传真
	private String ifForeignPenple; //是否外籍人士
	
	private String creatorId;
	private String creator;
	
	public AppAppContactInfoEntity(Long loanBaseId,Long personId,String loanNo,Long creatorId,String creator){
		this.loanBaseId = ObjectUtils.toString(loanBaseId);
		this.personId = ObjectUtils.toString(personId);
		this.loanNo = loanNo;
		this.creatorId = ObjectUtils.toString(creatorId);
		this.creator = creator;
		this.org = EnumConstants.BMS_Org;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(String loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
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
	public String getIfKnowLoan() {
		return ifKnowLoan;
	}
	public void setIfKnowLoan(String ifKnowLoan) {
		this.ifKnowLoan = ifKnowLoan;
	}
	public String getIfKnowLoan1() {
		return ifKnowLoan1;
	}
	public void setIfKnowLoan1(String ifKnowLoan1) {
		this.ifKnowLoan1 = ifKnowLoan1;
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
	public void setContactCorpPost(String contactCorpPost) {
		this.contactCorpPost = contactCorpPost;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}

	public String getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public String getContactCellphone1() {
		return contactCellphone1;
	}

	public void setContactCellphone1(String contactCellphone1) {
		this.contactCellphone1 = contactCellphone1;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getContactCellphone2() {
		return contactCellphone2;
	}

	public void setContactCellphone2(String contactCellphone2) {
		this.contactCellphone2 = contactCellphone2;
	}

	public String getIfForeignPenple() {
		return ifForeignPenple;
	}

	public void setIfForeignPenple(String ifForeignPenple) {
		this.ifForeignPenple = ifForeignPenple;
	}
}
