package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;

public class ResCheckNewDataVO implements Serializable{
	private String loanNo;//借款编号
	private String ifHaveValidate;//是否有借款
	private String name;//姓名
	private String IdNo;//身份证
	private String ifOldOrNewLogo;//是否新客户 
	private String ifLoanAgain;//是否结清再贷
	private String productCd;//借款产品 
	private String productName;//产品名称
	private String contractSource;//合同来源
	private String ContractSourceName;//合同来源名称
	private String applyType;//申请类型
	private Integer applyLmt;//申请额度 
	private Integer applyTerm;//审批期限 
	private Date applyDate;//申请时间 
	private Double accLmt;//审批额度
	private Double contractLmt;//合同金额
	private Date signDate;//签约时间 
	private Integer contractTrem;//合同期限
	private Date repayDate;//还款日期
	private String applyBankName;//所属银行
	private String applyBankCode;//所属银行ID
	private String applyBankCardNo;//银行卡号
	private String previousStatus;//借款状态
	private String previousRtfState;//借款环节
	private String owningBranch;//进件门店
	private String owningBranchCode;//进件门店编码
	private String contractBranch;//签约营业部
	private String contractBranchCode;//签约营业部编码
	private String loanBranch;//放款营业部
	private String loanBranchCode;//放款营业部编码
	private String ifRefuse;//借款是否被拒
	private Date refuseDate;//借款被拒时间 
	private String RefuseCode;//拒绝原因码
	private String RefuseName;//拒绝原因
	private Integer limitDays;//限制天数 
	private Integer protectDays;//保护天数
	private String ifGreyOrBlack;//是否灰名单或者黑名单
	private String appApplyInput;//是否APP进件
	
	private String returnMoneyStatu;//结清状态
	public String getIfHaveValidate() {
		return ifHaveValidate;
	}
	public void setIfHaveValidate(String ifHaveValidate) {
		this.ifHaveValidate = ifHaveValidate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return IdNo;
	}
	public void setIdNo(String idNo) {
		IdNo = idNo;
	}
	public String getIfOldOrNewLogo() {
		return ifOldOrNewLogo;
	}
	public void setIfOldOrNewLogo(String ifOldOrNewLogo) {
		this.ifOldOrNewLogo = ifOldOrNewLogo;
	}
	public String getIfLoanAgain() {
		return ifLoanAgain;
	}
	public void setIfLoanAgain(String ifLoanAgain) {
		this.ifLoanAgain = ifLoanAgain;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getContractSource() {
		return contractSource;
	}
	public void setContractSource(String contractSource) {
		this.contractSource = contractSource;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public Integer getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(Integer applyLmt) {
		this.applyLmt = applyLmt;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Double getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(Double accLmt) {
		this.accLmt = accLmt;
	}
	public Double getContractLmt() {
		return contractLmt;
	}
	public void setContractLmt(Double contractLmt) {
		this.contractLmt = contractLmt;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Integer getContractTrem() {
		return contractTrem;
	}
	public void setContractTrem(Integer contractTrem) {
		this.contractTrem = contractTrem;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public String getApplyBankName() {
		return applyBankName;
	}
	public void setApplyBankName(String applyBankName) {
		this.applyBankName = applyBankName;
	}
	public String getApplyBankCardNo() {
		return applyBankCardNo;
	}
	public void setApplyBankCardNo(String applyBankCardNo) {
		this.applyBankCardNo = applyBankCardNo;
	}
	public String getPreviousStatus() {
		return previousStatus;
	}
	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}
	public String getPreviousRtfState() {
		return previousRtfState;
	}
	public void setPreviousRtfState(String previousRtfState) {
		this.previousRtfState = previousRtfState;
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
	public String getLoanBranch() {
		return loanBranch;
	}
	public void setLoanBranch(String loanBranch) {
		this.loanBranch = loanBranch;
	}
	public String getIfRefuse() {
		return ifRefuse;
	}
	public void setIfRefuse(String ifRefuse) {
		this.ifRefuse = ifRefuse;
	}
	public Date getRefuseDate() {
		return refuseDate;
	}
	public void setRefuseDate(Date refuseDate) {
		this.refuseDate = refuseDate;
	}
	public String getRefuseCode() {
		return RefuseCode;
	}
	public void setRefuseCode(String refuseCode) {
		RefuseCode = refuseCode;
	}
	public Integer getLimitDays() {
		return limitDays;
	}
	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}
	public Integer getProtectDays() {
		return protectDays;
	}
	public void setProtectDays(Integer protectDays) {
		this.protectDays = protectDays;
	}
	public String getIfGreyOrBlack() {
		return ifGreyOrBlack;
	}
	public void setIfGreyOrBlack(String ifGreyOrBlack) {
		this.ifGreyOrBlack = ifGreyOrBlack;
	}
	public String getAppApplyInput() {
		return appApplyInput;
	}
	public void setAppApplyInput(String appApplyInput) {
		this.appApplyInput = appApplyInput;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getContractSourceName() {
		return ContractSourceName;
	}
	public void setContractSourceName(String contractSourceName) {
		ContractSourceName = contractSourceName;
	}
	public String getApplyBankCode() {
		return applyBankCode;
	}
	public void setApplyBankCode(String applyBankCode) {
		this.applyBankCode = applyBankCode;
	}
	public String getOwningBranchCode() {
		return owningBranchCode;
	}
	public void setOwningBranchCode(String owningBranchCode) {
		this.owningBranchCode = owningBranchCode;
	}
	public String getContractBranchCode() {
		return contractBranchCode;
	}
	public void setContractBranchCode(String contractBranchCode) {
		this.contractBranchCode = contractBranchCode;
	}
	public String getLoanBranchCode() {
		return loanBranchCode;
	}
	public void setLoanBranchCode(String loanBranchCode) {
		this.loanBranchCode = loanBranchCode;
	}
	public String getRefuseName() {
		return RefuseName;
	}
	public void setRefuseName(String refuseName) {
		RefuseName = refuseName;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getReturnMoneyStatu() {
		return returnMoneyStatu;
	}
	public void setReturnMoneyStatu(String returnMoneyStatu) {
		this.returnMoneyStatu = returnMoneyStatu;
	}
	
	
	
	

}
