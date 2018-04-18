package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;

/**
 * 自动派单响应VO
 * @author ym10161
 *
 */
public class ResBMSAutomaticDispatchAttrVO implements Serializable{
	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = -2890975027318625385L;
	
	private String loanNo;//借款编号
	//private String specialOrgCode;//机构CODE
	private String areaCode;//区域编码
	private String productCode;//产品code
	private String rtfStatus;//申请流程状态
	private String rtfNodeStatus;//申请流程节点状态
	private String ifPri;//是否加急
	private String appInputFlag;//是否app进件
	private String ifSuspectCheatis;//是否疑似欺诈
	private String checkPersonCode;//初审员Code
	private String finalPersonCode;//终审员Code
	private String apppovalPersonCode;//协审员Code
	private String owningBranchId;//进件门店ID
	private String firstSubZsDate;//首次初审提交终审时间
	private String accLmt;//审批金额
	private String applyDate;//首次提交门店申请时间
	private String ifNewLoanNo;//是否新生件 0：不是；1：是
	private String zSIfNewLoanNo;//zs新生件标识
	private int version;//版本号
	private int personId;//客户ID 
	private String custmerName;//客户名称
	private String custmerIDNo;//身份证号
	private String applyType;//申请件类型
	
	public String getzSIfNewLoanNo() {
		return zSIfNewLoanNo;
	}
	public void setzSIfNewLoanNo(String zSIfNewLoanNo) {
		this.zSIfNewLoanNo = zSIfNewLoanNo;
	}
	public String getIfSuspectCheatis() {
		return ifSuspectCheatis;
	}
	public void setIfSuspectCheatis(String ifSuspectCheatis) {
		this.ifSuspectCheatis = ifSuspectCheatis;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCustmerName() {
		return custmerName;
	}
	public void setCustmerName(String custmerName) {
		this.custmerName = custmerName;
	}
	public String getCustmerIDNo() {
		return custmerIDNo;
	}
	public void setCustmerIDNo(String custmerIDNo) {
		this.custmerIDNo = custmerIDNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRtfStatus() {
		return rtfStatus;
	}
	public void setRtfStatus(String rtfStatus) {
		this.rtfStatus = rtfStatus;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public String getAppInputFlag() {
		return appInputFlag;
	}
	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}
	public String getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApppovalPersonCode() {
		return apppovalPersonCode;
	}
	public void setApppovalPersonCode(String apppovalPersonCode) {
		this.apppovalPersonCode = apppovalPersonCode;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getFinalPersonCode() {
		return finalPersonCode;
	}
	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
	}
	public String getFirstSubZsDate() {
		return firstSubZsDate;
	}
	public void setFirstSubZsDate(String firstSubZsDate) {
		this.firstSubZsDate = firstSubZsDate;
	}
	
	
}
