package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;

public class ResBMSReassignmentVo implements Serializable{

	/**
	 * 对象序列化
	 */
	private static final long serialVersionUID = -4624498783241173275L;

	private String loanNo;//借款单号
	private String rtfStatus;//借款状态
	private String rtfNodeStatus;//借款流程节点状态
	private String ifNewLoanNo;//是否是新生件
	private String zSIfNewLoanNo;//zs新生件标识
	private String customerName;//客户名称
	private String customerIDNO;//客户身份证
	private String productName;//产品名称
	private String xsSubDate;//提交信审时间
	private String owningBranchName;//营业部名称
	private String owningBranchAttr;//营业部属性
	private String ifPri;//是否加急
	private String appInputFlag;//app进件标识
	private String ifSuspectCheat;//是否疑似欺诈
	private String handleCode;//处理人编码
	private String proxyGroupName;//处理人所在小组名称
	private String cSProxyGroupName;//初审人员所在组名称
	private String version;//版本号
	private String loanNoTopClass;//申请件层级
	private String applyLmt;//申请额度
	private String accLmt;//审批额度
	private String checkPersonCode;//初审人员code
	private String finalPersonCode;//终审人员code	
	private String productCd;//产品编码
	private String applyType;//申请件类型
	private String specialOrg;//地区
	private String approvalPersonCode; //协审人员
	private String handleName;
	private String checkPerson;
	private int personId;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
	public String getFinalPersonCode() {
		return finalPersonCode;
	}
	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getSpecialOrg() {
		return specialOrg;
	}
	public void setSpecialOrg(String specialOrg) {
		this.specialOrg = specialOrg;
	}
	public String getzSIfNewLoanNo() {
		return zSIfNewLoanNo;
	}
	public void setzSIfNewLoanNo(String zSIfNewLoanNo) {
		this.zSIfNewLoanNo = zSIfNewLoanNo;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getRtfStatus() {
		return rtfStatus;
	}
	public void setRtfStatus(String rtfStatus) {
		this.rtfStatus = rtfStatus;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	public String getcSProxyGroupName() {
		return cSProxyGroupName;
	}
	public void setcSProxyGroupName(String cSProxyGroupName) {
		this.cSProxyGroupName = cSProxyGroupName;
	}
	public String getLoanNoTopClass() {
		return loanNoTopClass;
	}
	public void setLoanNoTopClass(String loanNoTopClass) {
		this.loanNoTopClass = loanNoTopClass;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getAppInputFlag() {
		return appInputFlag;
	}
	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}
	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIDNO() {
		return customerIDNO;
	}
	public void setCustomerIDNO(String customerIDNO) {
		this.customerIDNO = customerIDNO;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getXsSubDate() {
		return xsSubDate;
	}
	public void setXsSubDate(String xsSubDate) {
		this.xsSubDate = xsSubDate;
	}
	public String getOwningBranchName() {
		return owningBranchName;
	}
	public void setOwningBranchName(String owningBranchName) {
		this.owningBranchName = owningBranchName;
	}
	public String getOwningBranchAttr() {
		return owningBranchAttr;
	}
	public void setOwningBranchAttr(String owningBranchAttr) {
		this.owningBranchAttr = owningBranchAttr;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	
	public String getHandleCode() {
		return handleCode;
	}
	public void setHandleCode(String handleCode) {
		this.handleCode = handleCode;
	}
	public String getProxyGroupName() {
		return proxyGroupName;
	}
	public void setProxyGroupName(String proxyGroupName) {
		this.proxyGroupName = proxyGroupName;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getApprovalPersonCode() {
		return approvalPersonCode;
	}
	public void setApprovalPersonCode(String approvalPersonCode) {
		this.approvalPersonCode = approvalPersonCode;
	}
	public String getHandleName() {
		return handleName;
	}
	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	
}
