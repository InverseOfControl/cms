package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPEstateInfoVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
 
 
	 
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
	
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	 

 

}
