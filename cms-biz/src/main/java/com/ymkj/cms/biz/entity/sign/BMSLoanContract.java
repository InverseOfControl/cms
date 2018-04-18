package com.ymkj.cms.biz.entity.sign;

import java.math.BigDecimal;

import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;


public class BMSLoanContract extends BMSProductBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1607005822357470434L;
	
	private String contractNum;     //合同编号
	private String borrowerName;  //借款者姓名
	private String borrowerName2;  //共同借款人姓名
	private String sex; //性别
	private String idnum;  //借款者证件号码
	private String idnum2;  //共同借款人证件号码
	private String address;  //住址
	private String postcode; //邮编
	private String email;
	private String signDate;   //签约日期
	private String startRDate;  //开始还款日期
	private String endRDate;   //最后还款日期
	private Long time;  //期数
	private String signingSite; //签约地点
	private BigDecimal pactMoney;   //合同金额
	private BigDecimal returnETerm;  //每期还款
	private Long  promiseReturnDate; //约定还款日
	private BigDecimal referRate;  //咨询费
	private  BigDecimal evalRate; //评估费
	private BigDecimal manageRate; //管理费
	private BigDecimal risk; //风险金
	private  BigDecimal managerRateForPartyC; //丙方管理费
    private  BigDecimal rateSum;  //收费合计
    private  BigDecimal giveBackRateFor3Term;  //第三期退费
    private BigDecimal giveBackRateFor4Term;  //第四期退费
    private  BigDecimal giveBackRateAfter4Term; //第四期后每期退费
    private  String serviceTel; //服务电话
    private BigDecimal overduePenalty1Day; //逾期1日罚息
    private  BigDecimal overduePenalty15Day; //  逾期15日罚息
    private  String purpose; //借款用途
    private String contractVersion;  //合同版本
    private String bank;    //银行名称
    private String bankFullName;  //银行支行
    private String account; //银行账号
    private String giveBackBank; //还款银行
    private  String gbFullName; //还款支行
    private  String gbAccount; // 还款账户

    private  BigDecimal returnETermForT1;  //一期月还款
    private   Long timeForT1; //第一期期数
    private  String startRDateForT1; //第一期还款开始日期
    private  String endRDateForT1; //第一期还款结束日期
    private BigDecimal returnETermForT2;  //二期月还款
    private Long timeForT2; //第二期期数
    private  String startRDateForT2; //第一期还款开始日期
    private String endRDateForT2; //第一期还款结束日期
    private  String orgName; //名称
    private  String zhongTaiSequence; //中泰信托合同序列号
    private String xtjhSequence; //信托计划合同序列号

    private String loanNo;
    
    private Long loanId;
    
    private Long loanBaseId;
    
    private String productCd;
    
    BigDecimal giveBackRateFor1Term;  //第一期退费

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerName2() {
		return borrowerName2;
	}

	public void setBorrowerName2(String borrowerName2) {
		this.borrowerName2 = borrowerName2;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getIdnum2() {
		return idnum2;
	}

	public void setIdnum2(String idnum2) {
		this.idnum2 = idnum2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getSigningSite() {
		return signingSite;
	}

	public void setSigningSite(String signingSite) {
		this.signingSite = signingSite;
	}

	public BigDecimal getPactMoney() {
		return pactMoney;
	}

	public void setPactMoney(BigDecimal pactMoney) {
		this.pactMoney = pactMoney;
	}

	public BigDecimal getReturnETerm() {
		return returnETerm;
	}

	public void setReturnETerm(BigDecimal returnETerm) {
		this.returnETerm = returnETerm;
	}

	public Long getPromiseReturnDate() {
		return promiseReturnDate;
	}

	public void setPromiseReturnDate(Long promiseReturnDate) {
		this.promiseReturnDate = promiseReturnDate;
	}

	public BigDecimal getReferRate() {
		return referRate;
	}

	public void setReferRate(BigDecimal referRate) {
		this.referRate = referRate;
	}

	public BigDecimal getEvalRate() {
		return evalRate;
	}

	public void setEvalRate(BigDecimal evalRate) {
		this.evalRate = evalRate;
	}

	public BigDecimal getManageRate() {
		return manageRate;
	}

	public void setManageRate(BigDecimal manageRate) {
		this.manageRate = manageRate;
	}

	public BigDecimal getRisk() {
		return risk;
	}

	public void setRisk(BigDecimal risk) {
		this.risk = risk;
	}

	public BigDecimal getManagerRateForPartyC() {
		return managerRateForPartyC;
	}

	public void setManagerRateForPartyC(BigDecimal managerRateForPartyC) {
		this.managerRateForPartyC = managerRateForPartyC;
	}

	public BigDecimal getRateSum() {
		return rateSum;
	}

	public void setRateSum(BigDecimal rateSum) {
		this.rateSum = rateSum;
	}

	public BigDecimal getGiveBackRateFor3Term() {
		return giveBackRateFor3Term;
	}

	public void setGiveBackRateFor3Term(BigDecimal giveBackRateFor3Term) {
		this.giveBackRateFor3Term = giveBackRateFor3Term;
	}

	public BigDecimal getGiveBackRateFor4Term() {
		return giveBackRateFor4Term;
	}

	public void setGiveBackRateFor4Term(BigDecimal giveBackRateFor4Term) {
		this.giveBackRateFor4Term = giveBackRateFor4Term;
	}

	public BigDecimal getGiveBackRateAfter4Term() {
		return giveBackRateAfter4Term;
	}

	public void setGiveBackRateAfter4Term(BigDecimal giveBackRateAfter4Term) {
		this.giveBackRateAfter4Term = giveBackRateAfter4Term;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public BigDecimal getOverduePenalty1Day() {
		return overduePenalty1Day;
	}

	public void setOverduePenalty1Day(BigDecimal overduePenalty1Day) {
		this.overduePenalty1Day = overduePenalty1Day;
	}

	public BigDecimal getOverduePenalty15Day() {
		return overduePenalty15Day;
	}

	public void setOverduePenalty15Day(BigDecimal overduePenalty15Day) {
		this.overduePenalty15Day = overduePenalty15Day;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
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

	public String getGiveBackBank() {
		return giveBackBank;
	}

	public void setGiveBackBank(String giveBackBank) {
		this.giveBackBank = giveBackBank;
	}

	public String getGbFullName() {
		return gbFullName;
	}

	public void setGbFullName(String gbFullName) {
		this.gbFullName = gbFullName;
	}

	public String getGbAccount() {
		return gbAccount;
	}

	public void setGbAccount(String gbAccount) {
		this.gbAccount = gbAccount;
	}

	public BigDecimal getReturnETermForT1() {
		return returnETermForT1;
	}

	public void setReturnETermForT1(BigDecimal returnETermForT1) {
		this.returnETermForT1 = returnETermForT1;
	}

	public Long getTimeForT1() {
		return timeForT1;
	}

	public void setTimeForT1(Long timeForT1) {
		this.timeForT1 = timeForT1;
	}

	

	public BigDecimal getReturnETermForT2() {
		return returnETermForT2;
	}

	public void setReturnETermForT2(BigDecimal returnETermForT2) {
		this.returnETermForT2 = returnETermForT2;
	}

	public Long getTimeForT2() {
		return timeForT2;
	}

	public void setTimeForT2(Long timeForT2) {
		this.timeForT2 = timeForT2;
	}


	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getZhongTaiSequence() {
		return zhongTaiSequence;
	}

	public void setZhongTaiSequence(String zhongTaiSequence) {
		this.zhongTaiSequence = zhongTaiSequence;
	}

	public String getXtjhSequence() {
		return xtjhSequence;
	}

	public void setXtjhSequence(String xtjhSequence) {
		this.xtjhSequence = xtjhSequence;
	}

	



	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public BigDecimal getGiveBackRateFor1Term() {
		return giveBackRateFor1Term;
	}

	public void setGiveBackRateFor1Term(BigDecimal giveBackRateFor1Term) {
		this.giveBackRateFor1Term = giveBackRateFor1Term;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getStartRDate() {
		return startRDate;
	}

	public void setStartRDate(String startRDate) {
		this.startRDate = startRDate;
	}

	public String getEndRDate() {
		return endRDate;
	}

	public void setEndRDate(String endRDate) {
		this.endRDate = endRDate;
	}

	public String getStartRDateForT1() {
		return startRDateForT1;
	}

	public void setStartRDateForT1(String startRDateForT1) {
		this.startRDateForT1 = startRDateForT1;
	}

	public String getEndRDateForT1() {
		return endRDateForT1;
	}

	public void setEndRDateForT1(String endRDateForT1) {
		this.endRDateForT1 = endRDateForT1;
	}

	public String getStartRDateForT2() {
		return startRDateForT2;
	}

	public void setStartRDateForT2(String startRDateForT2) {
		this.startRDateForT2 = startRDateForT2;
	}

	public String getEndRDateForT2() {
		return endRDateForT2;
	}

	public void setEndRDateForT2(String endRDateForT2) {
		this.endRDateForT2 = endRDateForT2;
	}

	public Long getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	
    
    
    
/*	private String loanId;
	private String contractNum;
	private String borrowerName;
	private String borrowerName2;
	private String sex;
	private String idnum;
	private String idnum2;
	private String address;
	private String postcode;
	private String email;
	private String signDate;
	private String startRDate;
	private String endRDate;
	private String time;
	private String signingSite;
	private String pactMoney;
	private String returnETerm;
	private String promiseReturnDate;
	private String referRate;
	private String evalRate;
	private String manageRate;
	private String risk;
	private String managerRateForPartyC;
	private String rateSum;
	private String giveBackRateFor1Term;
	private String giveBackRateFor3Term;
	private String giveBackRateFor4Term;
	private String giveBackRateAfter4Term;
	private String serviceTel;
	private String overduePenalty1Day;
	private String overduePenalty15Day;
	private String purpose;
	private String contractVersion;
	private String bank;
	private String bankFullName;
	private String account;
	private String giveBackBank;
	private String gbFullName;
	private String gbAccount;
	private String returnETermForT1;
	private String timeForT1;
	private String startRDateForT1;
	private String endRDateForT1;
	private String returnETermForT2;
	private String timeForT2;
	private String startRDateForT2;
	private String endRDateForT2;
	private String orgName;
	private String zhongTaiSequence;
	private String xtjhSequence;
	*/
	
	
}
