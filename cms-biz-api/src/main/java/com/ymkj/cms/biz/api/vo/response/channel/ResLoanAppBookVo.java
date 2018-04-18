package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年5月9日
 * @Description:导出放款明细 response vo
 */
public class ResLoanAppBookVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1369346757279507239L;

	private String creditCode;// 信托项目简码
	private String loanNo;// 合同号/借款编号
	private String requestPlace;// 申请地点
	private String requestNo;// 申请号
	private String channel;// 渠道来源
	private String idType;// 证件类型
	private String idNum;// 证件号码
	private String name;// 姓名
	private String contactPhone;// 联系电话
	private String mphone;// 移动电话
	private String postcode;// 邮政编码
	private String address;// 通讯地址
	private String purpose;// 申请用途
	private String pactMoney;// 合同金额
	private String money;// 实付金额
	private String currency;// 申请币种
	private String time;// 申请期限(月)
	private String accountType;// 还款帐户类型
	private String backAccount;// 还款帐号
	private String repaymentMethod;// 还款方式
	private String repaymentDay_type;// 扣款日类型
	private String repaymentDayCategory;// 扣款日类别
	private String promiseReturnDate;// 扣款日期
	private String married;// 婚姻状况
	private String edLevel;// 学历
	private String hrAddress;// 户籍
	private String totalMonthlyIncome;// 个人月收入
	private String familyAddress;// 家庭住址
	private String familyPostcode;// 家庭邮编
	private String familyPhone;// 住宅电话
	private String handleOrg;// 经办机构
	private String payMethod;// 缴费方式
	private String loanType;// 贷款类型
	private String customerType;// 借款人类型
	private String productCode;// 产品编号
	private String productName;// 产品名称
	private String counterFee;// 手续费
	private String counterRate;// 手续费率
	private String rate;// 利率(月)
	private String defaultRate;// 提前还款违约金比率
	private String penaltyRate;// 罚息率(月)
	private String assureDays;// 履行担保天数
	private String serviceFee;// 服务费
	private String serviceFeeRate;// 服务费率
	private String assureFee;// 担保费
	private String assureFeeRate;// 担保费率
	private String bankCode;// 银行代码
	private String openAccountCity;// 开户省市
	private String fee1;// 费用一
	private String fee2;// 费用二
	private String fee3;// 费用三
	private String fee4;// 费用四
	private String fee5;// 费用五
	private String profession;// 职业
	private String company;// 单位名称
	private String industryType;// 单位所属行业
	private String cAddress;// 单位地址
	private String cAode;// 单位邮政编码
	private String startYear;// 本单位工作起始年份
	private String officialRank;// 本人职务
	private String staff;// 本人职称
	private String assureMethod;// 担保方式
	private String assureName;// 担保人姓名
	private String assureIdType;// 担保人证件类型
	private String assureIdnum;// 担保人证件号码
	private String assureAmount;// 担保金额
	private String assureRelation;// 担保关系
	private String lenderAcountType;// 放款账户类型(*)
	private String lenderBankCode;// 放款银行代码(*)
	private String lenderAcountName;// 放款账户名称(*)
	private String lenderAcount;// 放款账户号码(*)
	private String lenderBranchBank;// 放款账户开户支行
	private String lenderAcountProvince;// 放款账户开户所在省
	private String lenderAcountCity;// 放款账户开户所在市
	private String applyName;// 划拨申请书文件名称
	private String loanDate;// 放款日期
	private String endrdate;// 到期日期
	private String rateType;// 利率类型
	private String valueDate;// 起息日期
	private String batchDum;// 批次号
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRequestPlace() {
		return requestPlace;
	}
	public void setRequestPlace(String requestPlace) {
		this.requestPlace = requestPlace;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPactMoney() {
		return pactMoney;
	}
	public void setPactMoney(String pactMoney) {
		this.pactMoney = pactMoney;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBackAccount() {
		return backAccount;
	}
	public void setBackAccount(String backAccount) {
		this.backAccount = backAccount;
	}
	public String getRepaymentMethod() {
		return repaymentMethod;
	}
	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	public String getRepaymentDay_type() {
		return repaymentDay_type;
	}
	public void setRepaymentDay_type(String repaymentDay_type) {
		this.repaymentDay_type = repaymentDay_type;
	}
	public String getRepaymentDayCategory() {
		return repaymentDayCategory;
	}
	public void setRepaymentDayCategory(String repaymentDayCategory) {
		this.repaymentDayCategory = repaymentDayCategory;
	}
	public String getPromiseReturnDate() {
		return promiseReturnDate;
	}
	public void setPromiseReturnDate(String promiseReturnDate) {
		this.promiseReturnDate = promiseReturnDate;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getEdLevel() {
		return edLevel;
	}
	public void setEdLevel(String edLevel) {
		this.edLevel = edLevel;
	}
	public String getHrAddress() {
		return hrAddress;
	}
	public void setHrAddress(String hrAddress) {
		this.hrAddress = hrAddress;
	}
	public String getTotalMonthlyIncome() {
		return totalMonthlyIncome;
	}
	public void setTotalMonthlyIncome(String totalMonthlyIncome) {
		this.totalMonthlyIncome = totalMonthlyIncome;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getFamilyPostcode() {
		return familyPostcode;
	}
	public void setFamilyPostcode(String familyPostcode) {
		this.familyPostcode = familyPostcode;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public String getHandleOrg() {
		return handleOrg;
	}
	public void setHandleOrg(String handleOrg) {
		this.handleOrg = handleOrg;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCounterFee() {
		return counterFee;
	}
	public void setCounterFee(String counterFee) {
		this.counterFee = counterFee;
	}
	public String getCounterRate() {
		return counterRate;
	}
	public void setCounterRate(String counterRate) {
		this.counterRate = counterRate;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDefaultRate() {
		return defaultRate;
	}
	public void setDefaultRate(String defaultRate) {
		this.defaultRate = defaultRate;
	}
	public String getPenaltyRate() {
		return penaltyRate;
	}
	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}
	public String getAssureDays() {
		return assureDays;
	}
	public void setAssureDays(String assureDays) {
		this.assureDays = assureDays;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	
	public String getServiceFeeRate() {
		return serviceFeeRate;
	}
	public void setServiceFeeRate(String serviceFeeRate) {
		this.serviceFeeRate = serviceFeeRate;
	}
	public String getAssureFee() {
		return assureFee;
	}
	public void setAssureFee(String assureFee) {
		this.assureFee = assureFee;
	}
	public String getAssureFeeRate() {
		return assureFeeRate;
	}
	public void setAssureFeeRate(String assureFeeRate) {
		this.assureFeeRate = assureFeeRate;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getOpenAccountCity() {
		return openAccountCity;
	}
	public void setOpenAccountCity(String openAccountCity) {
		this.openAccountCity = openAccountCity;
	}
	public String getFee1() {
		return fee1;
	}
	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}
	public String getFee2() {
		return fee2;
	}
	public void setFee2(String fee2) {
		this.fee2 = fee2;
	}
	public String getFee3() {
		return fee3;
	}
	public void setFee3(String fee3) {
		this.fee3 = fee3;
	}
	public String getFee4() {
		return fee4;
	}
	public void setFee4(String fee4) {
		this.fee4 = fee4;
	}
	public String getFee5() {
		return fee5;
	}
	public void setFee5(String fee5) {
		this.fee5 = fee5;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	public String getcAode() {
		return cAode;
	}
	public void setcAode(String cAode) {
		this.cAode = cAode;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getOfficialRank() {
		return officialRank;
	}
	public void setOfficialRank(String officialRank) {
		this.officialRank = officialRank;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getAssureMethod() {
		return assureMethod;
	}
	public void setAssureMethod(String assureMethod) {
		this.assureMethod = assureMethod;
	}
	public String getAssureName() {
		return assureName;
	}
	public void setAssureName(String assureName) {
		this.assureName = assureName;
	}
	public String getAssureIdType() {
		return assureIdType;
	}
	public void setAssureIdType(String assureIdType) {
		this.assureIdType = assureIdType;
	}
	public String getAssureIdnum() {
		return assureIdnum;
	}
	public void setAssureIdnum(String assureIdnum) {
		this.assureIdnum = assureIdnum;
	}
	public String getAssureAmount() {
		return assureAmount;
	}
	public void setAssureAmount(String assureAmount) {
		this.assureAmount = assureAmount;
	}
	public String getAssureRelation() {
		return assureRelation;
	}
	public void setAssureRelation(String assureRelation) {
		this.assureRelation = assureRelation;
	}
	public String getLenderAcountType() {
		return lenderAcountType;
	}
	public void setLenderAcountType(String lenderAcountType) {
		this.lenderAcountType = lenderAcountType;
	}
	public String getLenderBankCode() {
		return lenderBankCode;
	}
	public void setLenderBankCode(String lenderBankCode) {
		this.lenderBankCode = lenderBankCode;
	}
	public String getLenderAcountName() {
		return lenderAcountName;
	}
	public void setLenderAcountName(String lenderAcountName) {
		this.lenderAcountName = lenderAcountName;
	}
	public String getLenderAcount() {
		return lenderAcount;
	}
	public void setLenderAcount(String lenderAcount) {
		this.lenderAcount = lenderAcount;
	}
	public String getLenderBranchBank() {
		return lenderBranchBank;
	}
	public void setLenderBranchBank(String lenderBranchBank) {
		this.lenderBranchBank = lenderBranchBank;
	}
	public String getLenderAcountProvince() {
		return lenderAcountProvince;
	}
	public void setLenderAcountProvince(String lenderAcountProvince) {
		this.lenderAcountProvince = lenderAcountProvince;
	}
	public String getLenderAcountCity() {
		return lenderAcountCity;
	}
	public void setLenderAcountCity(String lenderAcountCity) {
		this.lenderAcountCity = lenderAcountCity;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getEndrdate() {
		return endrdate;
	}
	public void setEndrdate(String endrdate) {
		this.endrdate = endrdate;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getBatchDum() {
		return batchDum;
	}
	public void setBatchDum(String batchDum) {
		this.batchDum = batchDum;
	}
	
	

}
