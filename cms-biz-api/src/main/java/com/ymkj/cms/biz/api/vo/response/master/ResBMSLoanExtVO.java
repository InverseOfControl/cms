package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;

public class ResBMSLoanExtVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 760983512235634010L;
	private String loanNo;
	private String appNo;
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
	private String specialOrg;
	    private String regularState;
	    private String specialPlan;
	    private String pushDate;
	    private String reasonShuxin;
	    private String creditApplication;
	    private String sugLmt;
	    private String appOrgName;
	    private String priority;
	    private String thirdId;
	    private String appLoanPlan;
	    private String ensureAmtAmount;
	    private String clientType;
	   /* private String primaryReason;
	    private String secodeReason;*/
	    private int id;
	    private String loanBaseId;
	    private String creator;
	    private String createdTime;
	    private String creatorId;
	    private String modifier;
	    private String modifiedTime;
	    private String modifierId;
	    private String version;
	    private String isDelete;
	    
	    private String busNumber;//包银业务流水申请号
	    private String byState;//包银状态值 0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功
	    private String byRefusalResult;//包银黑名单拒绝原因
	    private String windControlDate;//授信信息推送时间
	    private String windControlResult;//风控结果
	    private String auditingState;//'人工审核状态 0审核中 1通过  2拒绝 3是图像资料待上传 4是补件';
	    
	    
	    private String rejectPersonName; // <!--  拒绝人姓名 -->
	    private String rejectPersonCode;    //<!-- 拒绝人编码 -->
	    private String applyStartTime;   //<!-- 申请开始时间 -->
	    private String applyEndTime;    //<!-- 申请结束时间 -->
	    private String auditStartTime;  //<!-- 复核开始时间 -->
	    private String auditEndTime;     //<!-- 复核结束 -->
	    private String blacklistId;     //<!--  灰黑名单ID -->
	    private String loggedArea;      //<!-- 录入区域 -->
	    private String loggedAreaName;   //<!-- 录入区域名称 -->
	    private String reviewSnapVersion;   //<!--  复核快照版本 -->
	    private String auditSnapVersion;    //<!-- 初审审核快照版本 -->
	    private String antiFraudScore;      //<!-- 反欺诈评分 -->
	    private String antiFraudWarning;     //<!-- 反欺诈预警 -->
	    private String antiRiskRate;      //<!--   欺诈风险评估 -->
	    private String auditBackSnapVersion;    //<!-- 初审退回快照版本 -->
	    private String finalauditBackSnapVersion;   //<!--  终审退回快照版本 -->
	    private String primaryReason;
	    private String secodeReason; 
	    private String lujsName;
	    private String lujsApplyNo;
	    private String lujsLoanReqId;
	    private String orgAuditState;
	    private String appAverageFlag;
	    
	    
		public String getLujsLoanReqId() {
			return lujsLoanReqId;
		}
		public void setLujsLoanReqId(String lujsLoanReqId) {
			this.lujsLoanReqId = lujsLoanReqId;
		}
		public String getOrgAuditState() {
			return orgAuditState;
		}
		public void setOrgAuditState(String orgAuditState) {
			this.orgAuditState = orgAuditState;
		}
		public String getAppAverageFlag() {
			return appAverageFlag;
		}
		public void setAppAverageFlag(String appAverageFlag) {
			this.appAverageFlag = appAverageFlag;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLoanBaseId() {
			return loanBaseId;
		}
		public void setLoanBaseId(String loanBaseId) {
			this.loanBaseId = loanBaseId;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public String getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public String getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(String creatorId) {
			this.creatorId = creatorId;
		}
		public String getModifier() {
			return modifier;
		}
		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
		public String getModifiedTime() {
			return modifiedTime;
		}
		public void setModifiedTime(String modifiedTime) {
			this.modifiedTime = modifiedTime;
		}
		public String getModifierId() {
			return modifierId;
		}
		public void setModifierId(String modifierId) {
			this.modifierId = modifierId;
		}
		public String getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(String isDelete) {
			this.isDelete = isDelete;
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
		public String getPushDate() {
			return pushDate;
		}
		public void setPushDate(String pushDate) {
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
		public String getSugLmt() {
			return sugLmt;
		}
		public void setSugLmt(String sugLmt) {
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
		public String getThirdId() {
			return thirdId;
		}
		public void setThirdId(String thirdId) {
			this.thirdId = thirdId;
		}
		public String getAppLoanPlan() {
			return appLoanPlan;
		}
		public void setAppLoanPlan(String appLoanPlan) {
			this.appLoanPlan = appLoanPlan;
		}
		public String getEnsureAmtAmount() {
			return ensureAmtAmount;
		}
		public void setEnsureAmtAmount(String ensureAmtAmount) {
			this.ensureAmtAmount = ensureAmtAmount;
		}
		public String getClientType() {
			return clientType;
		}
		public void setClientType(String clientType) {
			this.clientType = clientType;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
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
		public String getApplyStartTime() {
			return applyStartTime;
		}
		public void setApplyStartTime(String applyStartTime) {
			this.applyStartTime = applyStartTime;
		}
		public String getApplyEndTime() {
			return applyEndTime;
		}
		public void setApplyEndTime(String applyEndTime) {
			this.applyEndTime = applyEndTime;
		}
		public String getAuditStartTime() {
			return auditStartTime;
		}
		public void setAuditStartTime(String auditStartTime) {
			this.auditStartTime = auditStartTime;
		}
		public String getAuditEndTime() {
			return auditEndTime;
		}
		public void setAuditEndTime(String auditEndTime) {
			this.auditEndTime = auditEndTime;
		}
		public String getBlacklistId() {
			return blacklistId;
		}
		public void setBlacklistId(String blacklistId) {
			this.blacklistId = blacklistId;
		}
		public String getLoggedArea() {
			return loggedArea;
		}
		public void setLoggedArea(String loggedArea) {
			this.loggedArea = loggedArea;
		}
		public String getLoggedAreaName() {
			return loggedAreaName;
		}
		public void setLoggedAreaName(String loggedAreaName) {
			this.loggedAreaName = loggedAreaName;
		}
		public String getReviewSnapVersion() {
			return reviewSnapVersion;
		}
		public void setReviewSnapVersion(String reviewSnapVersion) {
			this.reviewSnapVersion = reviewSnapVersion;
		}
		public String getAuditSnapVersion() {
			return auditSnapVersion;
		}
		public void setAuditSnapVersion(String auditSnapVersion) {
			this.auditSnapVersion = auditSnapVersion;
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
		public String getAuditBackSnapVersion() {
			return auditBackSnapVersion;
		}
		public void setAuditBackSnapVersion(String auditBackSnapVersion) {
			this.auditBackSnapVersion = auditBackSnapVersion;
		}
		public String getFinalauditBackSnapVersion() {
			return finalauditBackSnapVersion;
		}
		public void setFinalauditBackSnapVersion(String finalauditBackSnapVersion) {
			this.finalauditBackSnapVersion = finalauditBackSnapVersion;
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
		public String getBusNumber() {
			return busNumber;
		}
		public void setBusNumber(String busNumber) {
			this.busNumber = busNumber;
		}
		public String getByState() {
			return byState;
		}
		public void setByState(String byState) {
			this.byState = byState;
		}
		public String getByRefusalResult() {
			return byRefusalResult;
		}
		public void setByRefusalResult(String byRefusalResult) {
			this.byRefusalResult = byRefusalResult;
		}
		public String getWindControlDate() {
			return windControlDate;
		}
		public void setWindControlDate(String windControlDate) {
			this.windControlDate = windControlDate;
		}
		public String getWindControlResult() {
			return windControlResult;
		}
		public void setWindControlResult(String windControlResult) {
			this.windControlResult = windControlResult;
		}
		public String getAuditingState() {
			return auditingState;
		}
		public void setAuditingState(String auditingState) {
			this.auditingState = auditingState;
		}
		public String getLujsName() {
			return lujsName;
		}
		public void setLujsName(String lujsName) {
			this.lujsName = lujsName;
		}
		public String getLujsApplyNo() {
			return lujsApplyNo;
		}
		public void setLujsApplyNo(String lujsApplyNo) {
			this.lujsApplyNo = lujsApplyNo;
		}

}
