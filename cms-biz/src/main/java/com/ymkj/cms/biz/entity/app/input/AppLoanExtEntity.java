package com.ymkj.cms.biz.entity.app.input;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppLoanExtEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private String loanNo; //借款编号
	private String specialOrg; //机构	
	private String regularState; //规则状态
	private String specialPlan; //方案
	private Date pushDate; //数信推送时间
	private String reasonShuxin; //数信拒绝原因
	private String creditApplication; //贷款用途
	private BigDecimal sugLmt; //系统建议额度
	private String appOrgName; //机构名称	 
	private String priority; //客户等级
	private Long thirdId; //第三方id
	private Long appLoanPlan; //借款计划
	private BigDecimal ensureAmtAmount; //保证金
	private String primaryReasonCode;//一级原因Code
	private String primaryReason;//一级原因
	private String rejectPersonName;//拒绝人姓名
	private String rejectPersonCode;//拒绝人编码
	private BigDecimal clientType; //客户类型
	private Long creatorId; //创建人ID 
	private String creator; //创建人 
	private Date createdTime; //创建时间 
	private Integer isDelete; 
	private Integer verson ;
	private String loggedArea; //录入区域code
	private String loggedAreaName; //录入区域名称
	private String customerSource; //客户来源
	private String appAverageFlag; //app客服平均分派标识
	
	
	public AppLoanExtEntity(Long loanBaseId,String loanNo,Long creatorId, String creator){
		this.loanBaseId = loanBaseId;
		this.loanNo = loanNo;
		this.creatorId = creatorId;
		this.creator = creator;
	}
	
	
	public AppLoanExtEntity() {
		super();
		
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

	public String getPrimaryReason() {
		return primaryReason;
	}

	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
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


	public String getPrimaryReasonCode() {
		return primaryReasonCode;
	}


	public void setPrimaryReasonCode(String primaryReasonCode) {
		this.primaryReasonCode = primaryReasonCode;
	}


	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getAppAverageFlag() {
		return appAverageFlag;
	}

	public void setAppAverageFlag(String appAverageFlag) {
		this.appAverageFlag = appAverageFlag;
	}
	
}
