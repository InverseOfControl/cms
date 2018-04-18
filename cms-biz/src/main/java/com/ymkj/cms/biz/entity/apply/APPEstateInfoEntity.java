package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPEstateInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long perosnId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String loanNo;//借款编号
	private Long estateId;//房产信息流水号
	private String org;//机构号
	@NotNull(message = "000001,房产类型不能为空")
	private String estateType;//房产类型
	@NotNull(message = "000001,房产所在省不能为空")
	private Long estateStateId ;//房产所在省ID
	private String estateState ;//房产所在省
	@NotNull(message = "000001,房产所在市ID不能为空")
	private Long estateCityId ;//房产所在市ID
	private String estateCity ;//房产所在市
	@NotNull(message = "000001,房产所在区ID不能为空")
	private Long estateZoneId ;//房产所在区ID
	private String estateZone ;//房产所在区
	@NotNull(message = "000001,房贷情况不能为空")
	private String estateLoan;//房贷情况
	@NotNull(message = "000001,购买时间不能为空")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date estateBuyDate;//购买时间
 
	private BigDecimal estateAmt;//	购买总价值/万元
	@NotNull(message = "000001,市值参考价/元不能为空")
	private BigDecimal referenceAmt;//		市值参考价/万元	
	//@NotNull(message = "000001,房贷金额不能为空")  bug当房贷情况为还款中才是必填
	private BigDecimal estateLoanAmt;//			房贷金额
	//@NotNull(message = "000001,月供不能为空")	bug当房贷情况为还款中才是必填
	private BigDecimal monthPaymentAmt;//	月供
	//@NotNull(message = "000001,已还期数不能为空")	bug当房贷情况为还款中才是必填
	private Integer hasRepaymentNum;//			已还期数
//	@NotNull(message = "000001,建筑面积不能为空")
	private Double builtupArea ;//		建筑面积
//	@NotNull(message = "000001,房产所有权不能为空")
	private String houseOwnership ;//		房产所有权
	@NotNull(message = "000001,产权比例不能为空")
	private Double equityRate ;//产权比例
//	@NotNull(message = "000001,单据户名为本人不能为空")	 
	private String ifMe ;//单据户名为本人
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date estateLoanIssueDate;//房贷发放年月
	private String estateSameRegistered;//房产住址是否同户籍地址
	
	private String otherName ;//共有人姓名
	private String otherIdNo ;//共有人身份证号
	private String estateAddress ;//房产地址
	private Integer pengyuanCheck ;//鹏元验证共有人
	private Integer personBankCheck ;//人行是否获取到住址或房产地址
		 
			 
	 
	
	public String getEstateSameRegistered() {
		return estateSameRegistered;
	}

	public void setEstateSameRegistered(String estateSameRegistered) {
		this.estateSameRegistered = estateSameRegistered;
	}

	private Long creatorId ; 
	
	private String creator ; 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer snapVersion=3;//快照版本号

	
	
	
	
	public Date getEstateLoanIssueDate() {
		return estateLoanIssueDate;
	}

	public void setEstateLoanIssueDate(Date estateLoanIssueDate) {
		this.estateLoanIssueDate = estateLoanIssueDate;
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

	public Integer getSnapVersion() {
		return snapVersion;
	}

	public void setSnapVersion(Integer snapVersion) {
		this.snapVersion = snapVersion;
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

	public String getIfMe() {
		return ifMe;
	}

	public void setIfMe(String ifMe) {
		this.ifMe = ifMe;
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

	public Integer getVerson() {
		return verson;
	}

	public void setVerson(Integer verson) {
		this.verson = verson;
	}

 

}
