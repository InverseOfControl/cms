package com.ymkj.cms.biz.entity.audit;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSApplicationPartEntity extends BaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3369743613021767142L;
	private String loanNo;//借款编号
	private String status;//申请件状态
	private String rtfStatus;//流程状态
	private String rtfNodeStatus;//节点状态
	private String owningBanchId;//营业部Id
	private String owningBanchName;//营业部名称
	private String applyDate;//申请时间
	private String customerName;//客户姓名
	private String cdNo;//身份证ID
	private String productName;//产品名称
	private String productCode;//产品编码
	private String accLmt;//审批额度
	private String accTerm;//审批期限
	private String checkPersonName;//初审员
	private String finalPersonName;//终审员
	private String primaryReason;//一级原因
	private String secodeReason;//二级原因
	private String primaryReasonCode;//一级原因code
	private String secodeReasonCode;//二级原因code
	private String accDate;//最终终审审批时间
	private String rejectPersonName;//拒绝人姓名
	private Date rejectPersonDate;//最后拒绝时间
	private String blackList;//黑名单ID
	private String version;//审批表的版本号
	private Date auditEndTime;  //提交信审时间
	private String approvalPersonName;  //协审员
	
	
	private String checkPersonCode;//初审员Code
	private String finalPersonCode;//终审员Code
	private String approvalPersonCode;  //协审人员Code
	
	
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getFinalPersonCode() {
		return finalPersonCode;
	}
	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
	}
	public String getApprovalPersonCode() {
		return approvalPersonCode;
	}
	public void setApprovalPersonCode(String approvalPersonCode) {
		this.approvalPersonCode = approvalPersonCode;
	}
	public Date getRejectPersonDate() {
		return rejectPersonDate;
	}
	public void setRejectPersonDate(Date rejectPersonDate) {
		this.rejectPersonDate = rejectPersonDate;
	}
	public String getBlackList() {
		return blackList;
	}
	public void setBlackList(String blackList) {
		this.blackList = blackList;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCdNo() {
		return cdNo;
	}
	public void setCdNo(String cdNo) {
		this.cdNo = cdNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getCheckPersonName() {
		return checkPersonName;
	}
	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}
	public String getFinalPersonName() {
		return finalPersonName;
	}
	public void setFinalPersonName(String finalPersonName) {
		this.finalPersonName = finalPersonName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(String accTerm) {
		this.accTerm = accTerm;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getOwningBanchId() {
		return owningBanchId;
	}
	public void setOwningBanchId(String owningBanchId) {
		this.owningBanchId = owningBanchId;
	}
	public String getOwningBanchName() {
		return owningBanchName;
	}
	public void setOwningBanchName(String owningBanchName) {
		this.owningBanchName = owningBanchName;
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
	public String getRejectPersonName() {
		return rejectPersonName;
	}
	public void setRejectPersonName(String rejectPersonName) {
		this.rejectPersonName = rejectPersonName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPrimaryReasonCode() {
		return primaryReasonCode;
	}
	public void setPrimaryReasonCode(String primaryReasonCode) {
		this.primaryReasonCode = primaryReasonCode;
	}
	public String getSecodeReasonCode() {
		return secodeReasonCode;
	}
	public void setSecodeReasonCode(String secodeReasonCode) {
		this.secodeReasonCode = secodeReasonCode;
	}
	public Date getAuditEndTime() {
		return auditEndTime;
	}
	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
	}
	public String getApprovalPersonName() {
		return approvalPersonName;
	}
	public void setApprovalPersonName(String approvalPersonName) {
		this.approvalPersonName = approvalPersonName;
	}
	
	
}
