package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;

/**
 * 申请件响应VO
 * @author YM10161
 *
 */
public class ResBMSQualityInspectionAttrVO implements Serializable {
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 1401065917404587746L;
	
	private String personName;//客户姓名
	private String IDNo;//身份证号
	private String owningBrance;//进件营业部 
	private String owningBranceId;//进件营业部ID
	private String loanNo;//借款编号
	private String rtfState;//流程状态
	private String applyDate;//申请日期
	private String checkPerson;//初审员姓名
	private String checkPersonCd;//初审员工号
	private String finalPerson;//终审员姓名
	private String finalPersonCd;//终审员工号
	private String accDate;//审批日期
	private String applyType;//申请类型
	private String productCd;//产品
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
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
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
	
	
	
}
