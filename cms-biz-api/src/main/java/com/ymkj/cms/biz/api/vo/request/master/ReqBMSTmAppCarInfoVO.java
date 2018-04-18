package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSTmAppCarInfoVO extends Request{

	private static final long serialVersionUID = 7727110255985281438L;
	
private Long id;
	
	private Long personId;
	
	private Long loanBaseId;
	
	private String appNo;//申请件编号
	
	private String loanNo;//申请单编号
	
	private String org;//机构号
	
	private Long checkId; //资料核对流水号
	
	private String carType; //车辆类型
	
	private String carLoan; //是否有车贷
	
	private Date carBuyDate; //车辆购买时间
	
	private BigDecimal nakedCarPrice; //裸车价/万元
	
	private BigDecimal carBuyPrice; //购买价/万元
	
	private Integer carLoanTerm; //贷款剩余期数	

	private BigDecimal monthPaymentAmt; //月供
	
	private String localPlate; //本地车牌
	
	private String  plateNum; //车牌号 //新加字段
	
	private Date transferDate; //过户时间
	
	private Integer operationStatus; //营运状况
	
	

	private Integer carSeat; //车辆座数	
		 
	 
	
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

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
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

	public String getLocalPlate() {
		return localPlate;
	}

	public void setLocalPlate(String localPlate) {
		this.localPlate = localPlate;
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
	
	private int pageNum; // 当前页数
	private int pageSize;
	private int rows;// 行数
	private int page;// 页数

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	


}
