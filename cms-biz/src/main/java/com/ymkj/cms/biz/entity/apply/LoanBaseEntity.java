package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;



/**
 * 实体类 对应 表 demo
 */
public class LoanBaseEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
	private Long id ; 
	private Long personId;
	private String perosnNo;
	private String appNo;
	private String loanNo;
	private String status ; //申请件状态
	private String rtfState ; //流程状态
	private String rtfNodeState;//节点状态
	private Date signDate ; 
	private String branchManagerCode;//客户经理Code
	private String branchManagerName;//客户经理名称
	private String serviceCode;//客服经理code
	private String serviceName;//客服经理名字
	private String remark;
	private String reviewCode;//复核人员Code
	private String reviewName;//复核人员名称
	@NotNull(message = "000001,签约营业部不能为空")
	private String contractBranch;//签约营业部
	@NotNull(message = "000001,签约营业部Id不能为空")
	private Long contractBranchId;
	private String loanBranch;//放款营业部
	private Long loanBranchId;
	private String manageBranch;//管理营业部
	private Long manageBranchId;
	private String manageUpdateDate;//管理营业部变更时间
	private String groupForDirector;//业务组
	private Long groupForDirectorId;
	private String director;//业务主任
	private String directorCode;//业务主任Code
	
	private Date loanDate;//放款时间
	
	
	private Long owningBranchId;//录单门店
	private String owningBranch;//录单门店
	private String owningBranchAttribute;//录单门店属性
	
	private Long enterBranchId;//进件门店（复核保存）
	private String enterBranch;//进件门店（复核保存）
	private String enterBranchAttribute;//进件门店属性（复核保存）
	
	private String applyType;
	private String applyInputFlag;//申请件渠道标识
	
	private String appInputFlag;//APP进件标识
	private Long loanId;
	
	private String lockTarget;
	private Date appApplyDate;//aPP进件申请时间
	private Date applyDate;//申请时间
	
	private String managerCode;//经理code
	private String managerName;//经理名称
	
	private String handleType;//退回类型
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private String state;
	
	private Long verson ;
	
	private Long taskId ;
	
	
	
	private String name;//姓名
	private String idNo;//身份证ID
	
	private String signCode;//签约客服code
	private String signName;//签约客服Name
	
	private Long ifNewLoanNo;//是否新生件

	private String checkNodeState; //复核节点
	
	private BigDecimal minAccLmt; //审批额度最小额度
	
	private BigDecimal maxAccLmt; //审批额度最大额度
	
	private List<Integer> accTerms;//审批期限集合
	
	private  BigDecimal pactMoney;//合同金额
	
	private String sqlrUserCode;//申请录入提交工号
	
	private String initProductName;
	private String initProductCd;
	
	private String  productName;
	private String productCd;

	private Integer logoFlag;//借款前台标识,1(黄色)
		
	public BigDecimal getMinAccLmt() {
		return minAccLmt;
	}

	public void setMinAccLmt(BigDecimal minAccLmt) {
		this.minAccLmt = minAccLmt;
	}

	public BigDecimal getMaxAccLmt() {
		return maxAccLmt;
	}

	public void setMaxAccLmt(BigDecimal maxAccLmt) {
		this.maxAccLmt = maxAccLmt;
	}

	public List<Integer> getAccTerms() {
		return accTerms;
	}

	public void setAccTerms(List<Integer> accTerms) {
		this.accTerms = accTerms;
	}

	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public Long getIfNewLoanNo() {
		return ifNewLoanNo;
	}

	public void setIfNewLoanNo(Long ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}

	public LoanBaseEntity() {
		super();
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

	public LoanBaseEntity(ReqLoanContractSignVo reqLoanContractSignVo) {
		this.rtfState = reqLoanContractSignVo.getRtfState();
		this.status = reqLoanContractSignVo.getStatus();
		this.loanNo=reqLoanContractSignVo.getLoanNo();
		this.id=reqLoanContractSignVo.getId();
		
	}
	public LoanBaseEntity(ReqLoanVo reqLoanVo) {
		this.rtfState = reqLoanVo.getRtfState();
		this.status = reqLoanVo.getStatus();
		this.loanNo=reqLoanVo.getLoanNo();
		this.id=reqLoanVo.getId();
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public Long getId() {
		return id;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}

	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	
	public String getDirectorCode() {
		return directorCode;
	}

	public void setDirectorCode(String directorCode) {
		this.directorCode = directorCode;
	}

	public Long getEnterBranchId() {
		return enterBranchId;
	}

	public void setEnterBranchId(Long enterBranchId) {
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

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPerosnNo() {
		return perosnNo;
	}

	public void setPerosnNo(String perosnNo) {
		this.perosnNo = perosnNo;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	 
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getBranchManagerCode() {
		return branchManagerCode;
	}

	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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

	public String getLoanBranch() {
		return loanBranch;
	}

	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}

	public Long getLoanBranchId() {
		return loanBranchId;
	}

	public void setLoanBranchId(Long loanBranchId) {
		this.loanBranchId = loanBranchId;
	}

	public String getManageBranch() {
		return manageBranch;
	}

	public void setManageBranch(String manageBranch) {
		this.manageBranch = manageBranch;
	}

	public Long getManageBranchId() {
		return manageBranchId;
	}

	public void setManageBranchId(Long manageBranchId) {
		this.manageBranchId = manageBranchId;
	}

	public String getManageUpdateDate() {
		return manageUpdateDate;
	}

	public void setManageUpdateDate(String manageUpdateDate) {
		this.manageUpdateDate = manageUpdateDate;
	}

	public String getGroupForDirector() {
		return groupForDirector;
	}

	public void setGroupForDirector(String groupForDirector) {
		this.groupForDirector = groupForDirector;
	}

	public Long getGroupForDirectorId() {
		return groupForDirectorId;
	}

	public void setGroupForDirectorId(Long groupForDirectorId) {
		this.groupForDirectorId = groupForDirectorId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
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

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}

	public Date getAppApplyDate() {
		return appApplyDate;
	}

	public void setAppApplyDate(Date appApplyDate) {
		this.appApplyDate = appApplyDate;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
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
	public Long getVerson() {
		return verson;
	}
	public void setVerson(Long verson) {
		this.verson = verson;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRtfNodeState() {
		return rtfNodeState;
	}

	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
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

	public String getCheckNodeState() {
		return checkNodeState;
	}

	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}

	public BigDecimal getPactMoney() {
		return pactMoney;
	}

	public void setPactMoney(BigDecimal pactMoney) {
		this.pactMoney = pactMoney;
	}

	public String getSqlrUserCode() {
		return sqlrUserCode;
	}

	public void setSqlrUserCode(String sqlrUserCode) {
		this.sqlrUserCode = sqlrUserCode;
	}

	public Integer getLogoFlag() {
		return logoFlag;
	}

	public void setLogoFlag(Integer logoFlag) {
		this.logoFlag = logoFlag;
	}	

	public String getInitProductName() {
		return initProductName;
	}

	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}

	public String getInitProductCd() {
		return initProductCd;
	}

	public void setInitProductCd(String initProductCd) {
		this.initProductCd = initProductCd;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

}
