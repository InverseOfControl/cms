package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppCarInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private Long personId; //客户主表主键
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String org; //机构号
	private String checkId; //资料核对流水号
	
	private String carType; //车辆类型
	private String carLoan; //是否有车贷
	private String carBuyDate; //购买时间
	private String nakedCarPrice; //裸车价
	private String carBuyPrice; //购买价
	private String carLoanTerm; //贷款剩余期数
	private String carMonthPaymentAmt; //月供
	private String localPlate; //本地车牌
	private String carNo; //车牌号
	private String carLoanDate; //车贷发放年月
	
	private String creator;
	private String creatorId;
	
	public AppCarInfo(Long loanBaseId, Long personId, String loanNo, String org, String creator, String creatorId){
		this.loanBaseId = loanBaseId;
		this.personId = personId;
		this.loanNo = loanNo;
		this.org = org;
		this.creator = creator;
		this.creatorId = creatorId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarLoan() {
		return carLoan;
	}
	public void setCarLoan(String carLoan) {
		this.carLoan = carLoan;
	}
	public String getCarBuyDate() {
		return carBuyDate;
	}
	public void setCarBuyDate(String carBuyDate) {
		this.carBuyDate = carBuyDate;
	}
	public String getNakedCarPrice() {
		return nakedCarPrice;
	}
	public void setNakedCarPrice(String nakedCarPrice) {
		this.nakedCarPrice = nakedCarPrice;
	}
	public String getCarBuyPrice() {
		return carBuyPrice;
	}
	public void setCarBuyPrice(String carBuyPrice) {
		this.carBuyPrice = carBuyPrice;
	}
	public String getCarLoanTerm() {
		return carLoanTerm;
	}
	public void setCarLoanTerm(String carLoanTerm) {
		this.carLoanTerm = carLoanTerm;
	}
	
	public String getCarMonthPaymentAmt() {
		return carMonthPaymentAmt;
	}

	public void setCarMonthPaymentAmt(String carMonthPaymentAmt) {
		this.carMonthPaymentAmt = carMonthPaymentAmt;
	}

	public String getLocalPlate() {
		return localPlate;
	}
	public void setLocalPlate(String localPlate) {
		this.localPlate = localPlate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCarLoanDate() {
		return carLoanDate;
	}

	public void setCarLoanDate(String carLoanDate) {
		this.carLoanDate = carLoanDate;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
}
