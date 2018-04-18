package com.ymkj.cms.biz.entity.sign;


import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;

public class BMSLoanBaseEntity extends BMSProductBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2261178042205755664L;
	
    private Long id; // LOAN_BASE_ID
    private Long taskId; //当前任务ID
    private Long loanId;
    private String loanNo;
    private String name;
    private String idNo;
    private String channelName;
    private String productName;
    
    private BigDecimal accLmt;
    private Integer accTerm;
    
	private BigDecimal contractLmt ;       // '合同金额',
	private Integer contractTrem;        // '合同期限',
	
    private String owningBranch;
    private String contractBranch;
    
    private String owningBranchId;
    private String contractBranchId;
    
    private String signCode;
    private String signName;
    private String signDate;
    private String signEndDate;
    private String signEndDate2;
    private String confirmDate;
    private String confirmEndDate;
    
	private String bankPhone;  //银行预留手机号
	private String applyBankCardNo;  //银行卡号	 
	private String applyBankBranch;  //开户行
	private String applyBankName;  //所属银行
	
	private String applyBankNameId;
	private String applyBankBranchId; 
	
	private String  reportId;
    
    private String taskName;

    private String  productId;
    
    private String channelId;
    
    private String channelCd;

    private String  productCd;
    
    private String manageBranchId;

    private String state;
    
    private String status;
    
	private String userCode;

	private String rtfNodeState; //xiao流程状态
	
	private String rtfState; 
	
	private String lockTarget;//锁定标的,有值且为Y则锁定
	
	private String  oprateFlag; //是否可以操作标识
    
	private String  applyInputFlag;
	
	private String signOprateType; //签约处理类型
	
	private String confirmOprateType; //签约处理类型
	
	
	private String cellphone; //手机1
	
	private String cellphoneSec; //手机2
	
	private Long pversion;
	
	private String	appInputFlag;
	
	private Integer ifPri;
	
	private String managerCode;
	
	private String managerName;
    
	private String taskCreatedate;
	
	private BigDecimal adjustBase;
	
	private Long logoFlag;
	
	
	private Integer ifNeedPatchBolt;
	
	private Date patchBoltTime;
	/**
	 * 合同类型
	 */
	private Long contractType;

	private String remark;

	//包银的状态值
	private String busNumber;//包银业务流水申请号
	private String byState;//包银状态值 0黑名单拒绝 1黑名单通过 2银行卡鉴权拒绝 3鉴权通过 4风控信息推送失败 5风控信息推送成功
	private String byRefusalResult;//包银黑名单拒绝原因
	private String windControlDate;//授信信息推送时间
	private String windControlResult;//风控结果
	private String auditingState;//'人工审核状态 0审核中 1通过  2拒绝 3是图像资料待上传 4是补件';

	private String applyType;//申请件类型--客户类型 NEW TOPUP等
	private String lujsName;//陆金所注册用户名
	private String lujsLoanReqId;//陆金所id
	private Date idLastDate;//证件到期日
	private String lujsApplyNo;
	
	private Date auditSubmitTime;
	
	private String orgAuditState;
	private String isReturn; //是否退回
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private Integer channelPushFrequency; //渠道推送次数，默认为0
	
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}

	public String getLujsApplyNo() {
		return lujsApplyNo;
	}

	public void setLujsApplyNo(String lujsApplyNo) {
		this.lujsApplyNo = lujsApplyNo;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BMSLoanBaseEntity() {
		super();
	}
	
	public BMSLoanBaseEntity(Long id, String rtfNodeState,Long version) {
		this.rtfNodeState = rtfNodeState;
		this.id = id;
		this.setVersion(version);
	}
	
	//借款系统节点状态封装
	public BMSLoanBaseEntity(Long id,String rtfState, String rtfNodeState,Long version) {
		this.rtfNodeState = rtfNodeState;
		this.rtfState = rtfState;
		this.id = id;
		this.setVersion(version);
	}
	//借款系统节点状态封装
	public BMSLoanBaseEntity(Long id,String rtfState, String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
		this.rtfState = rtfState;
		this.id = id;

	}
	
	//更新核心借款状态封装数据
	public BMSLoanBaseEntity(String loanNo, String userCode,String state) {
		this.loanNo = loanNo;
		this.userCode = userCode;
		this.state = state;
	}

	public BMSLoanBaseEntity(Long id,String rtfState, String rtfNodeState,Long version,String managerName,String managerCode) {
		this.rtfNodeState = rtfNodeState;
		this.rtfState = rtfState;
		this.id = id;
		this.managerCode=managerCode;
		this.managerName=managerName;
		this.setVersion(version);
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

	public String getSignEndDate2() {
		return signEndDate2;
	}

	public void setSignEndDate2(String signEndDate2) {
		this.signEndDate2 = signEndDate2;
	}

	public String getApplyInputFlag() {
		return applyInputFlag;
	}

	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
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

	public String getApplyBankBranch() {
		return applyBankBranch;
	}

	public void setApplyBankBranch(String applyBankBranch) {
		this.applyBankBranch = applyBankBranch;
	}

	public String getApplyBankName() {
		return applyBankName;
	}

	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getLoanId() {
		return loanId;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}



	public BigDecimal getAccLmt() {
		return accLmt;
	}

	public void setAccLmt(BigDecimal accLmt) {
		this.accLmt = accLmt;
	}

	public Integer getAccTerm() {
		return accTerm;
	}

	public void setAccTerm(Integer accTerm) {
		this.accTerm = accTerm;
	}

	public BigDecimal getContractLmt() {
		return contractLmt;
	}

	public void setContractLmt(BigDecimal contractLmt) {
		this.contractLmt = contractLmt;
	}



	public Integer getContractTrem() {
		return contractTrem;
	}

	public void setContractTrem(Integer contractTrem) {
		this.contractTrem = contractTrem;
	}

	public String getOwningBranch() {
		return owningBranch;
	}

	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
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



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRtfNodeState() {
		return rtfNodeState;
	}

	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}

	

	public String getChannelCd() {
		return channelCd;
	}

	public void setChannelCd(String channelCd) {
		this.channelCd = channelCd;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getManageBranchId() {
		return manageBranchId;
	}

	public void setManageBranchId(String manageBranchId) {
		this.manageBranchId = manageBranchId;
	}

	public String getApplyBankNameId() {
		return applyBankNameId;
	}

	public void setApplyBankNameId(String applyBankNameId) {
		this.applyBankNameId = applyBankNameId;
	}

	public String getApplyBankBranchId() {
		return applyBankBranchId;
	}

	public void setApplyBankBranchId(String applyBankBranchId) {
		this.applyBankBranchId = applyBankBranchId;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public String getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}

	public Long getPversion() {
		return pversion;
	}

	public void setPversion(Long pversion) {
		this.pversion = pversion;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOprateFlag() {
		return oprateFlag;
	}

	public void setOprateFlag(String oprateFlag) {
		this.oprateFlag = oprateFlag;
	}

	public String getSignOprateType() {
		return signOprateType;
	}

	public void setSignOprateType(String signOprateType) {
		this.signOprateType = signOprateType;
	}

	public String getConfirmOprateType() {
		return confirmOprateType;
	}

	public void setConfirmOprateType(String confirmOprateType) {
		this.confirmOprateType = confirmOprateType;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	public String getAppInputFlag() {
		return appInputFlag;
	}

	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}

	public Integer getIfPri() {
		return ifPri;
	}

	public void setIfPri(Integer ifPri) {
		this.ifPri = ifPri;
	}

	public String getTaskCreatedate() {
		return taskCreatedate;
	}

	public void setTaskCreatedate(String taskCreatedate) {
		this.taskCreatedate = taskCreatedate;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCellphoneSec() {
		return cellphoneSec;
	}

	public void setCellphoneSec(String cellphoneSec) {
		this.cellphoneSec = cellphoneSec;
	}

	public BigDecimal getAdjustBase() {
		return adjustBase;
	}

	public void setAdjustBase(BigDecimal adjustBase) {
		this.adjustBase = adjustBase;
	}

	public Long getContractType() {
		return contractType;
	}

	public void setContractType(Long contractType) {
		this.contractType = contractType;
	}

	public Long getLogoFlag() {
		return logoFlag;
	}

	public void setLogoFlag(Long logoFlag) {
		this.logoFlag = logoFlag;
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

	public Date getIdLastDate() {
		return idLastDate;
	}

	public void setIdLastDate(Date idLastDate) {
		this.idLastDate = idLastDate;
	}

	public Date getAuditSubmitTime() {
		return auditSubmitTime;
	}

	public void setAuditSubmitTime(Date auditSubmitTime) {
		this.auditSubmitTime = auditSubmitTime;
	}

	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public String getOrgAuditState(){
		return orgAuditState;
	}

	public void setOrgAuditState(String orgAuditState) {
		this.orgAuditState = orgAuditState;
	}



	public Integer getIfNeedPatchBolt() {
		return ifNeedPatchBolt;
	}

	public void setIfNeedPatchBolt(Integer ifNeedPatchBolt) {
		this.ifNeedPatchBolt = ifNeedPatchBolt;
	}

	public Date getPatchBoltTime() {
		return patchBoltTime;
	}

	public void setPatchBoltTime(Date patchBoltTime) {
		this.patchBoltTime = patchBoltTime;
	}
	public Integer getChannelPushFrequency() {
		return channelPushFrequency;
	}
	public void setChannelPushFrequency(Integer channelPushFrequency) {
		this.channelPushFrequency = channelPushFrequency;
	}



	

}
