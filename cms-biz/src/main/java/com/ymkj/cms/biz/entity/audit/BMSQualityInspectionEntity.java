package com.ymkj.cms.biz.entity.audit;

import com.ymkj.base.core.biz.entity.BaseEntity;
/**
 * 申请件实体
 * @author YM10161
 *
 */
public class BMSQualityInspectionEntity extends BaseEntity{

	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 1L;
	
	private String personName;//客户姓名
	private String IDNo;//身份证号
	private String owningBrance;//进件营业部 
	private String owningBranceId;//进件营业部ID
	private String applyInputFlag;//申请件来源
	private String loanNo;//借款编号
	private String appNo;//申请编号
	private String applyDate;//申请日期
	private String rtfNodeState;//流程节点状态
	private String checkPerson;//初审员姓名
	private String checkPersonCd;//初审员工号
	private String finalPerson;//终审员姓名
	private String finalPersonCd;//终审员工号
	private String accDate;//审批日期
	private String applyType;//申请类型
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getIDNo() {
		return IDNo;
	}
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	public String getOwningBrance() {
		return owningBrance;
	}
	public void setOwningBrance(String owningBrance) {
		this.owningBrance = owningBrance;
	}
	public String getApplyInputFlag() {
		return applyInputFlag;
	}
	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getCheckPersonCd() {
		return checkPersonCd;
	}
	public void setCheckPersonCd(String checkPersonCd) {
		this.checkPersonCd = checkPersonCd;
	}
	public String getFinalPerson() {
		return finalPerson;
	}
	public void setFinalPerson(String finalPerson) {
		this.finalPerson = finalPerson;
	}
	public String getFinalPersonCd() {
		return finalPersonCd;
	}
	public void setFinalPersonCd(String finalPersonCd) {
		this.finalPersonCd = finalPersonCd;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOwningBranceId() {
		return owningBranceId;
	}
	public void setOwningBranceId(String owningBranceId) {
		this.owningBranceId = owningBranceId;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	
	
	
}
