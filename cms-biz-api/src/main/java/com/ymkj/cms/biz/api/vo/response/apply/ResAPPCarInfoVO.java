package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPCarInfoVO  implements Serializable{

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
 
	private String carType; //车辆类型
	private String carLoan; //是否有车贷
	private Date carBuyDate; //车辆购买时间
	private BigDecimal nakedCarPrice; //裸车价/万元
	private BigDecimal carBuyPrice; //购买价/万元
	private Integer carLoanTerm; //贷款剩余期数	
	private BigDecimal monthPaymentAmt; //月供
	private String localPlate; //本地车牌
	private String  plateNum; //车牌号 //新加字段

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
