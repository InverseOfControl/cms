package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;


public class ResLoanNumberVO implements Serializable{
	private long id;
	private String personId;
	private String loanNo;
	private String appNo;
	private String status;
	private String rtfState;
	private String rtfNodeState;
	private String signDate;
	private String branchManagerCode;
	private String branchManagerName;
	private String serviceCode;
	private String serviceName;
	private String remark;
	private String reviewCode;
	private String reviewName;
	private String signCode;
	private String signName;
	private String contractBranchId;
	private String contractBranch;
	private String loanBranchId;
	private String loanBranch;
	private String manageBranchId;
	private String manageBranch;
	private String manageUpdateDate;
	private String groupForDirectorId;
	private String groupForDirector;
	private String director;
	private String loanDate;
	private String owningBranchId;
	private String owningBranch;
	private String handleType;
	private String owningBranchAttribute;
	private String applyType;
	private String applyInputFlag;
    private String appInputFlag;
    private String loanId;
    private String idNo;
    private String name;
    private String appApplyDate;
    private String applyDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getBranchManagerCode() {
		return branchManagerCode;
	}
	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getReviewName() {
		return reviewName;
	}
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	public String getSignCode() {
		return signCode;
	}
	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public String getLoanBranchId() {
		return loanBranchId;
	}
	public void setLoanBranchId(String loanBranchId) {
		this.loanBranchId = loanBranchId;
	}
	public String getLoanBranch() {
		return loanBranch;
	}
	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}
	public String getManageBranchId() {
		return manageBranchId;
	}
	public void setManageBranchId(String manageBranchId) {
		this.manageBranchId = manageBranchId;
	}
	public String getManageBranch() {
		return manageBranch;
	}
	public void setManageBranch(String manageBranch) {
		this.manageBranch = manageBranch;
	}
	public String getManageUpdateDate() {
		return manageUpdateDate;
	}
	public void setManageUpdateDate(String manageUpdateDate) {
		this.manageUpdateDate = manageUpdateDate;
	}
	public String getGroupForDirectorId() {
		return groupForDirectorId;
	}
	public void setGroupForDirectorId(String groupForDirectorId) {
		this.groupForDirectorId = groupForDirectorId;
	}
	public String getGroupForDirector() {
		return groupForDirector;
	}
	public void setGroupForDirector(String groupForDirector) {
		this.groupForDirector = groupForDirector;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public String getHandleType() {
		return handleType;
	}
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
	public String getOwningBranchAttribute() {
		return owningBranchAttribute;
	}
	public void setOwningBranchAttribute(String owningBranchAttribute) {
		this.owningBranchAttribute = owningBranchAttribute;
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
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppApplyDate() {
		return appApplyDate;
	}
	public void setAppApplyDate(String appApplyDate) {
		this.appApplyDate = appApplyDate;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
    
    

}
