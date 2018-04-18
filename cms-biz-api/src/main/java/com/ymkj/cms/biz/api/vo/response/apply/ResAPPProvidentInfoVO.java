package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPProvidentInfoVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
 
	
	private Long providentId;//公积金信息流水号	
	
	 
	private Date openAccountDate;//开户时间	
	 
	private BigDecimal depositBase;//	缴存基数	
 
	private Double depositRate;//		缴存比例	
	 
	private BigDecimal monthDepositAmt;//月缴存额	
 
	private String providentInfo;//		公积金材料
 
	private String paymentUnit;//			缴纳单位同申请单位
	 
	private Integer paymentMonthNum;//		申请单位已缴月数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProvidentId() {
		return providentId;
	}

	public void setProvidentId(Long providentId) {
		this.providentId = providentId;
	}

	public Date getOpenAccountDate() {
		return openAccountDate;
	}

	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
	}

	public BigDecimal getDepositBase() {
		return depositBase;
	}

	public void setDepositBase(BigDecimal depositBase) {
		this.depositBase = depositBase;
	}

	public Double getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(Double depositRate) {
		this.depositRate = depositRate;
	}

	public BigDecimal getMonthDepositAmt() {
		return monthDepositAmt;
	}

	public void setMonthDepositAmt(BigDecimal monthDepositAmt) {
		this.monthDepositAmt = monthDepositAmt;
	}

	public String getProvidentInfo() {
		return providentInfo;
	}

	public void setProvidentInfo(String providentInfo) {
		this.providentInfo = providentInfo;
	}

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public Integer getPaymentMonthNum() {
		return paymentMonthNum;
	}

	public void setPaymentMonthNum(Integer paymentMonthNum) {
		this.paymentMonthNum = paymentMonthNum;
	}
	
	 
 

}
