package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqInformationVO  extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6376785794271674305L;
	
	private String loanNo;//借款编号
	private String idNo;	//身份证号
	private String name;	//姓名
	private String productCd;	//最新商品编号
	private String productName;	//最新商品名称
	
	
	private String applyTerm;	//门店提交申请期限
	private String applyLmt;	//门店提交申请金额
	private String initProductCode;//门店提交原产品code
	private String initProductName;//门店提交原产品名称
	
	private String creditApplication;	//借款用途
	private String contractBranch;	//签约营业部
	private String barnchManagerName;	//客户经理名称
	private String ifPri;	//是否加急
	private String corpName; //单位名称
	private String corpProvinceId;  //	公司所在省ID
	private String corpCityId; //公司所在市ID
	private String corpZoneId; //公司所在区/县ID
	private String corpAddress; //公司地址
	private String corpPhone; // 公司单电
	private String corpPhoneSec; // 单电2
	
	private String homeStateId; //家庭所在省ID
	private String homeCityId; //家庭所在市ID
	private String homeZoneId; //家庭所在区县ID
	private String homeAddress; //家庭地址
	private Long version;//版本号
	
	private String monthMaxRepay;// 可接受的月最高还款
	private String cusWorkType;//客户工作类型
	private String corpPayWay;//发薪方式
	private String monthSalary;//单位月收入日
	private String otherIncome;//其他月收入
	private String totalMonthSalary;//月总收入
	
	private String ifNewLoanNo; //是否新生件
	private String rtfState;	//流程状态
	private String rtfNodeState;//节点状态
	private String checkNodeState;//审核状态
	private String cellPhone;  //常用电话
	private String cellPhone_sec;//备用电话
	private String remark;  //备注
	
	private String nfcsId;//上海资信ID
	private String reportId; //人行征信ID
	private String hzzxId;//华征征信ID
	private Date createdTime ; //创建时间
	
	private String theThirdPartyNote;//第三方联系人备注
	private String theThirdPartyNoteDetails;//第三方联系人备注详情
	
	private String applyType;//申请类型
	private Date auditEndTime;//复核结束结束时间
	
	private String owningBranchId;//进件门店id
	private String owningBranch;//进件门店名称
	
	private String amoutIncome;//收入证明    AMOUT_INCOME
	private String ifCreditRecord;//是否有无信息记录 IF_CREDIT_RECORD
	
	private String antiFraudScore;//  反欺诈评分
	private String antiFraudWarning;//   反欺诈预警
	private String antiRiskRate;//  欺诈风险评估
	
	private String maritalStatus;        //婚姻状况
	
	private String ifReportId;		//央行报告是否重绑  Y 是，N 否
	
	private String approvalPersonCode;//协审人员Code
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private Date firstSubmitAudit;//首次提交信审
	
	private Long zsIfNewLoanNo;  //终审是否是新生件
	
	
	private String isAntifraud;//是否欺诈可疑 
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
	public Date getFirstSubmitAudit() {
		return firstSubmitAudit;
	}
	public void setFirstSubmitAudit(Date firstSubmitAudit) {
		this.firstSubmitAudit = firstSubmitAudit;
	}
	public String getCorpPhoneSec() {
		return corpPhoneSec;
	}
	public void setCorpPhoneSec(String corpPhoneSec) {
		this.corpPhoneSec = corpPhoneSec;
	}
	public String getCorpPhone() {
		return corpPhone;
	}
	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public String getApprovalPersonCode() {
		return approvalPersonCode;
	}
	public void setApprovalPersonCode(String approvalPersonCode) {
		this.approvalPersonCode = approvalPersonCode;
	}
	public String getIfReportId() {
		return ifReportId;
	}
	public void setIfReportId(String ifReportId) {
		this.ifReportId = ifReportId;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getAntiFraudScore() {
		return antiFraudScore;
	}
	public void setAntiFraudScore(String antiFraudScore) {
		this.antiFraudScore = antiFraudScore;
	}
	public String getAntiFraudWarning() {
		return antiFraudWarning;
	}
	public void setAntiFraudWarning(String antiFraudWarning) {
		this.antiFraudWarning = antiFraudWarning;
	}
	public String getAntiRiskRate() {
		return antiRiskRate;
	}
	public void setAntiRiskRate(String antiRiskRate) {
		this.antiRiskRate = antiRiskRate;
	}
	public String getInitProductCode() {
		return initProductCode;
	}
	public void setInitProductCode(String initProductCode) {
		this.initProductCode = initProductCode;
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}
	public String getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(String amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getIfCreditRecord() {
		return ifCreditRecord;
	}
	public void setIfCreditRecord(String ifCreditRecord) {
		this.ifCreditRecord = ifCreditRecord;
	}
	public String getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public Date getAuditEndTime() {
		return auditEndTime;
	}
	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getTheThirdPartyNote() {
		return theThirdPartyNote;
	}
	public void setTheThirdPartyNote(String theThirdPartyNote) {
		this.theThirdPartyNote = theThirdPartyNote;
	}
	public String getTheThirdPartyNoteDetails() {
		return theThirdPartyNoteDetails;
	}
	public void setTheThirdPartyNoteDetails(String theThirdPartyNoteDetails) {
		this.theThirdPartyNoteDetails = theThirdPartyNoteDetails;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getHzzxId() {
		return hzzxId;
	}
	public void setHzzxId(String hzzxId) {
		this.hzzxId = hzzxId;
	}
	public String getNfcsId() {
		return nfcsId;
	}
	public void setNfcsId(String nfcsId) {
		this.nfcsId = nfcsId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getCellPhone_sec() {
		return cellPhone_sec;
	}
	public void setCellPhone_sec(String cellPhone_sec) {
		this.cellPhone_sec = cellPhone_sec;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	public String getCheckNodeState() {
		return checkNodeState;
	}
	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}
	public String getMonthMaxRepay() {
		return monthMaxRepay;
	}
	public void setMonthMaxRepay(String monthMaxRepay) {
		this.monthMaxRepay = monthMaxRepay;
	}
	public String getCusWorkType() {
		return cusWorkType;
	}
	public void setCusWorkType(String cusWorkType) {
		this.cusWorkType = cusWorkType;
	}
	public String getCorpPayWay() {
		return corpPayWay;
	}
	public void setCorpPayWay(String corpPayWay) {
		this.corpPayWay = corpPayWay;
	}
	public String getMonthSalary() {
		return monthSalary;
	}
	public void setMonthSalary(String monthSalary) {
		this.monthSalary = monthSalary;
	}
	public String getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getTotalMonthSalary() {
		return totalMonthSalary;
	}
	public void setTotalMonthSalary(String totalMonthSalary) {
		this.totalMonthSalary = totalMonthSalary;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getCreditApplication() {
		return creditApplication;
	}
	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public String getBarnchManagerName() {
		return barnchManagerName;
	}
	public void setBarnchManagerName(String barnchManagerName) {
		this.barnchManagerName = barnchManagerName;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpProvinceId() {
		return corpProvinceId;
	}
	public void setCorpProvinceId(String corpProvinceId) {
		this.corpProvinceId = corpProvinceId;
	}
	public String getCorpCityId() {
		return corpCityId;
	}
	public void setCorpCityId(String corpCityId) {
		this.corpCityId = corpCityId;
	}
	public String getCorpZoneId() {
		return corpZoneId;
	}
	public void setCorpZoneId(String corpZoneId) {
		this.corpZoneId = corpZoneId;
	}
	public String getCorpAddress() {
		return corpAddress;
	}
	public void setCorpAddress(String corpAddress) {
		this.corpAddress = corpAddress;
	}
	public String getHomeStateId() {
		return homeStateId;
	}
	public void setHomeStateId(String homeStateId) {
		this.homeStateId = homeStateId;
	}
	public String getHomeCityId() {
		return homeCityId;
	}
	public void setHomeCityId(String homeCityId) {
		this.homeCityId = homeCityId;
	}
	public String getHomeZoneId() {
		return homeZoneId;
	}
	public void setHomeZoneId(String homeZoneId) {
		this.homeZoneId = homeZoneId;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Long getZsIfNewLoanNo() {
		return zsIfNewLoanNo;
	}
	public void setZsIfNewLoanNo(Long zsIfNewLoanNo) {
		this.zsIfNewLoanNo = zsIfNewLoanNo;
	}
	public String getIsAntifraud() {
		return isAntifraud;
	}
	public void setIsAntifraud(String isAntifraud) {
		this.isAntifraud = isAntifraud;
	}
	
}

