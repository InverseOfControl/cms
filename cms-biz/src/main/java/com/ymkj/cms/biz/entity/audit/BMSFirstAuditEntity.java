package com.ymkj.cms.biz.entity.audit;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 审核接口实体类
 * @author YM10143
 *
 */
public class BMSFirstAuditEntity extends BaseEntity{
	
	/**
	 * 序列化版本ID
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;//base表的主键
	private String loanNo;//借款编号
	private String ifPri;//是否加急标识
	private String appInputFlag;//app进件标识
	private String ifSuspectCheat;//是否疑似欺诈
	private String personName;//申请人姓名/客户姓名
	private String productName;//申请产品/productCd
	private String applyLmt;//申请金额
	private String owningBrance;//营业部名称
	private String owningBranceId;//进件营业部Id
	private String owningBranceAttribute;//进件营业部属性
	private Date auditEndTime;//提交信审时间 可能为Date类型
	private String applyType;//客户类型/申请类型
	private String refNodeStatus;//流程状态
	private String historNodeStatus;//历史节点状态
	private String checkPersonCode;//初审人员CODE
	private String proxyGroupName;
	private String accDate;//审批时间
	private String csStartDate;//初审首次分配时间
	private String zsStartDate;//终审首次分配时间
	private String accLmt;//审批额度
	private String idNo; //身份证号
	private String ifNewLoanNo;
	private String zSIfNewLoanNo;//ZS新生件标识	
	private String ifCreditRecord;//有无信用记录
	private String amoutIncome;//收入证明金额
	private String approvalPersonCode;//协审人员code
	private String operationTime;//操作时间
	private String primaryReason;//一级原因
	private String secodeReason;//二级原因
	private Date submitAuditDate;
	private String finalPersonCode; //终审人员
	private String  checkNodeState;  //复核节点
	private String applyTerm;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private String auditBackSnapVersion;//初审退回快照版本
	private String finalAuditBackSnapVersion;//终审退回快照版本
	private String auditSnapVersion;   //初审提交版本
	private String rtfState;   //状态
	
	
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
	public String getApprovalPersonCode() {
		return approvalPersonCode;
	}
	public void setApprovalPersonCode(String approvalPersonCode) {
		this.approvalPersonCode = approvalPersonCode;
	}
	public String getIfCreditRecord() {
		return ifCreditRecord;
	}
	public void setIfCreditRecord(String ifCreditRecord) {
		this.ifCreditRecord = ifCreditRecord;
	}
	public String getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(String amoutIncome) {
		this.amoutIncome = amoutIncome;
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
	public String getzSIfNewLoanNo() {
		return zSIfNewLoanNo;
	}
	public void setzSIfNewLoanNo(String zSIfNewLoanNo) {
		this.zSIfNewLoanNo = zSIfNewLoanNo;
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
	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public String getProxyGroupName() {
		return proxyGroupName;
	}
	public void setProxyGroupName(String proxyGroupName) {
		this.proxyGroupName = proxyGroupName;
	}
	private int version;//版本号
	
	public BMSFirstAuditEntity() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getOwningBranceId() {
		return owningBranceId;
	}
	public void setOwningBranceId(String owningBranceId) {
		this.owningBranceId = owningBranceId;
	}
	public String getOwningBranceAttribute() {
		return owningBranceAttribute;
	}
	public void setOwningBranceAttribute(String owningBranceAttribute) {
		this.owningBranceAttribute = owningBranceAttribute;
	}

	
	public Date getAuditEndTime() {
		return auditEndTime;
	}
	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getRefNodeStatus() {
		return refNodeStatus;
	}
	public void setRefNodeStatus(String refNodeStatus) {
		this.refNodeStatus = refNodeStatus;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getPrimaryReason() {
		return primaryReason;
	}
	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
	}
	public String getSecodeReason() {
		return secodeReason;
	}
	public void setSecodeReason(String secodeReason) {
		this.secodeReason = secodeReason;
	}
	public Date getSubmitAuditDate() {
		return submitAuditDate;
	}
	public void setSubmitAuditDate(Date submitAuditDate) {
		this.submitAuditDate = submitAuditDate;
	}
	public String getFinalPersonCode() {
		return finalPersonCode;
	}
	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
	}
	public String getCheckNodeState() {
		return checkNodeState;
	}
	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}

	public String getAuditBackSnapVersion() {
		return auditBackSnapVersion;
	}

	public void setAuditBackSnapVersion(String auditBackSnapVersion) {
		this.auditBackSnapVersion = auditBackSnapVersion;
	}

	public String getFinalAuditBackSnapVersion() {
		return finalAuditBackSnapVersion;
	}

	public void setFinalAuditBackSnapVersion(String finalAuditBackSnapVersion) {
		this.finalAuditBackSnapVersion = finalAuditBackSnapVersion;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public String getAuditSnapVersion() {
		return auditSnapVersion;
	}

	public void setAuditSnapVersion(String auditSnapVersion) {
		this.auditSnapVersion = auditSnapVersion;
	}	
	
	
}
