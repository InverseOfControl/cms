package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年5月8日
 * @Description:债权导出vo
 */
public class ResLoanExpVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6177827509777679749L;

	/**
	 * 借款ID
	 */
	private String loanNo;

	/**
	 * 合同编号
	 */
	private String contractId;

	/**
	 * 产品类型
	 */
	private String productName;

	/**
	 * 借款用途
	 */
	private String creditAppliction;

	/**
	 * 合同金额
	 */
	private String pactMonney;

	/**
	 * 借款期限
	 */
	private String loanLmt;

	/**
	 * 已还期数
	 */
	private String payLmt;

	/**
	 * 首还款日期
	 */
	private String loanDate;

	/**
	 * 真实姓名
	 */
	private String name;

	/**
	 * 性别
	 */
	private String age;

	/**
	 * 身份证号
	 */
	private String idNo;

	/**
	 * 产品批次号
	 */
	private String projectBacthNum;

	/**
	 * 放款银行卡开户行
	 */
	private String bankName;

	/**
	 * 支行
	 */
	private String bankFullName;

	/**
	 * 卡号
	 */
	private String account;

	/**
	 * 放款金额，和产品确认为签约金额
	 */
	private String grantMonney;

	/**
	 * 服务费金额
	 */
	private String serviceMonney;

	/**
	 * 借款签约日期
	 */
	private String signDate;
	
	/**
	 * 到期日
	 */
	private String dueDate;

	/**
	 * 每月还款金额
	 */
	private String returneterm;

	/**
	 * 放款营业部
	 */
	private String loanBranck;
	
	/**
	 * 性别
	 * */
	private String sex;

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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


	public String getPayLmt() {
		return payLmt;
	}

	public void setPayLmt(String payLmt) {
		this.payLmt = payLmt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getProjectBacthNum() {
		return projectBacthNum;
	}

	public void setProjectBacthNum(String projectBacthNum) {
		this.projectBacthNum = projectBacthNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getGrantMonney() {
		return grantMonney;
	}

	public void setGrantMonney(String grantMonney) {
		this.grantMonney = grantMonney;
	}

	public String getServiceMonney() {
		return serviceMonney;
	}

	public void setServiceMonney(String serviceMonney) {
		this.serviceMonney = serviceMonney;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getReturneterm() {
		return returneterm;
	}

	public void setReturneterm(String returneterm) {
		this.returneterm = returneterm;
	}

	public String getLoanBranck() {
		return loanBranck;
	}

	public void setLoanBranck(String loanBranck) {
		this.loanBranck = loanBranck;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCreditAppliction() {
		return creditAppliction;
	}

	public void setCreditAppliction(String creditAppliction) {
		this.creditAppliction = creditAppliction;
	}

	public String getPactMonney() {
		return pactMonney;
	}

	public void setPactMonney(String pactMonney) {
		this.pactMonney = pactMonney;
	}

	public String getLoanLmt() {
		return loanLmt;
	}

	public void setLoanLmt(String loanLmt) {
		this.loanLmt = loanLmt;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	

}
