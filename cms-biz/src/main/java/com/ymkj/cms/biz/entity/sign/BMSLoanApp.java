package com.ymkj.cms.biz.entity.sign;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSLoanApp extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1496252005735983187L;
	private String loanNo;//借款编号
	private String ifCreditRecord;
	private String remark;
	private String checkPerson;
	private String manageBranch;
	private String productCd;
	private String appNo;
	private String sysAccTrem;
	private String signDate;
	private String ifLoanAgain;
	private String priority;
	private String ifPri;
	private String branchManager;
	private String contractSource;
	private String ifPatchBolt;
	private String applyBankBranch;
	private String jpaVersion;
	private String ifEnd;
	private String applyLmt;
	private String owningBranch;
	private String checkAllotDate;
	private String contractTrem;
	private String status;
	private String finalRole;
	private String accLmt;
	private String sugLmt;
	private String accTerm;
	private String proNum;
	private String pointResult;
	private String amoutIncome;
	private String createUser;
	private String applyBankName;
	private String bankPhone;
	private String applyBankCardNo;
    private String appOrgName;
    private String updateUser;
    private String clientType;
    private String specialPlan;
    private String specialOrg;
    private String creditApplication;
    private String sysCheckLmt;
    private String contractNum;
    private String thirdId;
    private String initProductCd;
    private String org;
    private String finalAllotDate;
    private String refuseCode;
    private String accDate;
    private String proName;
    private String director; //业务主任code
    private String owningBranchAttribute;
    private String ifRefuse;
    private String contractLmt;
    private String ensureAmtAmount;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date manageUpdateDate;
    private String loanBranch;
    private String createDate;
    private String repayDate;
    private String approvalPerson;
    private String ifUrgent;
    private String ifSuspectCheat;
    private String ifOldOrNewLogo;
    private String contractBranch;
    private String appLoanPlan;
    private String updateDate;
    private String applyRate;
    private String finalPerson;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date loanDate;
    private String sysAccLmt;
    private String applyTerm;
    private String rtfState;
    private String applyType; 
    private String applyInputFlag;
    private String appInputFlag;
    private String lujsName;
    private String lujsLoanReqId;
	private Integer channelPushFrequency; //渠道推送次数，默认为0
	private String channelPushNo; //渠道流水号

    
    
    
    
    
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIfCreditRecord() {
		return ifCreditRecord;
	}
	public void setIfCreditRecord(String ifCreditRecord) {
		this.ifCreditRecord = ifCreditRecord;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getManageBranch() {
		return manageBranch;
	}
	public void setManageBranch(String manageBranch) {
		this.manageBranch = manageBranch;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getSysAccTrem() {
		return sysAccTrem;
	}
	public void setSysAccTrem(String sysAccTrem) {
		this.sysAccTrem = sysAccTrem;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getIfLoanAgain() {
		return ifLoanAgain;
	}
	public void setIfLoanAgain(String ifLoanAgain) {
		this.ifLoanAgain = ifLoanAgain;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public String getBranchManager() {
		return branchManager;
	}
	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}
	public String getContractSource() {
		return contractSource;
	}
	public void setContractSource(String contractSource) {
		this.contractSource = contractSource;
	}
	public String getIfPatchBolt() {
		return ifPatchBolt;
	}
	public void setIfPatchBolt(String ifPatchBolt) {
		this.ifPatchBolt = ifPatchBolt;
	}
	public String getApplyBankBranch() {
		return applyBankBranch;
	}
	public void setApplyBankBranch(String applyBankBranch) {
		this.applyBankBranch = applyBankBranch;
	}
	public String getJpaVersion() {
		return jpaVersion;
	}
	public void setJpaVersion(String jpaVersion) {
		this.jpaVersion = jpaVersion;
	}
	public String getIfEnd() {
		return ifEnd;
	}
	public void setIfEnd(String ifEnd) {
		this.ifEnd = ifEnd;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public String getCheckAllotDate() {
		return checkAllotDate;
	}
	public void setCheckAllotDate(String checkAllotDate) {
		this.checkAllotDate = checkAllotDate;
	}
	public String getContractTrem() {
		return contractTrem;
	}
	public void setContractTrem(String contractTrem) {
		this.contractTrem = contractTrem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFinalRole() {
		return finalRole;
	}
	public void setFinalRole(String finalRole) {
		this.finalRole = finalRole;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getSugLmt() {
		return sugLmt;
	}
	public void setSugLmt(String sugLmt) {
		this.sugLmt = sugLmt;
	}
	public String getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(String accTerm) {
		this.accTerm = accTerm;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	public String getPointResult() {
		return pointResult;
	}
	public void setPointResult(String pointResult) {
		this.pointResult = pointResult;
	}
	public String getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(String amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getApplyBankName() {
		return applyBankName;
	}
	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	public String getApplyBankCardNo() {
		return applyBankCardNo;
	}
	public void setApplyBankCardNo(String applyBankCardNo) {
		this.applyBankCardNo = applyBankCardNo;
	}
	public String getAppOrgName() {
		return appOrgName;
	}
	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getSpecialPlan() {
		return specialPlan;
	}
	public void setSpecialPlan(String specialPlan) {
		this.specialPlan = specialPlan;
	}
	public String getSpecialOrg() {
		return specialOrg;
	}
	public void setSpecialOrg(String specialOrg) {
		this.specialOrg = specialOrg;
	}
	public String getCreditApplication() {
		return creditApplication;
	}
	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	public String getSysCheckLmt() {
		return sysCheckLmt;
	}
	public void setSysCheckLmt(String sysCheckLmt) {
		this.sysCheckLmt = sysCheckLmt;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	public String getInitProductCd() {
		return initProductCd;
	}
	public void setInitProductCd(String initProductCd) {
		this.initProductCd = initProductCd;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getFinalAllotDate() {
		return finalAllotDate;
	}
	public void setFinalAllotDate(String finalAllotDate) {
		this.finalAllotDate = finalAllotDate;
	}
	public String getRefuseCode() {
		return refuseCode;
	}
	public void setRefuseCode(String refuseCode) {
		this.refuseCode = refuseCode;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getOwningBranchAttribute() {
		return owningBranchAttribute;
	}
	public void setOwningBranchAttribute(String owningBranchAttribute) {
		this.owningBranchAttribute = owningBranchAttribute;
	}
	public String getIfRefuse() {
		return ifRefuse;
	}
	public void setIfRefuse(String ifRefuse) {
		this.ifRefuse = ifRefuse;
	}
	public String getContractLmt() {
		return contractLmt;
	}
	public void setContractLmt(String contractLmt) {
		this.contractLmt = contractLmt;
	}
	public String getEnsureAmtAmount() {
		return ensureAmtAmount;
	}
	public void setEnsureAmtAmount(String ensureAmtAmount) {
		this.ensureAmtAmount = ensureAmtAmount;
	}

	public String getLoanBranch() {
		return loanBranch;
	}
	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String getApprovalPerson() {
		return approvalPerson;
	}
	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	public String getIfUrgent() {
		return ifUrgent;
	}
	public void setIfUrgent(String ifUrgent) {
		this.ifUrgent = ifUrgent;
	}
	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}
	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}
	public String getIfOldOrNewLogo() {
		return ifOldOrNewLogo;
	}
	public void setIfOldOrNewLogo(String ifOldOrNewLogo) {
		this.ifOldOrNewLogo = ifOldOrNewLogo;
	}
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public String getAppLoanPlan() {
		return appLoanPlan;
	}
	public void setAppLoanPlan(String appLoanPlan) {
		this.appLoanPlan = appLoanPlan;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getApplyRate() {
		return applyRate;
	}
	public void setApplyRate(String applyRate) {
		this.applyRate = applyRate;
	}
	public String getFinalPerson() {
		return finalPerson;
	}
	public void setFinalPerson(String finalPerson) {
		this.finalPerson = finalPerson;
	}

	public Date getManageUpdateDate() {
		return manageUpdateDate;
	}
	public void setManageUpdateDate(Date manageUpdateDate) {
		this.manageUpdateDate = manageUpdateDate;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public String getSysAccLmt() {
		return sysAccLmt;
	}
	public void setSysAccLmt(String sysAccLmt) {
		this.sysAccLmt = sysAccLmt;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getApplyInputFlag() {
		return applyInputFlag;
	}
	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
	}
	public String getAppInputFlag() {
		return appInputFlag;
	}
	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}
	public String getLujsName() {
		return lujsName;
	}
	public void setLujsName(String lujsName) {
		this.lujsName = lujsName;
	}
	public String getLujsLoanReqId() {
		return lujsLoanReqId;
	}
	public void setLujsLoanReqId(String lujsLoanReqId) {
		this.lujsLoanReqId = lujsLoanReqId;
	}
	public Integer getChannelPushFrequency() {
		return channelPushFrequency;
	}
	public void setChannelPushFrequency(Integer channelPushFrequency) {
		this.channelPushFrequency = channelPushFrequency;
	}
	public String getChannelPushNo() {
		return channelPushNo;
	}
	public void setChannelPushNo(String channelPushNo) {
		this.channelPushNo = channelPushNo;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
    
}
