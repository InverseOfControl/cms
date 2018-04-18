package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSLoanBaseVO extends Request {

	
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3774123415274388337L;
	private int id;
	/*private String personId;*/
	private String personId;
	private String loanNo;
	private String appNo;
	private String status;
	
	private String startDate;
	
	private String endDate;
	
	private String startVersion;
	
	private String endVersion;
	

	/**
	 * app进件时间差（间隔天）
	 */
	private Integer appApplydateDiff;
	/**
	 * 非app进件时间差（间隔天）
	 */
	private Integer createTimedateDiff;
	/**
	 * 需要要显示的联合状态   status+rtfState+rtfNodeState集合
	 */
	private List<String> concatRtfStateList;
	/**
	 * 联合状态   rtfNodeState+check
	 */
	private List<String> concatRtfNodeStateNoCheckList;
	/**
	 * 需要要显示的联合状态   rtfNodeState集合
	 */
	private List<String> rtfNodeStateList;
	/**
	 * 渠道限制集合
	 */
	private List<String> channelList;
	
	/**
	 * 剔除借款编号集合
	 */
	private List<String> loanNoEliminateList;



	public Integer getCreateTimedateDiff() {
		return createTimedateDiff;
	}
	public void setCreateTimedateDiff(Integer createTimedateDiff) {
		this.createTimedateDiff = createTimedateDiff;
	}
	public List<String> getConcatRtfStateList() {
		return concatRtfStateList;
	}
	public void setConcatRtfStateList(List<String> concatRtfStateList) {
		this.concatRtfStateList = concatRtfStateList;
	}
	public Integer getAppApplydateDiff() {
		return appApplydateDiff;
	}
	public void setAppApplydateDiff(Integer appApplydateDiff) {
		this.appApplydateDiff = appApplydateDiff;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


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
    
    private String creator;
    private String createdTime;
    private String creatorId;
    private String modifier;
    private String modifiedTime;
    private String modifierId;
    private String version;
    private String isDelete;
    /**
     * 没有发生过复核提交
     */
    private Integer auditCommitFalse;
    
    private String appInputFlagFalse;//非app进件标志
    
    private Integer logoFlag;//标黄标志
    
    private String taskName;//流程节点名称
    
    private Integer creditReportFalse;//绑定判断,未绑定参数
    
    private Integer limitMin;//特殊分页下限
    private Integer limitMax;//特殊分页上限
    
    private Integer versionMax;//版本号，老数据大于999
  
    
  
    
   
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
	

	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<String> getRtfNodeStateList() {
		return rtfNodeStateList;
	}
	public void setRtfNodeStateList(List<String> rtfNodeStateList) {
		this.rtfNodeStateList = rtfNodeStateList;
	}
	public Integer getAuditCommitFalse() {
		return auditCommitFalse;
	}
	public void setAuditCommitFalse(Integer auditCommitFalse) {
		this.auditCommitFalse = auditCommitFalse;
	}
	public String getAppInputFlagFalse() {
		return appInputFlagFalse;
	}
	public void setAppInputFlagFalse(String appInputFlagFalse) {
		this.appInputFlagFalse = appInputFlagFalse;
	}
	public Integer getLogoFlag() {
		return logoFlag;
	}
	public void setLogoFlag(Integer logoFlag) {
		this.logoFlag = logoFlag;
	}
	public List<String> getConcatRtfNodeStateNoCheckList() {
		return concatRtfNodeStateNoCheckList;
	}
	public void setConcatRtfNodeStateNoCheckList(List<String> concatRtfNodeStateNoCheckList) {
		this.concatRtfNodeStateNoCheckList = concatRtfNodeStateNoCheckList;
	}
	public List<String> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<String> channelList) {
		this.channelList = channelList;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getCreditReportFalse() {
		return creditReportFalse;
	}
	public void setCreditReportFalse(Integer creditReportFalse) {
		this.creditReportFalse = creditReportFalse;
	}
	public Integer getLimitMin() {
		return limitMin;
	}
	public void setLimitMin(Integer limitMin) {
		this.limitMin = limitMin;
	}
	public Integer getLimitMax() {
		return limitMax;
	}
	public void setLimitMax(Integer limitMax) {
		this.limitMax = limitMax;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getVersionMax() {
		return versionMax;
	}
	public void setVersionMax(Integer versionMax) {
		this.versionMax = versionMax;
	}
	public String getStartVersion() {
		return startVersion;
	}
	public void setStartVersion(String startVersion) {
		this.startVersion = startVersion;
	}
	public String getEndVersion() {
		return endVersion;
	}
	public void setEndVersion(String endVersion) {
		this.endVersion = endVersion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getLoanNoEliminateList() {
		return loanNoEliminateList;
	}
	public void setLoanNoEliminateList(List<String> loanNoEliminateList) {
		this.loanNoEliminateList = loanNoEliminateList;
	}

	
}
