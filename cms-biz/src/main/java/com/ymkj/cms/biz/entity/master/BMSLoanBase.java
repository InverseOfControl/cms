package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSLoanBase extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2261178042205755664L;
	
	private int id;
	private String personId;
	private String loanNo;
	private String appNo;
	private String status;
	private String rtfState;
	private String rtfNodeState;
	private String signDate;
	private String branchManagerCode;
	private BigDecimal debtDio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
    
    private String creator;
    private String createdTime;
    private String creatorId;
    private String modifier;
    private String modifiedTime;
    private String modifierId;
    private String version;
    private String isDelete;
    /**
     * 产品code
     */
    private String productCode;
	//<!-- 经理code -->
	private String managerCode;
	//<!-- 经理名称 -->
	private String managerName;
	//<!-- 借款前台标识 -->
	private String zsIfNewLoanNo;
	//<!-- 签约客服code -->
	private String enterBranchId;
	//<!-- 签约客服code -->
	private String enterBranch;
	//<!-- 签约客服code -->
	private String enterBranchAttribute;
	//<!-- 借款前台标识 -->
	private String logoFlag;
	//<!-- 签约结束时间  -->
	private String signEndDate;
	//<!-- 合同确认时间-->
	private String confirmDate;
	//<!-- 合同确认结束时间 -->
	private String confirmEndDate;
	//<!-- 财务审核时间 -->
	private String financeAuditTime;
	//<!-- 复核时间 -->
	private String auditDate;
	//<!-- 锁定标的 -->
	private String lockTarget;
	//<!-- 审 是否新生件 0否 1是 -->
	private String ifNewLoanNo;
	//<!-- 是否优惠费率用户 -->
	private String  ifPreferentialUser;    
	//<!-- 是否需要补件：0:不需要，1：需要，2：已补件 -->                                             
	private String ifNeedPatchbolt;   
	//<!--补件时间 -->                  
	private String patchboltTime; 
    //是否抽取
    //private Integer isExtract;
    
    
   
    
    
  
    
  
//	public Integer getIsExtract() {
//		return isExtract;
//	}
//	public void setIsExtract(Integer isExtract) {
//		this.isExtract = isExtract;
//	}
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public BigDecimal getDebtDio() {
		return debtDio;
	}
	public void setDebtDio(BigDecimal debtDio) {
		this.debtDio = debtDio;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getZsIfNewLoanNo() {
		return zsIfNewLoanNo;
	}
	public void setZsIfNewLoanNo(String zsIfNewLoanNo) {
		this.zsIfNewLoanNo = zsIfNewLoanNo;
	}
	public String getEnterBranchId() {
		return enterBranchId;
	}
	public void setEnterBranchId(String enterBranchId) {
		this.enterBranchId = enterBranchId;
	}
	public String getEnterBranch() {
		return enterBranch;
	}
	public void setEnterBranch(String enterBranch) {
		this.enterBranch = enterBranch;
	}
	public String getEnterBranchAttribute() {
		return enterBranchAttribute;
	}
	public void setEnterBranchAttribute(String enterBranchAttribute) {
		this.enterBranchAttribute = enterBranchAttribute;
	}
	public String getLogoFlag() {
		return logoFlag;
	}
	public void setLogoFlag(String logoFlag) {
		this.logoFlag = logoFlag;
	}
	public String getSignEndDate() {
		return signEndDate;
	}
	public void setSignEndDate(String signEndDate) {
		this.signEndDate = signEndDate;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getConfirmEndDate() {
		return confirmEndDate;
	}
	public void setConfirmEndDate(String confirmEndDate) {
		this.confirmEndDate = confirmEndDate;
	}
	public String getFinanceAuditTime() {
		return financeAuditTime;
	}
	public void setFinanceAuditTime(String financeAuditTime) {
		this.financeAuditTime = financeAuditTime;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getLockTarget() {
		return lockTarget;
	}
	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	public String getIfNeedPatchbolt() {
		return ifNeedPatchbolt;
	}
	public void setIfNeedPatchbolt(String ifNeedPatchbolt) {
		this.ifNeedPatchbolt = ifNeedPatchbolt;
	}
	public String getPatchboltTime() {
		return patchboltTime;
	}
	public void setPatchboltTime(String patchboltTime) {
		this.patchboltTime = patchboltTime;
	}
	


}
