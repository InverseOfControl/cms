package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;
import java.util.Date;

public class ResOffTheStocksAuditVO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7849628293229113728L;
	private String loanNo;//借款编号
	private String ifPri;//是否加急标识
	private String appInputFlag;//app进件标识
	private String ifSuspectCheat;//是否疑似欺诈
	private String ifNewLoanNo;//是否新生件
	private String personName;//申请人姓名/客户姓名
	private String productName;//申请产品/productCd
	private String owningBrance;//营业部名称
	private String owningBranceAttribute;//进件营业部属性
	private String applyType;//客户类型/申请类型
	private String refNodeStatus;//流程状态
	private String historNodeStatus;//历史节点状态
	private String accDate;//审批时间
	private String accLmt;//审批额度
	private String csStartDate;//初审首次分配时间
	private String zsStartDate;//终审首次分配时间
	private String operationTime;//操作时间
	private String checkNodeState; //复核节点状态
	private String zSIfNewLoanNo;  //终审是否是新生件
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getCsStartDate() {
		return csStartDate;
	}
	public void setCsStartDate(String csStartDate) {
		this.csStartDate = csStartDate;
	}
	public String getZsStartDate() {
		return zsStartDate;
	}
	public void setZsStartDate(String zsStartDate) {
		this.zsStartDate = zsStartDate;
	}
	public String getHistorNodeStatus() {
		return historNodeStatus;
	}
	public void setHistorNodeStatus(String historNodeStatus) {
		this.historNodeStatus = historNodeStatus;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOwningBrance() {
		return owningBrance;
	}
	public void setOwningBrance(String owningBrance) {
		this.owningBrance = owningBrance;
	}
	public String getOwningBranceAttribute() {
		return owningBranceAttribute;
	}
	public void setOwningBranceAttribute(String owningBranceAttribute) {
		this.owningBranceAttribute = owningBranceAttribute;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getRefNodeStatus() {
		return refNodeStatus;
	}
	public void setRefNodeStatus(String refNodeStatus) {
		this.refNodeStatus = refNodeStatus;
	}
	
	public String getCheckNodeState() {
		return checkNodeState;
	}
	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}
	
	public String getzSIfNewLoanNo() {
		return zSIfNewLoanNo;
	}
	public void setzSIfNewLoanNo(String zSIfNewLoanNo) {
		this.zSIfNewLoanNo = zSIfNewLoanNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
