package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPCarInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Long personId;
	private Long loanBaseId;
	private String appNo;//申请件编号
	private String loanNo;//申请单编号
	private String org;//机构号
	private Long checkId; //资料核对流水号
	
//	@NotNull(message = "000001,车辆类型不能为空")
	private String carType; //车辆类型
	
	@NotNull(message = "000001,是否有车贷不能为空")
	private String carLoan; //是否有车贷
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date carBuyDate; //车辆购买时间
	
//	@NotNull(message = "000001,裸车价/万元不能为空")
	private BigDecimal nakedCarPrice; //裸车价/元
//	@NotNull(message = "000001,购买价/万元不能为空")
	private BigDecimal carBuyPrice; //购买价/元
	
//	@NotNull(message = "000001,贷款剩余期数不能为空")
	private Integer carLoanTerm; //贷款剩余期数	
	
//	@NotNull(message = "000001,月供不能为空")
	private BigDecimal monthPaymentAmt; //月供
	
//	@NotNull(message = "000001,本地车牌不能为空")
	private String localPlate; //本地车牌
	@NotNull(message = "000001,车牌号不能为空")
	private String  plateNum; //车牌号 //新加字段
	
	private Date transferDate; //过户时间
	private Integer operationStatus; //营运状况
	private Integer carSeat; //车辆座数	
		 
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date carLoanIssueDate;//车贷发放年月
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer snapVersion=3;//快照版本号
	private String carCheckIsVerify;//车辆贷是否核实

	
	
	public String getCarCheckIsVerify() {
		return carCheckIsVerify;
	}

	public void setCarCheckIsVerify(String carCheckIsVerify) {
		this.carCheckIsVerify = carCheckIsVerify;
	}

	public Date getCarLoanIssueDate() {
		return carLoanIssueDate;
	}

	public void setCarLoanIssueDate(Date carLoanIssueDate) {
		this.carLoanIssueDate = carLoanIssueDate;
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

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	 

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

	 
	public Date getCarBuyDate() {
		return carBuyDate;
	}

	public void setCarBuyDate(Date carBuyDate) {
		this.carBuyDate = carBuyDate;
	}

	public BigDecimal getNakedCarPrice() {
		return nakedCarPrice;
	}

	public void setNakedCarPrice(BigDecimal nakedCarPrice) {
		this.nakedCarPrice = nakedCarPrice;
	}

	public BigDecimal getCarBuyPrice() {
		return carBuyPrice;
	}

	public void setCarBuyPrice(BigDecimal carBuyPrice) {
		this.carBuyPrice = carBuyPrice;
	}

	public Integer getCarLoanTerm() {
		return carLoanTerm;
	}

	public void setCarLoanTerm(Integer carLoanTerm) {
		this.carLoanTerm = carLoanTerm;
	}

	public BigDecimal getMonthPaymentAmt() {
		return monthPaymentAmt;
	}

	public void setMonthPaymentAmt(BigDecimal monthPaymentAmt) {
		this.monthPaymentAmt = monthPaymentAmt;
	}

	 
	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Integer getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(Integer operationStatus) {
		this.operationStatus = operationStatus;
	}

	public Integer getCarSeat() {
		return carSeat;
	}

	public void setCarSeat(Integer carSeat) {
		this.carSeat = carSeat;
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

	public String getLocalPlate() {
		return localPlate;
	}

	public void setLocalPlate(String localPlate) {
		this.localPlate = localPlate;
	}

 

}
