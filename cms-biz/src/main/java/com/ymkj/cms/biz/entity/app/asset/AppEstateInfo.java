package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class AppEstateInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private Long personId; //客户主表主键
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private String estateId; //房产信息流水号
	private String org; //机构号
	
	private String estateType; //房产类型
	private String estateHouseAddrSameFamilyAddr; //房产地址是否同家庭地址
	private String estateZoneLinkage; //房产所在
	private String estateAddress; //房产地址
	private String estateLoan; //房贷情况
	private String estateBuyDate; //购买时间
	private String estateReferenceAmt; //市值参考价/万元
	private String estateMonthPaymentAmt; //月供
	private String estateMortgageGrantDate; //房贷发放年月
	private String estateEquityRate; //产权比例
	private String estateIfMe; //单据户名为本人
	
	private String estateStateId; //省ID
	private String estateCityId; //市ID
	private String estateZoneId; //区ID
	private String estateState; //省
	private String estateCity; //市
	private String estateZone; //区
	private String creator; //创建人
	private String creatorId; //创建人ID
	
	public AppEstateInfo(Long loanBaseId, Long personId, String loanNo, String org, String creator, String creatorId){
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

	public String getEstateId() {
		return estateId;
	}

	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}

	public String getEstateHouseAddrSameFamilyAddr() {
		return estateHouseAddrSameFamilyAddr;
	}

	public void setEstateHouseAddrSameFamilyAddr(String estateHouseAddrSameFamilyAddr) {
		this.estateHouseAddrSameFamilyAddr = estateHouseAddrSameFamilyAddr;
	}

	public String getEstateZoneLinkage() {
		return estateZoneLinkage;
	}

	public void setEstateZoneLinkage(String estateZoneLinkage) {
		this.estateZoneLinkage = estateZoneLinkage;
	}

	public String getEstateAddress() {
		return estateAddress;
	}

	public void setEstateAddress(String estateAddress) {
		this.estateAddress = estateAddress;
	}

	public String getEstateLoan() {
		return estateLoan;
	}

	public void setEstateLoan(String estateLoan) {
		this.estateLoan = estateLoan;
	}

	public String getEstateBuyDate() {
		return estateBuyDate;
	}

	public void setEstateBuyDate(String estateBuyDate) {
		this.estateBuyDate = estateBuyDate;
	}

	public String getEstateReferenceAmt() {
		return estateReferenceAmt;
	}

	public void setEstateReferenceAmt(String estateReferenceAmt) {
		this.estateReferenceAmt = estateReferenceAmt;
	}

	public String getEstateMonthPaymentAmt() {
		return estateMonthPaymentAmt;
	}

	public void setEstateMonthPaymentAmt(String estateMonthPaymentAmt) {
		this.estateMonthPaymentAmt = estateMonthPaymentAmt;
	}

	public String getEstateMortgageGrantDate() {
		return estateMortgageGrantDate;
	}

	public void setEstateMortgageGrantDate(String estateMortgageGrantDate) {
		this.estateMortgageGrantDate = estateMortgageGrantDate;
	}

	public String getEstateEquityRate() {
		return estateEquityRate;
	}

	public void setEstateEquityRate(String estateEquityRate) {
		this.estateEquityRate = estateEquityRate;
	}

	public String getEstateIfMe() {
		return estateIfMe;
	}

	public void setEstateIfMe(String estateIfMe) {
		this.estateIfMe = estateIfMe;
	}

	public String getEstateStateId() {
		return estateStateId;
	}

	public void setEstateStateId(String estateStateId) {
		this.estateStateId = estateStateId;
	}

	public String getEstateCityId() {
		return estateCityId;
	}

	public void setEstateCityId(String estateCityId) {
		this.estateCityId = estateCityId;
	}

	public String getEstateZoneId() {
		return estateZoneId;
	}

	public void setEstateZoneId(String estateZoneId) {
		this.estateZoneId = estateZoneId;
	}

	public String getEstateState() {
		return estateState;
	}

	public void setEstateState(String estateState) {
		this.estateState = estateState;
	}

	public String getEstateCity() {
		return estateCity;
	}

	public void setEstateCity(String estateCity) {
		this.estateCity = estateCity;
	}

	public String getEstateZone() {
		return estateZone;
	}

	public void setEstateZone(String estateZone) {
		this.estateZone = estateZone;
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

}
