package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

public class ResWMXTExportVo implements Serializable{

	//机构代码
		private String  organizationCode;
		//合同号
		private String contractNum;
		//申请地点   <!--查询渤海信托机构表-->
		private String loggedArea;
		//申请号
		private String loanNo;
		//渠道来源  ="外贸信托"
		private String channelSource;
		//证件类型  ="身份证"
		private String idType;
		//证件号码
		private String idNo;
		//姓名
		private String name;
		//移动电话
		private String cellPhone;
		//联系电话
		private String homePhone;
		//邮政编码
		private String issuerPostcode;
		//通信地址
		private String homeAddress;
		//申请用途
		private String creditApplication;
		//合同金额
		private String pactMoney;
		//实付金额
		private String contractLmt;
		//申请币种 ="人民币"
		private String applyMoney;
		//申请期限
		private String applyTerm;
		//还款账户类型 
		private String repaymentAccountType;
		//还款账号
		private String gbAccount;
		//还款方式 
		private String gbMode;
		//扣款日类型
		private String dateOfDebitType;
		//扣款日类别 
		private String dateOfDebitType2;
		//扣款日期
		private String startrdate;
		//婚姻状况
		private String maritalStatus;
		//学历 
		private String qualification;
		//户籍   <!--查询渤海信托机构表-->
		private String idIssuerAddress;
		//个人月收入 
		private String monthSalary;
		//家庭地址 
		private String homeAddress2;
		//家庭邮编
		private String homePostcode;
		//住宅电话
		private String homePhone1;
		//经办机构
		private String organization;
		//缴费方式 
		private String repayMentway;
		//贷款类型 
		private String loanType;
		//借款人类型
		private String borrowerType;
		//产品编号
		private String productCd;
		//产品名称
		private String productName;
		//手续费
		private String poundage;
		//这个不知道是啥老系统查询出来了
		private String chargeRate;
		//月利率
		private String rateem;
		//提前还款违约金比例 
		private String penaltyRateem;
		//罚息率月
		private String penaltyInterestRate;
		//履行担保天数
		private String guaranteeDays;
		//服务费
		private String manageRate;
		//服务费率 
		private String manageRateRateem;
		//担保费 
		private String  guaranteeFee;
		//担保费率
		private String guaranteeFeeRateem;
		//银行代码Code（还款银行）
		private String loanBankIdStill;
		//开户省市  <!--查询渤海信托机构表-->
		private String provincesAndCities;
		//费用一 
		private String costOne;
		//费用二 
		private String costTwo;
		//费用三
		private String costThree;
		//费用四
		private String costFour;
		//费用五 
		private String costFix;
		//放款日期
		private String loanDate;
		//放款账户类型
		private String loanAccountType;
		//放款银行代码
		private String loanBankCode;
		//放款账户名称 
		private String loanAccountName;
		//放款账户号码
		private String loanAccountCode;
		//支付渠道 暂时没找到字段
		//private String paymentChannel;
		//放款账户开户支行 暂时没找到字段 
		//private String loanAccountSubbranch;
		//放款账户开户所在省 暂时没找到字段 
		//private String loanAccountSave;
		//放款账户开户所在市 暂时没找到字段 
		//private String loanAccountCity;
		
		
		
		//管理营业部ID，用于去平台查询对应的营业部信息获取parent_Id,这个字段不属于都出的字段
		private String manageBranchId;
		
