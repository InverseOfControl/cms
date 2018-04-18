package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPCardLoanInfoVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	private Date issuerDate;//发卡时间
	 
	private BigDecimal creditLimit;//额度
 
	private BigDecimal billAmt1;//近1个月账单金额
	 
	private BigDecimal billAmt2;//近2个月账单金额
	 
	private BigDecimal billAmt3;//近3个月账单金额
	 
	private BigDecimal billAmt4;//近4个月账单金额
	private BigDecimal payMonthAmt;//	月均
 
	
	public Date getIssuerDate() {
		return issuerDate;
	}

	public void setIssuerDate(Date issuerDate) {
		this.issuerDate = issuerDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getBillAmt1() {
		return billAmt1;
	}

	public void setBillAmt1(BigDecimal billAmt1) {
		this.billAmt1 = billAmt1;
	}

	public BigDecimal getBillAmt2() {
		return billAmt2;
	}

	public void setBillAmt2(BigDecimal billAmt2) {
		this.billAmt2 = billAmt2;
	}

	public BigDecimal getBillAmt3() {
		return billAmt3;
	}

	public void setBillAmt3(BigDecimal billAmt3) {
		this.billAmt3 = billAmt3;
	}

	public BigDecimal getBillAmt4() {
		return billAmt4;
	}

	public void setBillAmt4(BigDecimal billAmt4) {
		this.billAmt4 = billAmt4;
	}

	public BigDecimal getPayMonthAmt() {
		return payMonthAmt;
	}

	public void setPayMonthAmt(BigDecimal payMonthAmt) {
		this.payMonthAmt = payMonthAmt;
	}

 
	
 
 

}
