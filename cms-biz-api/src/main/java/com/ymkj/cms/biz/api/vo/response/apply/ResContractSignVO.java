package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResContractSignVO implements Serializable{

	private String appApplyInput;		 //  是否APP进件   Y是  N否
	private String owningBranchId;	  //进件营业部
	private String owningBranchCity;  //进件营业部城市
	private String owningBranchDivision;	//  进件营业部分部
	private String owningBranchArea;	//  进件营业部区域
	private String applyType;		//  申请类型
	private Double applyTypeLetterRate;//申请类型总负债率 
	private Double applyTypeInsideRate;//申请类型内部负债率  
	private String rtfState; //  申请所处业务环节  
	private String idNo;//身份证号码
	private String nameAndidNoIsBlack;//身份证是否匹配黑名单
	private String approvalProductCd;// 审批产品
	private Double approvalProductRate;//审批产品费率  
	private Double approvalProductTotalDebtRatio;//审批产品总负债率
	private Integer anticipationIncome; //审批意见表核实收入
	private Integer totalCreditLiabilities;//审批意见表信用负债总额
	private Date commitDate;//提交时间
	private Integer approvalLimit;// 信审审批额度
	private String clientManager;//客户经理 
	private Date firstContractSignDate;//首次进入合同签约环节日期
	private String IsPreferredRate;//是否优惠费率
	private Double productPreferredRate;//产品优惠费率
	private String ruleCallLink;//规则调用环节
	private String signBranchId;	  //签约营业部
	private String signBranchCity;  //签约营业部城市
	private String signBranchDivision;	// 签约营业部部分部
	private String signBranchArea;	//  签约营业部区域
	private List<String> systemConfigChannel;//系统配置可选渠道
	private String pageSelectChannel;//页面所选渠道
	private Integer signTerm;//签约期限
	private Integer signLimit;//签约额度 
	private Double ContractManey;//合同金额
	private Double pageSelectCannelContractManey;//页面所选渠道剩余合同金额
	private Double allCannelContractManey;//所有渠道剩余合同金额
	private String bankNo;//银行卡号
	private String bankLikeOthers;//银行卡号是否与他人一致
	
	//--吊核心接口返回
	private String ifHaveBreaks;//上笔结清是否有减免
	private List<Integer> repaymentTerm;//还款期数
	private List<Date> realityRepaymentDate;//实际还款日期
	private List<Date> shouldRepaymentDate;//应还日期
	private List<Integer> repaymentStatue;//还款状态
	
	private List<String> holidays;//	非工作日期		

	public String getAppApplyInput() {
		return appApplyInput;
	}

	public void setAppApplyInput(String appApplyInput) {
		this.appApplyInput = appApplyInput;
	}

	public String getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public String getOwningBranchCity() {
		return owningBranchCity;
	}

	public void setOwningBranchCity(String owningBranchCity) {
		this.owningBranchCity = owningBranchCity;
	}

	public String getOwningBranchDivision() {
		return owningBranchDivision;
	}

	public void setOwningBranchDivision(String owningBranchDivision) {
		this.owningBranchDivision = owningBranchDivision;
	}

	public String getOwningBranchArea() {
		return owningBranchArea;
	}

	public void setOwningBranchArea(String owningBranchArea) {
		this.owningBranchArea = owningBranchArea;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public Double getApplyTypeLetterRate() {
		return applyTypeLetterRate;
	}

	public void setApplyTypeLetterRate(Double applyTypeLetterRate) {
		this.applyTypeLetterRate = applyTypeLetterRate;
	}

	public Double getApplyTypeInsideRate() {
		return applyTypeInsideRate;
	}

	public void setApplyTypeInsideRate(Double applyTypeInsideRate) {
		this.applyTypeInsideRate = applyTypeInsideRate;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getNameAndidNoIsBlack() {
		return nameAndidNoIsBlack;
	}

	public void setNameAndidNoIsBlack(String nameAndidNoIsBlack) {
		this.nameAndidNoIsBlack = nameAndidNoIsBlack;
	}

	public String getApprovalProductCd() {
		return approvalProductCd;
	}

	public void setApprovalProductCd(String approvalProductCd) {
		this.approvalProductCd = approvalProductCd;
	}

	public Double getApprovalProductRate() {
		return approvalProductRate;
	}

	public void setApprovalProductRate(Double approvalProductRate) {
		this.approvalProductRate = approvalProductRate;
	}

	public Double getApprovalProductTotalDebtRatio() {
		return approvalProductTotalDebtRatio;
	}

	public void setApprovalProductTotalDebtRatio(Double approvalProductTotalDebtRatio) {
		this.approvalProductTotalDebtRatio = approvalProductTotalDebtRatio;
	}

	public Integer getAnticipationIncome() {
		return anticipationIncome;
	}

	public void setAnticipationIncome(Integer anticipationIncome) {
		this.anticipationIncome = anticipationIncome;
	}

	public Integer getTotalCreditLiabilities() {
		return totalCreditLiabilities;
	}

	public void setTotalCreditLiabilities(Integer totalCreditLiabilities) {
		this.totalCreditLiabilities = totalCreditLiabilities;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public Integer getApprovalLimit() {
		return approvalLimit;
	}

	public void setApprovalLimit(Integer approvalLimit) {
		this.approvalLimit = approvalLimit;
	}

	public String getClientManager() {
		return clientManager;
	}

	public void setClientManager(String clientManager) {
		this.clientManager = clientManager;
	}

	public Date getFirstContractSignDate() {
		return firstContractSignDate;
	}

	public void setFirstContractSignDate(Date firstContractSignDate) {
		this.firstContractSignDate = firstContractSignDate;
	}

	public String getIsPreferredRate() {
		return IsPreferredRate;
	}

	public void setIsPreferredRate(String isPreferredRate) {
		IsPreferredRate = isPreferredRate;
	}

	public Double getProductPreferredRate() {
		return productPreferredRate;
	}

	public void setProductPreferredRate(Double productPreferredRate) {
		this.productPreferredRate = productPreferredRate;
	}

	public String getRuleCallLink() {
		return ruleCallLink;
	}

	public void setRuleCallLink(String ruleCallLink) {
		this.ruleCallLink = ruleCallLink;
	}

	public String getSignBranchId() {
		return signBranchId;
	}

	public void setSignBranchId(String signBranchId) {
		this.signBranchId = signBranchId;
	}

	public String getSignBranchCity() {
		return signBranchCity;
	}

	public void setSignBranchCity(String signBranchCity) {
		this.signBranchCity = signBranchCity;
	}

	public String getSignBranchDivision() {
		return signBranchDivision;
	}

	public void setSignBranchDivision(String signBranchDivision) {
		this.signBranchDivision = signBranchDivision;
	}

	public String getSignBranchArea() {
		return signBranchArea;
	}

	public void setSignBranchArea(String signBranchArea) {
		this.signBranchArea = signBranchArea;
	}

	public List<String> getSystemConfigChannel() {
		return systemConfigChannel;
	}

	public void setSystemConfigChannel(List<String> systemConfigChannel) {
		this.systemConfigChannel = systemConfigChannel;
	}

	public String getPageSelectChannel() {
		return pageSelectChannel;
	}

	public void setPageSelectChannel(String pageSelectChannel) {
		this.pageSelectChannel = pageSelectChannel;
	}

	public Integer getSignTerm() {
		return signTerm;
	}

	public void setSignTerm(Integer signTerm) {
		this.signTerm = signTerm;
	}

	public Integer getSignLimit() {
		return signLimit;
	}

	public void setSignLimit(Integer signLimit) {
		this.signLimit = signLimit;
	}

	public Double getContractManey() {
		return ContractManey;
	}

	public void setContractManey(Double contractManey) {
		ContractManey = contractManey;
	}

	public Double getPageSelectCannelContractManey() {
		return pageSelectCannelContractManey;
	}

	public void setPageSelectCannelContractManey(Double pageSelectCannelContractManey) {
		this.pageSelectCannelContractManey = pageSelectCannelContractManey;
	}

	public Double getAllCannelContractManey() {
		return allCannelContractManey;
	}

	public void setAllCannelContractManey(Double allCannelContractManey) {
		this.allCannelContractManey = allCannelContractManey;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankLikeOthers() {
		return bankLikeOthers;
	}

	public void setBankLikeOthers(String bankLikeOthers) {
		this.bankLikeOthers = bankLikeOthers;
	}

	public String getIfHaveBreaks() {
		return ifHaveBreaks;
	}

	public void setIfHaveBreaks(String ifHaveBreaks) {
		this.ifHaveBreaks = ifHaveBreaks;
	}

	public List<Integer> getRepaymentTerm() {
		return repaymentTerm;
	}

	public void setRepaymentTerm(List<Integer> repaymentTerm) {
		this.repaymentTerm = repaymentTerm;
	}

	public List<Date> getRealityRepaymentDate() {
		return realityRepaymentDate;
	}

	public void setRealityRepaymentDate(List<Date> realityRepaymentDate) {
		this.realityRepaymentDate = realityRepaymentDate;
	}

	public List<Date> getShouldRepaymentDate() {
		return shouldRepaymentDate;
	}

	public void setShouldRepaymentDate(List<Date> shouldRepaymentDate) {
		this.shouldRepaymentDate = shouldRepaymentDate;
	}

	public List<Integer> getRepaymentStatue() {
		return repaymentStatue;
	}

	public void setRepaymentStatue(List<Integer> repaymentStatue) {
		this.repaymentStatue = repaymentStatue;
	}

	public List<String> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<String> holidays) {
		this.holidays = holidays;
	}
	
	
	
	
	
}
