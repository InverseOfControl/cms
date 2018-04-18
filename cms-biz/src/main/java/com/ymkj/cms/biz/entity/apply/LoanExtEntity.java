package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class LoanExtEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	private Long id; // 名称
	
	private Long loanBaseId;
	
	private String appNo;
	
	private String loanNo;
	
	private String specialOrg;//机构	
 
	private String regularState;//规则状态
	 
	private String specialPlan;//方案
		 
	private Date pushDate;//数信推送时间
		 
	private String reasonShuxin;//数信拒绝原因
	@NotNull(message = "000001,贷款用途不能为空")			
	private String creditApplication;//贷款用途
	
	private BigDecimal sugLmt;//系统建议额度
	
	private String appOrgName;//机构名称	 
		 
	private String priority;//客户等级
		 
	private Long thirdId;//第三方id
	
	private Long appLoanPlan;//借款计划
		 
	private BigDecimal ensureAmtAmount;//保证金
	
	private BigDecimal clientType;//客户类型
	
	private String customerSource;//客户来源
	
	private Date applyStartTime;//申请开始
	
	private Date applyEndTime;//申请结束
	 
	private Date auditStartTime; //审核开始
	
	private Date auditEndTime; //审核结束
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	private String rejectPersonName;
	private String rejectPersonCode;
	
	private String firstLevleReasonsCode;
	private String twoLevleReasonsCode;
	private String primaryReason;//一级原因
	private String secodeReason;//二级原因
	
	private String loggedArea;	//录入区域
	private String loggedAreaName;	//录入区域名称
	
	private String peviewSnapVersion;//复核快照版本
	private String auditSnapVersion;//初审审核快照版本
	
	private String auditBackSnapVersion;//初审退回快照版本
	private String finalAuditBackSnapVersion;//终审退回快照版本
	
	private String blackListId;//黑名单ID
	
	private String antiFraudScore;//  反欺诈评分
	private String antiFraudWarning;//   反欺诈预警
	private String antiRiskRate;//  欺诈风险评估
	
	private String lujsLoanReqId;//  陆金所id
	private String lujsName;//  陆金所用户名
	private String lujsApplyNo;
	
	
	
	
	
	public String getLujsApplyNo() {
		return lujsApplyNo;
	}

	public void setLujsApplyNo(String lujsApplyNo) {
		this.lujsApplyNo = lujsApplyNo;
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

	public String getAntiFraudScore() {
		return antiFraudScore;
	}

	public void setAntiFraudScore(String antiFraudScore) {
		this.antiFraudScore = antiFraudScore;
	}

	public String getAntiFraudWarning() {
		return antiFraudWarning;
	}

	public void setAntiFraudWarning(String antiFraudWarning) {
		this.antiFraudWarning = antiFraudWarning;
	}

	public String getAntiRiskRate() {
		return antiRiskRate;
	}

	public void setAntiRiskRate(String antiRiskRate) {
		this.antiRiskRate = antiRiskRate;
	}

	public String getBlackListId() {
		return blackListId;
	}

	public void setBlackListId(String blackListId) {
		this.blackListId = blackListId;
	}

	public String getPeviewSnapVersion() {
		return peviewSnapVersion;
	}

	public void setPeviewSnapVersion(String peviewSnapVersion) {
		this.peviewSnapVersion = peviewSnapVersion;
	}

	public String getAuditSnapVersion() {
		return auditSnapVersion;
	}

	public void setAuditSnapVersion(String auditSnapVersion) {
		this.auditSnapVersion = auditSnapVersion;
	}

	public String getLoggedAreaName() {
		return loggedAreaName;
	}

	public void setLoggedAreaName(String loggedAreaName) {
		this.loggedAreaName = loggedAreaName;
	}

	public String getLoggedArea() {
		return loggedArea;
	}

	public void setLoggedArea(String loggedArea) {
		this.loggedArea = loggedArea;
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

	public String getFirstLevleReasonsCode() {
		return firstLevleReasonsCode;
	}

	public void setFirstLevleReasonsCode(String firstLevleReasonsCode) {
		this.firstLevleReasonsCode = firstLevleReasonsCode;
	}

	public String getTwoLevleReasonsCode() {
		return twoLevleReasonsCode;
	}

	public void setTwoLevleReasonsCode(String twoLevleReasonsCode) {
		this.twoLevleReasonsCode = twoLevleReasonsCode;
	}

	public String getRejectPersonName() {
		return rejectPersonName;
	}

	public void setRejectPersonName(String rejectPersonName) {
		this.rejectPersonName = rejectPersonName;
	}

	public String getRejectPersonCode() {
		return rejectPersonCode;
	}

	public void setRejectPersonCode(String rejectPersonCode) {
		this.rejectPersonCode = rejectPersonCode;
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

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	
	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getSpecialOrg() {
		return specialOrg;
	}

	public void setSpecialOrg(String specialOrg) {
		this.specialOrg = specialOrg;
	}

	public String getRegularState() {
		return regularState;
	}

	public void setRegularState(String regularState) {
		this.regularState = regularState;
	}

	public String getSpecialPlan() {
		return specialPlan;
	}

	public void setSpecialPlan(String specialPlan) {
		this.specialPlan = specialPlan;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	public String getReasonShuxin() {
		return reasonShuxin;
	}

	public void setReasonShuxin(String reasonShuxin) {
		this.reasonShuxin = reasonShuxin;
	}

	public String getCreditApplication() {
		return creditApplication;
	}

	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}

	public BigDecimal getSugLmt() {
		return sugLmt;
	}

	public void setSugLmt(BigDecimal sugLmt) {
		this.sugLmt = sugLmt;
	}

	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Long getThirdId() {
		return thirdId;
	}

	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;
	}

	public Long getAppLoanPlan() {
		return appLoanPlan;
	}

	public void setAppLoanPlan(Long appLoanPlan) {
		this.appLoanPlan = appLoanPlan;
	}

	public BigDecimal getEnsureAmtAmount() {
		return ensureAmtAmount;
	}

	public void setEnsureAmtAmount(BigDecimal ensureAmtAmount) {
		this.ensureAmtAmount = ensureAmtAmount;
	}

	public BigDecimal getClientType() {
		return clientType;
	}

	public void setClientType(BigDecimal clientType) {
		this.clientType = clientType;
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

	public Date getApplyStartTime() {
		return applyStartTime;
	}

	public void setApplyStartTime(Date applyStartTime) {
		this.applyStartTime = applyStartTime;
	}

	public Date getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public Date getAuditStartTime() {
		return auditStartTime;
	}

	public void setAuditStartTime(Date auditStartTime) {
		this.auditStartTime = auditStartTime;
	}

	public Date getAuditEndTime() {
		return auditEndTime;
	}

	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
	}

	public String getLujsLoanReqId() {
		return lujsLoanReqId;
	}

	public void setLujsLoanReqId(String lujsLoanReqId) {
		this.lujsLoanReqId = lujsLoanReqId;
	}

	public String getLujsName() {
		return lujsName;
	}

	public void setLujsName(String lujsName) {
		this.lujsName = lujsName;
	}

 
	

}
