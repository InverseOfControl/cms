package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;
import java.util.Date;
/**
 * 审核接口返回结果实体
 * @author YM10143
 *
 */
public class ResBMSAuditVo implements Serializable{

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = -2890975027318625385L;
	private String loanNo;//借款编号
	private String ifPri;//是否加急标识
	private String appInputFlag;//app进件标识
	private String ifSuspectCheat;//是否疑似欺诈
	private String personName;//申请人姓名/客户姓名
	private String productName;//申请产品/productCd
	private String applyLmt;//申请金额
	private String owningBranceId;//进件营业部Id
	private String owningBrance;//营业部名称
	private String owningBranceAttribute;//进件营业部属性
	private Date auditEndTime;//提交信审时间 可能为Date类型
	private String applyType;//客户类型/申请类型
	private String refNodeStatus;//流程状态
	private String proxyGroupName; //审核小组
	private String checkPersonCode;//初审人员CODE
	private int version;//版本号
	private String zSIfNewLoanNo;//Zs新生件标识
	private String ifCreditRecord;//有无信用记录
	private String amoutIncome;//收入证明金额
	private String ifNewLoanNo;  //Cs是否新生件
	private Date submitAuditDate; //提交信审时间
	private String checkNodeState;  //复核环节
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private String rtfState;   //状态
	private String auditBackSnapVersion;//初审退回快照版本
	private String finalAuditBackSnapVersion;//终审退回快照版本
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}

	public Date getAuditEndTime() {
		return auditEndTime;
	}

	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
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

	public String getzSIfNewLoanNo() {
		return zSIfNewLoanNo;
	}

	public void setzSIfNewLoanNo(String zSIfNewLoanNo) {
		this.zSIfNewLoanNo = zSIfNewLoanNo;
	}

	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}

	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}

	public ResBMSAuditVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCheckPersonCode() {
		return checkPersonCode;
	}

	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}

	public String getProxyGroupName() {
		return proxyGroupName;
	}


	public void setProxyGroupName(String proxyGroupName) {
		this.proxyGroupName = proxyGroupName;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOwningBranceId() {
		return owningBranceId;
	}
	public void setOwningBranceId(String owningBranceId) {
		this.owningBranceId = owningBranceId;
	}
	public String getOwningBrance() {
		return owningBrance;
	}
	public void setOwningBrance(String owningBrance) {
		this.owningBrance = owningBrance;
	}
	public String getRefNodeStatus() {
		return refNodeStatus;
	}
	public void setRefNodeStatus(String refNodeStatus) {
		this.refNodeStatus = refNodeStatus;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public Date getSubmitAuditDate() {
		return submitAuditDate;
	}

	public void setSubmitAuditDate(Date submitAuditDate) {
		this.submitAuditDate = submitAuditDate;
	}

	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}

	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}

	public String getCheckNodeState() {
		return checkNodeState;
	}

	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
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
	
}
