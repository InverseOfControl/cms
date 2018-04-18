package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResQueryLoanVo</p>
 * <p>Description:查看借款响应对象</p>
 * @uthor YM10159
 * @date 2017年3月14日 上午10:42:18
 */

public class ResQueryLoanVo extends Request{

	private static final long serialVersionUID = 561204444878610877L;

	/**
	 * 借款单号
	 * 
	 */
	private String loanNo;
	/**
	 * 借款人
	 */
	private String customerName;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 身份证号
	 */
	private String customerIDNO;
	/**
	 * 借款类型
	 */
	private String loanType;
	/**
	 * 所属机构(渠道)
	 */
	private String organizationName;
	/**
	 * 借款方案(渠道)
	 */
	private String planName;
	/**
	 * 申请金额
	 */
	private String requestMoney;
	/**
	 * 申请期限
	 */
	private String contractTerm;
	/**
	 * 申请日期
	 */
	private String requestDate;
	/**
	 * 审批金额
	 */
	private String money;
	/**
	 * 借款期限
	 */
	private String time;
	/**
	 * 可接受的最高月还款额
	 */
	private String maxMonthlyPayment;
	/**
	 * 状态
	 */
	private String loanFlowState;
	/**
	 * 月还款能力
	 */
	private String restoreEM;
	/**
	 * 用途
	 */
	private String purpose;
	/**
	 * 管理营业部
	 */
	private String salesDepartment;
	/**
	 * 还款银行
	 */
	private String giveBackBank;
	/**
	 * 放款银行
	 */
	private String grantBank;
	/**
	 * 客户经理
	 */
	private String branchManagerName;
	/**
	 * 客服
	 */
	private String serviceName;
	/**
	 * 合同来源
	 */
	private String fundsSources;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 剩余未结清期数
	 */
	private String residualTime;
	/**
	 * 放款金额
	 */
	private String grantMoney;
	/**
	 * 结清类型 
	 */
	private String settlementType;
	/**
	 * 借款状态
	 */
	private String status;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 合同金额
	 */
	private String applyLmt;
	/**
	 * 签约金额
	 */
	private String contractLmt;
	
	private String patchBoltUrl;
	
	private String loanState;
	
	private String borrower;
	/**
	 * 客服
	 */
	private String crm;

	private String idnum;
	
	private String requestTime;
	
	private String salesman;
	
	public String getPatchBoltUrl() {
		return patchBoltUrl;
	}
	public void setPatchBoltUrl(String patchBoltUrl) {
		this.patchBoltUrl = patchBoltUrl;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getRequestMoney() {
		return requestMoney;
	}
	public void setRequestMoney(String requestMoney) {
		this.requestMoney = requestMoney;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMaxMonthlyPayment() {
		return maxMonthlyPayment;
	}
	public void setMaxMonthlyPayment(String maxMonthlyPayment) {
		this.maxMonthlyPayment = maxMonthlyPayment;
	}
	public String getLoanFlowState() {
		return loanFlowState;
	}
	public void setLoanFlowState(String loanFlowState) {
		this.loanFlowState = loanFlowState;
	}
	public String getRestoreEM() {
		return restoreEM;
	}
	public void setRestoreEM(String restoreEM) {
		this.restoreEM = restoreEM;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getSalesDepartment() {
		return salesDepartment;
	}
	public void setSalesDepartment(String salesDepartment) {
		this.salesDepartment = salesDepartment;
	}
	public String getGiveBackBank() {
		return giveBackBank;
	}
	public void setGiveBackBank(String giveBackBank) {
		this.giveBackBank = giveBackBank;
	}
	public String getGrantBank() {
		return grantBank;
	}
	public void setGrantBank(String grantBank) {
		this.grantBank = grantBank;
	}
	public String getFundsSources() {
		return fundsSources;
	}
	public void setFundsSources(String fundsSources) {
		this.fundsSources = fundsSources;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResidualTime() {
		return residualTime;
	}
	public void setResidualTime(String residualTime) {
		this.residualTime = residualTime;
	}
	public String getGrantMoney() {
		return grantMoney;
	}
	public void setGrantMoney(String grantMoney) {
		this.grantMoney = grantMoney;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIDNO() {
		return customerIDNO;
	}
	public void setCustomerIDNO(String customerIDNO) {
		this.customerIDNO = customerIDNO;
	}
	public String getContractTerm() {
		return contractTerm;
	}
	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getContractLmt() {
		return contractLmt;
	}
	public void setContractLmt(String contractLmt) {
		this.contractLmt = contractLmt;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
}
