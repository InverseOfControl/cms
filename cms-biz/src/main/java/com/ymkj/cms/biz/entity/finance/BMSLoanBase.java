package com.ymkj.cms.biz.entity.finance;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;

public class BMSLoanBase  extends BMSProductBaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2930174135624993952L;

	private Long id ; 
	private String loanNo; //借款编号
	private String channelName;		// 渠道名称
	private String	productName;// 产品名称
	private String channelCd;		// 渠道名称
	private String	productCd;// 产品名称
	private BigDecimal contractLmt  ; // 签约额度
	private Integer contractTrem; // 签约期限
	private String	name;// 客户姓名
	private String	idNo;// 身份证号码
	private String applyBankName;//银行名称
	private String applyBankBranch ; // 开户银行
	private String applyBankCardNo ; //银行卡号
	private String  branchManagerCode;
	private String bankPhone;  	// 手机号码
	private String   contractBranchId;	 //  签约网点
	private String owningBranchId; //进件营业部ID
	private String	contractBranch;	 //  签约网点
	
	private String loanBranch;    // <!-- 放款营业部 -->
	private String loanBranchId;  // <!-- 放款营业部ID -->
	private String manageBranch;    // <!-- 管理营业部 -->
	private String manageBranchId ;// <!-- 管理营业部ID -->
	
	
	private String	applyType;		  //申请类型
	private String	applyInputFlag;	   //     交件类型*/
	private String state;
	private String status ; //申请件状态
	private String rtfState ; //流程状态
	private String rtfNodeState ; //流程状态
	private String lockTarget;
	private Long taskId ;
	private String userCode;

	private String serviceCode;
	private String batchNum;
	private String signDate;
	private Date loanDate; //放款时间
	private String message;//请求返回信息
	private String oprateType;//操作方式
	private String ifPri;
	
	private Date financeAuditTime;
	/**
	 *  APP进件标识     app_input:进件
	 */
	private String appInputFlag;
	/**
	 * 是否疑似欺诈  Y 是  N否
	 */
	private String ifSuspectCheat;
	
	//export
	private String pactMoney;
	private String grantMoney;
	private String bank;
	private String bankFullName;
	private String account;
	private String contractNum;
	private String startrdate;
	private String endrdate;
	private String referRate;
	private String manageRate;
	private String managerRateForPartyc;
	private String managerRateForPartyd;
	private String evalRate;
	private String risk;
	private String rateSum;
	private String confirmEndDate;
	private String financeAuditTimeStr;
	
	private String orgAuditState;
	
	//是否优惠费率用户  Y 是  N 否
    private String ifPreferentialUser;
	
	public BMSLoanBase() {
		super();
	}

	//借款系统节点状态封装
	public BMSLoanBase(Long id,String rtfState, String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
		this.rtfState = rtfState;
		this.id = id;
	}
	//更新核心借款状态封装数据
	public BMSLoanBase(String loanNo, String userCode,String state) {
		this.loanNo = loanNo;
		this.userCode = userCode;
		this.state = state;
	}
	
	
	
	public BMSLoanBase(ReqLoanVo reqLoanVo) {
		this.rtfNodeState = reqLoanVo.getRtfNodeState();
		this.rtfState = reqLoanVo.getRtfState();
		this.status = reqLoanVo.getStatus();
		this.loanNo=reqLoanVo.getLoanNo();
		this.id=reqLoanVo.getId();
	}
	
	
	
	public String getLoanBranch() {
		return loanBranch;
	}

	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}

	public String getLoanBranchId() {
		return loanBranchId;
	}

	public void setLoanBranchId(String loanBranchId) {
		this.loanBranchId = loanBranchId;
	}

	public String getManageBranch() {
		return manageBranch;
	}

	public void setManageBranch(String manageBranch) {
		this.manageBranch = manageBranch;
	}

	public String getManageBranchId() {
		return manageBranchId;
	}

	public void setManageBranchId(String manageBranchId) {
		this.manageBranchId = manageBranchId;
	}

	public String getIfPri() {
		return ifPri;
	}

	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}

	public String getBranchManagerCode() {
		return branchManagerCode;
	}

	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	public String getApplyBankName() {
		return applyBankName;
	}
	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}
	public String getApplyBankBranch() {
		return applyBankBranch;
	}
	public void setApplyBankBranch(String applyBankBranch) {
		this.applyBankBranch = applyBankBranch;
	}
	public String getApplyBankCardNo() {
		return applyBankCardNo;
	}
	public void setApplyBankCardNo(String applyBankCardNo) {
		this.applyBankCardNo = applyBankCardNo;
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	
	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
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

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getLockTarget() {
		return lockTarget;
	}

	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOprateType() {
		return oprateType;
	}

	public void setOprateType(String oprateType) {
		this.oprateType = oprateType;
	}

	public String getAppInputFlag() {
		return appInputFlag;
	}

	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}

	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}

	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}

	public String getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getFinanceAuditTime() {
		return financeAuditTime;
	}

	public void setFinanceAuditTime(Date financeAuditTime) {
		this.financeAuditTime = financeAuditTime;
	}

	public String getPactMoney() {
		return pactMoney;
	}

	public void setPactMoney(String pactMoney) {
		this.pactMoney = pactMoney;
	}

	public String getGrantMoney() {
		return grantMoney;
	}

	public void setGrantMoney(String grantMoney) {
		this.grantMoney = grantMoney;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStartrdate() {
		return startrdate;
	}

	public void setStartrdate(String startrdate) {
		this.startrdate = startrdate;
	}

	public String getEndrdate() {
		return endrdate;
	}

	public void setEndrdate(String endrdate) {
		this.endrdate = endrdate;
	}

	public String getReferRate() {
		return referRate;
	}

	public void setReferRate(String referRate) {
		this.referRate = referRate;
	}

	public String getManageRate() {
		return manageRate;
	}

	public void setManageRate(String manageRate) {
		this.manageRate = manageRate;
	}

	public String getManagerRateForPartyc() {
		return managerRateForPartyc;
	}

	public void setManagerRateForPartyc(String managerRateForPartyc) {
		this.managerRateForPartyc = managerRateForPartyc;
	}

	public String getManagerRateForPartyd() {
		return managerRateForPartyd;
	}

	public void setManagerRateForPartyd(String managerRateForPartyd) {
		this.managerRateForPartyd = managerRateForPartyd;
	}

	public String getEvalRate() {
		return evalRate;
	}

	public void setEvalRate(String evalRate) {
		this.evalRate = evalRate;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getRateSum() {
		return rateSum;
	}

	public void setRateSum(String rateSum) {
		this.rateSum = rateSum;
	}

	public String getConfirmEndDate() {
		return confirmEndDate;
	}

	public void setConfirmEndDate(String confirmEndDate) {
		this.confirmEndDate = confirmEndDate;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getFinanceAuditTimeStr() {
		return financeAuditTimeStr;
	}

	public void setFinanceAuditTimeStr(String financeAuditTimeStr) {
		this.financeAuditTimeStr = financeAuditTimeStr;
	}

	public String getOrgAuditState() {
		return orgAuditState;
	}

	public void setOrgAuditState(String orgAuditState) {
		this.orgAuditState = orgAuditState;
	}

	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	

}
