package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSTmAppEstateInfoVO implements Serializable{

	private static final long serialVersionUID = 5705001337828613198L;
	
	private Long id;
	private Long perosnId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String loanNo;//借款编号
	private Long estateId;//房产信息流水号
	private String org;//机构号

	private String estateType;//房产类型
	
	private Long estateStateId ;//房产所在省
	
	private Long estateCityId ;//房产所在市
	
	private Long estateZoneId ;//房产所在区
	
	private String estateLoan;//房贷情况
	
	
	private Date estateBuyDate;//购买时间
 
	private BigDecimal estateAmt;//	购买总价值/万元
	
	private BigDecimal referenceAmt;//		市值参考价/万元	
	
	private BigDecimal estateLoanAmt;//			房贷金额/万元	
	
	private BigDecimal monthPaymentAmt;//	月供
	private Integer hasRepaymentNum;//			已还期数

	private Double builtupArea ;//		建筑面积
	
	private String houseOwnership ;//		房产所有权
	
	private Double equityRate ;//产权比例
	 
	private String ifMe ;//单据户名为本人
	
	
	
	
	private String otherName ;//共有人姓名
	private String otherIdNo ;//共有人身份证号
	private String estateAddress ;//房产地址
	private Integer pengyuanCheck ;//鹏元验证共有人
	private Integer personBankCheck ;//人行是否获取到住址或房产地址
		 
			 
		 
	
	private Long creatorId ; 
	
	private String creator ; 
	
	
	

	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer version ;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPerosnId() {
		return perosnId;
	}

	public void setPerosnId(Long perosnId) {
		this.perosnId = perosnId;
	}

	public Long getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
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

	public Long getEstateId() {
		return estateId;
	}

	public void setEstateId(Long estateId) {
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

	public Long getEstateStateId() {
		return estateStateId;
	}

	public void setEstateStateId(Long estateStateId) {
		this.estateStateId = estateStateId;
	}

	public Long getEstateCityId() {
		return estateCityId;
	}

	public void setEstateCityId(Long estateCityId) {
		this.estateCityId = estateCityId;
	}

	public Long getEstateZoneId() {
		return estateZoneId;
	}

	public void setEstateZoneId(Long estateZoneId) {
		this.estateZoneId = estateZoneId;
	}

	public String getEstateLoan() {
		return estateLoan;
	}

	public void setEstateLoan(String estateLoan) {
		this.estateLoan = estateLoan;
	}

	public Date getEstateBuyDate() {
		return estateBuyDate;
	}

	public void setEstateBuyDate(Date estateBuyDate) {
		this.estateBuyDate = estateBuyDate;
	}

	public BigDecimal getEstateAmt() {
		return estateAmt;
	}

	public void setEstateAmt(BigDecimal estateAmt) {
		this.estateAmt = estateAmt;
	}

	public BigDecimal getReferenceAmt() {
		return referenceAmt;
	}

	public void setReferenceAmt(BigDecimal referenceAmt) {
		this.referenceAmt = referenceAmt;
	}

	public BigDecimal getEstateLoanAmt() {
		return estateLoanAmt;
	}

	public void setEstateLoanAmt(BigDecimal estateLoanAmt) {
		this.estateLoanAmt = estateLoanAmt;
	}

	public BigDecimal getMonthPaymentAmt() {
		return monthPaymentAmt;
	}

	public void setMonthPaymentAmt(BigDecimal monthPaymentAmt) {
		this.monthPaymentAmt = monthPaymentAmt;
	}

	public Integer getHasRepaymentNum() {
		return hasRepaymentNum;
	}

	public void setHasRepaymentNum(Integer hasRepaymentNum) {
		this.hasRepaymentNum = hasRepaymentNum;
	}

	public Double getBuiltupArea() {
		return builtupArea;
	}

	public void setBuiltupArea(Double builtupArea) {
		this.builtupArea = builtupArea;
	}

	public String getHouseOwnership() {
		return houseOwnership;
	}

	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}

	public Double getEquityRate() {
		return equityRate;
	}

	public void setEquityRate(Double equityRate) {
		this.equityRate = equityRate;
	}

	public String getIfMe() {
		return ifMe;
	}

	public void setIfMe(String ifMe) {
		this.ifMe = ifMe;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getOtherIdNo() {
		return otherIdNo;
	}

	public void setOtherIdNo(String otherIdNo) {
		this.otherIdNo = otherIdNo;
	}

	public String getEstateAddress() {
		return estateAddress;
	}

	public void setEstateAddress(String estateAddress) {
		this.estateAddress = estateAddress;
	}

	public Integer getPengyuanCheck() {
		return pengyuanCheck;
	}

	public void setPengyuanCheck(Integer pengyuanCheck) {
		this.pengyuanCheck = pengyuanCheck;
	}

	public Integer getPersonBankCheck() {
		return personBankCheck;
	}

	public void setPersonBankCheck(Integer personBankCheck) {
		this.personBankCheck = personBankCheck;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
