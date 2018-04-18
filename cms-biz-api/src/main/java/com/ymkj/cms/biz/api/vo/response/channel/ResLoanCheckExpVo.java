package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年5月8日
 * @Description:债权审核导出vo
 */
public class ResLoanCheckExpVo implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2934070941316269170L;

	/**
	 * 批次号
	 */
	private String batchNum;

	/**
	 * 操作客服工号
	 */
	private String serviceCode;

	/**
	 * 操作客服姓名
	 */
	private String serviceName;

	/**
	 * 操作ip
	 */
	private String ip;

	/**
	 * 产品类型
	 */
	private String productName;

	/**
	 * 借款金额（万元）,经产品确认实际为签约金额
	 */
	private String pactMoney;

	/**
	 * 借款期限，经确认是签约期限
	 */
	private String time;

	/**
	 * 借款发放日期
	 */
	private String loanDate;

	/**
	 * 还款日期
	 */
	private String startRdate;

	/**
	 * 月还款额
	 */
	private String currentPrincipal;

	/**
	 * 借款到期日期
	 */
	private String dueDate;

	/**
	 * 借款用途
	 */
	private String creditApplication;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 年龄
	 */
	private String age;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 身份证号
	 */
	private String idNo;

	/**
	 * 学历
	 */
	private String qualification;

	/**
	 * 现居住地址
	 */
	private String homeAddress;

	/**
	 * 婚姻状况
	 */
	private String maritalStatus;

	/**
	 * 单位性质
	 */
	private String corpStructure;

	/**
	 * 本工作开始日期
	 */
	private String corpStandFrom;

	/**
	 * 职位
	 */
	private String corpPost;

	/**
	 * 车型
	 */
	private String carTypr;

	/**
	 * 购买价格
	 */
	private String estateAmt;

	/**
	 * 房产类型
	 */
	private String estateType;

	/**
	 * 建筑面积
	 */
	private String builtupArea;

	/**
	 * 经营主体类型
	 */
	private String priEnterpriseType;

	/**
	 * 成立年限,经产品确认，实际取得是成立日期
	 */
	private String setupDate;

	/**
	 * 融资人持股比例
	 */
	private String sharesScale;

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPactMoney() {
		return pactMoney;
	}

	public void setPactMoney(String pactMoney) {
		this.pactMoney = pactMoney;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getStartRdate() {
		return startRdate;
	}

	public void setStartRdate(String startRdate) {
		this.startRdate = startRdate;
	}

	public String getCurrentPrincipal() {
		return currentPrincipal;
	}

	public void setCurrentPrincipal(String currentPrincipal) {
		this.currentPrincipal = currentPrincipal;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getCreditApplication() {
		return creditApplication;
	}

	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCorpStructure() {
		return corpStructure;
	}

	public void setCorpStructure(String corpStructure) {
		this.corpStructure = corpStructure;
	}

	public String getCorpStandFrom() {
		return corpStandFrom;
	}

	public void setCorpStandFrom(String corpStandFrom) {
		this.corpStandFrom = corpStandFrom;
	}

	public String getCorpPost() {
		return corpPost;
	}

	public void setCorpPost(String corpPost) {
		this.corpPost = corpPost;
	}

	public String getCarTypr() {
		return carTypr;
	}

	public void setCarTypr(String carTypr) {
		this.carTypr = carTypr;
	}

	public String getEstateAmt() {
		return estateAmt;
	}

	public void setEstateAmt(String estateAmt) {
		this.estateAmt = estateAmt;
	}

	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}

	public String getBuiltupArea() {
		return builtupArea;
	}

	public void setBuiltupArea(String builtupArea) {
		this.builtupArea = builtupArea;
	}

	public String getPriEnterpriseType() {
		return priEnterpriseType;
	}

	public void setPriEnterpriseType(String priEnterpriseType) {
		this.priEnterpriseType = priEnterpriseType;
	}

	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}

	public String getSharesScale() {
		return sharesScale;
	}

	public void setSharesScale(String sharesScale) {
		this.sharesScale = sharesScale;
	}

}
