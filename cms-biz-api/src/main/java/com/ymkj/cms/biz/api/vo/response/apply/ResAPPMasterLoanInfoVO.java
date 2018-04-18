package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPMasterLoanInfoVO  implements Serializable{

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
 
	private Date acctRegisterDate;//账户注册时间
	private String buyerCreditLevel;//买家信用等级 
	private String buyerCreditType;//买家信用类型
	private BigDecimal lastYearPayAmt;//上一年度支出额
	private Integer  goodRate ;//好评率
 
	private Integer  sesameCreditValue ;//芝麻信用分
	 
	private BigDecimal payAmt1;//近1个月支出额
 
	private BigDecimal payAmt2;//近2个月支出额
	 
	private BigDecimal payAmt3;//近3个月支出额
	 
	private BigDecimal payMonthAmt;//月均支出额
	 
	private String  jiDongUserLevel ;//京东用户等级
	 
	private BigDecimal  pastYearShoppingAmount ;//近一年消费总量[京东]
 
	private Integer  whiteCreditValue ;//小白信用分
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 


	public Date getAcctRegisterDate() {
		return acctRegisterDate;
	}

	public void setAcctRegisterDate(Date acctRegisterDate) {
		this.acctRegisterDate = acctRegisterDate;
	}

	 

	public BigDecimal getLastYearPayAmt() {
		return lastYearPayAmt;
	}

	public void setLastYearPayAmt(BigDecimal lastYearPayAmt) {
		this.lastYearPayAmt = lastYearPayAmt;
	}

 

	public Integer getGoodRate() {
		return goodRate;
	}

	public void setGoodRate(Integer goodRate) {
		this.goodRate = goodRate;
	}

	public Integer getSesameCreditValue() {
		return sesameCreditValue;
	}

	public void setSesameCreditValue(Integer sesameCreditValue) {
		this.sesameCreditValue = sesameCreditValue;
	}

	public BigDecimal getPayAmt1() {
		return payAmt1;
	}

	public void setPayAmt1(BigDecimal payAmt1) {
		this.payAmt1 = payAmt1;
	}

	public BigDecimal getPayAmt2() {
		return payAmt2;
	}

	public void setPayAmt2(BigDecimal payAmt2) {
		this.payAmt2 = payAmt2;
	}

	public BigDecimal getPayAmt3() {
		return payAmt3;
	}

	public void setPayAmt3(BigDecimal payAmt3) {
		this.payAmt3 = payAmt3;
	}

	public BigDecimal getPayMonthAmt() {
		return payMonthAmt;
	}

	public void setPayMonthAmt(BigDecimal payMonthAmt) {
		this.payMonthAmt = payMonthAmt;
	}

	 
	public String getBuyerCreditLevel() {
		return buyerCreditLevel;
	}

	public void setBuyerCreditLevel(String buyerCreditLevel) {
		this.buyerCreditLevel = buyerCreditLevel;
	}

	public String getBuyerCreditType() {
		return buyerCreditType;
	}

	public void setBuyerCreditType(String buyerCreditType) {
		this.buyerCreditType = buyerCreditType;
	}

	public String getJiDongUserLevel() {
		return jiDongUserLevel;
	}

	public void setJiDongUserLevel(String jiDongUserLevel) {
		this.jiDongUserLevel = jiDongUserLevel;
	}

	public BigDecimal getPastYearShoppingAmount() {
		return pastYearShoppingAmount;
	}

	public void setPastYearShoppingAmount(BigDecimal pastYearShoppingAmount) {
		this.pastYearShoppingAmount = pastYearShoppingAmount;
	}

	public Integer getWhiteCreditValue() {
		return whiteCreditValue;
	}

	public void setWhiteCreditValue(Integer whiteCreditValue) {
		this.whiteCreditValue = whiteCreditValue;
	}

	 

	 
	
	

}