		public String getOrganizationCode() {
			return organizationCode;
		}
		public void setOrganizationCode(String organizationCode) {
			this.organizationCode = organizationCode;
		}
		public String getContractNum() {
			return contractNum;
		}
		public void setContractNum(String contractNum) {
			this.contractNum = contractNum;
		}
		public String getLoggedArea() {
			return loggedArea;
		}
		public void setLoggedArea(String loggedArea) {
			this.loggedArea = loggedArea;
		}
		public String getLoanNo() {
			return loanNo;
		}
		public void setLoanNo(String loanNo) {
			this.loanNo = loanNo;
		}
		public String getChannelSource() {
			return channelSource;
		}
		public void setChannelSource(String channelSource) {
			this.channelSource = channelSource;
		}
		public String getIdType() {
			return idType;
		}
		public void setIdType(String idType) {
			this.idType = idType;
		}
		public String getIdNo() {
			return idNo;
		}
		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCellPhone() {
			return cellPhone;
		}
		public void setCellPhone(String cellPhone) {
			this.cellPhone = cellPhone;
		}
		public String getHomePhone() {
			return homePhone;
		}
		public void setHomePhone(String homePhone) {
			this.homePhone = homePhone;
		}
		
		
		public String getPactMoney() {
			return pactMoney;
		}
		public void setPactMoney(String pactMoney) {
			this.pactMoney = pactMoney;
		}
		public String getContractLmt() {
			return contractLmt;
		}
		public void setContractLmt(String contractLmt) {
			this.contractLmt = contractLmt;
		}
		public String getApplyMoney() {
			return applyMoney;
		}
		public void setApplyMoney(String applyMoney) {
			this.applyMoney = applyMoney;
		}
		public String getApplyTerm() {
			return applyTerm;
		}
		public void setApplyTerm(String applyTerm) {
			this.applyTerm = applyTerm;
		}
		public String getRepaymentAccountType() {
			return repaymentAccountType;
		}
		public void setRepaymentAccountType(String repaymentAccountType) {
			this.repaymentAccountType = repaymentAccountType;
		}
		public String getGbAccount() {
			return gbAccount;
		}
		public void setGbAccount(String gbAccount) {
			this.gbAccount = gbAccount;
		}
		public String getGbMode() {
			return gbMode;
		}
		public void setGbMode(String gbMode) {
			this.gbMode = gbMode;
		}
		public String getDateOfDebitType() {
			return dateOfDebitType;
		}
		public void setDateOfDebitType(String dateOfDebitType) {
			this.dateOfDebitType = dateOfDebitType;
		}
		public String getDateOfDebitType2() {
			return dateOfDebitType2;
		}
		public void setDateOfDebitType2(String dateOfDebitType2) {
			this.dateOfDebitType2 = dateOfDebitType2;
		}
		public String getStartrdate() {
			return startrdate;
		}
		public void setStartrdate(String startrdate) {
			this.startrdate = startrdate;
		}
		public String getMaritalStatus() {
			return maritalStatus;
		}
		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getIdIssuerAddress() {
			return idIssuerAddress;
		}
		public void setIdIssuerAddress(String idIssuerAddress) {
			this.idIssuerAddress = idIssuerAddress;
		}
		public String getMonthSalary() {
			return monthSalary;
		}
		public void setMonthSalary(String monthSalary) {
			this.monthSalary = monthSalary;
		}
		public String getHomeAddress2() {
			return homeAddress2;
		}
		public void setHomeAddress2(String homeAddress2) {
			this.homeAddress2 = homeAddress2;
		}
		public String getHomePostcode() {
			return homePostcode;
		}
		public void setHomePostcode(String homePostcode) {
			this.homePostcode = homePostcode;
		}
		public String getHomePhone1() {
			return homePhone1;
		}
		public void setHomePhone1(String homePhone1) {
			this.homePhone1 = homePhone1;
		}
		public String getOrganization() {
			return organization;
		}
		public void setOrganization(String organization) {
			this.organization = organization;
		}
		public String getRepayMentway() {
			return repayMentway;
		}
		public void setRepayMentway(String repayMentway) {
			this.repayMentway = repayMentway;
		}
		public String getLoanType() {
			return loanType;
		}
		public void setLoanType(String loanType) {
			this.loanType = loanType;
		}
		public String getBorrowerType() {
			return borrowerType;
		}
		public void setBorrowerType(String borrowerType) {
			this.borrowerType = borrowerType;
		}
		public String getProductCd() {
			return productCd;
		}
		public void setProductCd(String productCd) {
			this.productCd = productCd;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getPoundage() {
			return poundage;
		}
		public void setPoundage(String poundage) {
			this.poundage = poundage;
		}
		public String getRateem() {
			return rateem;
		}
		public void setRateem(String rateem) {
			this.rateem = rateem;
		}
		public String getPenaltyRateem() {
			return penaltyRateem;
		}
		public void setPenaltyRateem(String penaltyRateem) {
			this.penaltyRateem = penaltyRateem;
		}
		public String getPenaltyInterestRate() {
			return penaltyInterestRate;
		}
		public void setPenaltyInterestRate(String penaltyInterestRate) {
			this.penaltyInterestRate = penaltyInterestRate;
		}
		public String getGuaranteeDays() {
			return guaranteeDays;
		}
		public void setGuaranteeDays(String guaranteeDays) {
			this.guaranteeDays = guaranteeDays;
		}
		public String getManageRate() {
			return manageRate;
		}
		public void setManageRate(String manageRate) {
			this.manageRate = manageRate;
		}
		public String getManageRateRateem() {
			return manageRateRateem;
		}
		public void setManageRateRateem(String manageRateRateem) {
			this.manageRateRateem = manageRateRateem;
		}
		public String getGuaranteeFee() {
			return guaranteeFee;
		}
		public void setGuaranteeFee(String guaranteeFee) {
			this.guaranteeFee = guaranteeFee;
		}
		public String getGuaranteeFeeRateem() {
			return guaranteeFeeRateem;
		}
		public void setGuaranteeFeeRateem(String guaranteeFeeRateem) {
			this.guaranteeFeeRateem = guaranteeFeeRateem;
		}
		public String getLoanBankIdStill() {
			return loanBankIdStill;
		}
		public void setLoanBankIdStill(String loanBankIdStill) {
			this.loanBankIdStill = loanBankIdStill;
		}
		public String getProvincesAndCities() {
			return provincesAndCities;
		}
		public void setProvincesAndCities(String provincesAndCities) {
			this.provincesAndCities = provincesAndCities;
		}
		public String getCostOne() {
			return costOne;
		}
		public void setCostOne(String costOne) {
			this.costOne = costOne;
		}
		public String getCostTwo() {
			return costTwo;
		}
		public void setCostTwo(String costTwo) {
			this.costTwo = costTwo;
		}
		public String getCostThree() {
			return costThree;
		}
		public void setCostThree(String costThree) {
			this.costThree = costThree;
		}
		public String getCostFour() {
			return costFour;
		}
		public void setCostFour(String costFour) {
			this.costFour = costFour;
		}
		public String getCostFix() {
			return costFix;
		}
		public void setCostFix(String costFix) {
			this.costFix = costFix;
		}
		public String getLoanDate() {
			return loanDate;
		}
		public void setLoanDate(String loanDate) {
			this.loanDate = loanDate;
		}
		public String getLoanAccountType() {
			return loanAccountType;
		}
		public void setLoanAccountType(String loanAccountType) {
			this.loanAccountType = loanAccountType;
		}
		public String getLoanBankCode() {
			return loanBankCode;
		}
		public void setLoanBankCode(String loanBankCode) {
			this.loanBankCode = loanBankCode;
		}
		public String getLoanAccountName() {
			return loanAccountName;
		}
		public void setLoanAccountName(String loanAccountName) {
			this.loanAccountName = loanAccountName;
		}
		public String getLoanAccountCode() {
			return loanAccountCode;
		}
		public void setLoanAccountCode(String loanAccountCode) {
			this.loanAccountCode = loanAccountCode;
		}
		
		public String getChargeRate() {
			return chargeRate;
		}
		public void setChargeRate(String chargeRate) {
			this.chargeRate = chargeRate;
		}
		public String getIssuerPostcode() {
			return issuerPostcode;
		}
		public void setIssuerPostcode(String issuerPostcode) {
			this.issuerPostcode = issuerPostcode;
		}
		public String getHomeAddress() {
			return homeAddress;
		}
		public void setHomeAddress(String homeAddress) {
			this.homeAddress = homeAddress;
		}
		public String getCreditApplication() {
			return creditApplication;
		}
		public void setCreditApplication(String creditApplication) {
			this.creditApplication = creditApplication;
		}
		public String getManageBranchId() {
			return manageBranchId;
		}
		public void setManageBranchId(String manageBranchId) {
			this.manageBranchId = manageBranchId;
		}
		
}
