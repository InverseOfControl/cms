package com.ymkj.cms.biz.entity.sign;


import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;

public class BMSContractLoan extends BMSProductBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6913709511444832589L;
	private Long loanId;
	private String loanNo;
	private Long loanBaseId;
	private String pactMoney;   //借款金额
	private String thirdPartyId;//华澳信托:甲方指定其在畅捷支付开设的虚拟账户
	private String managerRateForPartyCFinance; //积木盒子：融资服务费
	private String managerRateForPartyCTechnology; //积木盒子：技术服务费
	private String rateEM;//贷款月利率
	private Long time;//贷款期限
	private String MonthlyRepayment;//月偿还本息数额

	private String repaymentWay;//华澳信托:还款方式
	private String huaAoThirdPartyId;//华澳信托在畅捷支付开设的虚拟账户

	private String firstLegalRepresentative;
	private String firstAddress;
	private String firstPostcode;
	private String thirdLegalRepresentative;
	private String thirdAddress;
	private String thirdPostcode;

	private String mphone;//国民信托：乙方对应的：手机号码
	private String peiOuName;//国民信托：乙方对应的：配偶姓名
	private String repaymentTotal;//国民信托：需偿还的贷款本息总额
	private String loanType;//国民信托：贷款类型

	private  String corpName;//国民信托、华澳信托 公司名称
	private String corpBankName;//国民信托和华澳信托 开户银行
	private String corpBankAccount;//国民信托和华澳信托 公司账号
    
	private  String grantMoney;//放款金额
	private String accrualem ;//补偿利率
	private String rateey;//年化利率
	private String rate;//借款费率
	
	public String getPactMoney() {
		return pactMoney;
	}
	public void setPactMoney(String pactMoney) {
		this.pactMoney = pactMoney;
	}
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public String getManagerRateForPartyCFinance() {
		return managerRateForPartyCFinance;
	}
	public void setManagerRateForPartyCFinance(String managerRateForPartyCFinance) {
		this.managerRateForPartyCFinance = managerRateForPartyCFinance;
	}
	public String getManagerRateForPartyCTechnology() {
		return managerRateForPartyCTechnology;
	}
	public void setManagerRateForPartyCTechnology(String managerRateForPartyCTechnology) {
		this.managerRateForPartyCTechnology = managerRateForPartyCTechnology;
	}
	public String getRateEM() {
		return rateEM;
	}
	public void setRateEM(String rateEM) {
		this.rateEM = rateEM;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getMonthlyRepayment() {
		return MonthlyRepayment;
	}
	public void setMonthlyRepayment(String monthlyRepayment) {
		MonthlyRepayment = monthlyRepayment;
	}
	public String getRepaymentWay() {
		return repaymentWay;
	}
	public void setRepaymentWay(String repaymentWay) {
		this.repaymentWay = repaymentWay;
	}
	public String getHuaAoThirdPartyId() {
		return huaAoThirdPartyId;
	}
	public void setHuaAoThirdPartyId(String huaAoThirdPartyId) {
		this.huaAoThirdPartyId = huaAoThirdPartyId;
	}
	public String getFirstLegalRepresentative() {
		return firstLegalRepresentative;
	}
	public void setFirstLegalRepresentative(String firstLegalRepresentative) {
		this.firstLegalRepresentative = firstLegalRepresentative;
	}
	public String getFirstAddress() {
		return firstAddress;
	}
	public void setFirstAddress(String firstAddress) {
		this.firstAddress = firstAddress;
	}
	public String getFirstPostcode() {
		return firstPostcode;
	}
	public void setFirstPostcode(String firstPostcode) {
		this.firstPostcode = firstPostcode;
	}
	public String getThirdLegalRepresentative() {
		return thirdLegalRepresentative;
	}
	public void setThirdLegalRepresentative(String thirdLegalRepresentative) {
		this.thirdLegalRepresentative = thirdLegalRepresentative;
	}
	public String getThirdAddress() {
		return thirdAddress;
	}
	public void setThirdAddress(String thirdAddress) {
		this.thirdAddress = thirdAddress;
	}
	public String getThirdPostcode() {
		return thirdPostcode;
	}
	public void setThirdPostcode(String thirdPostcode) {
		this.thirdPostcode = thirdPostcode;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getPeiOuName() {
		return peiOuName;
	}
	public void setPeiOuName(String peiOuName) {
		this.peiOuName = peiOuName;
	}
	public String getRepaymentTotal() {
		return repaymentTotal;
	}
	public void setRepaymentTotal(String repaymentTotal) {
		this.repaymentTotal = repaymentTotal;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpBankName() {
		return corpBankName;
	}
	public void setCorpBankName(String corpBankName) {
		this.corpBankName = corpBankName;
	}
	public String getCorpBankAccount() {
		return corpBankAccount;
	}
	public void setCorpBankAccount(String corpBankAccount) {
		this.corpBankAccount = corpBankAccount;
	}
	public String getGrantMoney() {
		return grantMoney;
	}
	public void setGrantMoney(String grantMoney) {
		this.grantMoney = grantMoney;
	}
	public String getAccrualem() {
		return accrualem;
	}
	public void setAccrualem(String accrualem) {
		this.accrualem = accrualem;
	}
	public String getRateey() {
		return rateey;
	}
	public void setRateey(String rateey) {
		this.rateey = rateey;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
	
	
}
