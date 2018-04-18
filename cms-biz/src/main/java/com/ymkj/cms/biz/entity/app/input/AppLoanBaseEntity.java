package com.ymkj.cms.biz.entity.app.input;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppLoanBaseEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
	private Long id ; //主键
	private Long personId; //借款人ID
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String status ; //申请件状态
	private String rtfState ; //流程状态
	private String rtfNodeState; //节点状态
	private String branchManagerCode; //客户经理Code
	private String branchManagerName; //客户经理名称
	private String serviceCode;//客服code
	private String serviceName;//客服名字
	private String remark; //备注
	private String contractBranch; //签约营业部
	private Long contractBranchId; //签约营业部ID
	private Long owningBranchId; //进件门店ID
	private String owningBranch; //进件门店
	private String applyType; //申请类型
	private String applyInputFlag; //申请件渠道标识
	private String appInputFlag; //APP进件标识
	private Date appApplyDate; //aPP进件申请时间
	private Long creatorId; //创建人ID 
	private String creator; //创建人 
	private Date createdTime; //创建时间 
	private Integer isDelete; //是否删除 
	private Long verson; //版本
	private String name;//姓名
	private String idNo;//身份证ID
	private String isAppClaim;//是否发生认领
	private String groupForDirector; //业务组
	private String groupForDirectorId; //业务组ID
	private String director; //业务主任
	private String directorCode; //业务主任Code
	
	public AppLoanBaseEntity(Long personId,String loanNo,Long creatorId,String creator){
		this.personId = personId;
		this.loanNo = loanNo;
		this.creatorId = creatorId;
		this.creator = creator;
	}
	
	
	public AppLoanBaseEntity() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public Long getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(Long contractBranchId) {
		this.contractBranchId = contractBranchId;
	}
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
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
	public Date getAppApplyDate() {
		return appApplyDate;
	}
	public void setAppApplyDate(Date appApplyDate) {
		this.appApplyDate = appApplyDate;
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
	public Long getVerson() {
		return verson;
	}
	public void setVerson(Long verson) {
		this.verson = verson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIsAppClaim() {
		return isAppClaim;
	}

	public void setIsAppClaim(String isAppClaim) {
		this.isAppClaim = isAppClaim;
	}

	public String getGroupForDirector() {
		return groupForDirector;
	}

	public void setGroupForDirector(String groupForDirector) {
		this.groupForDirector = groupForDirector;
	}

	public String getGroupForDirectorId() {
		return groupForDirectorId;
	}

	public void setGroupForDirectorId(String groupForDirectorId) {
		this.groupForDirectorId = groupForDirectorId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDirectorCode() {
		return directorCode;
	}

	public void setDirectorCode(String directorCode) {
		this.directorCode = directorCode;
	}
	
}
