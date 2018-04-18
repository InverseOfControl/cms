package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResMerchantLoanInfoVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	

	 
	private Date setupShopDate;//开店时间
 
	private String sellerCreditLevel;//卖家信用等级
	 
	private String sellerCreditType;//卖家信用类型
 
	private Integer regardedNum;//近半年好评数
 
	private BigDecimal billAmt1;//近1个月账单金额
	 
	private BigDecimal billAmt2;//近2个月账单金额
 
	private BigDecimal billAmt3;//近3个月账单金额
	 
	private BigDecimal billAmt4;//近4个月账单金额
	 
	private BigDecimal billAmt5;//近5个月账单金额
	 
	private BigDecimal billAmt6;//近6个月账单金额	
	
	private BigDecimal payMonthAmt;//月均账单金额

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSetupShopDate() {
		return setupShopDate;
	}

	public void setSetupShopDate(Date setupShopDate) {
		this.setupShopDate = setupShopDate;
	}

	public String getSellerCreditLevel() {
		return sellerCreditLevel;
	}

	public void setSellerCreditLevel(String sellerCreditLevel) {
		this.sellerCreditLevel = sellerCreditLevel;
	}

	public String getSellerCreditType() {
		return sellerCreditType;
	}

	public void setSellerCreditType(String sellerCreditType) {
		this.sellerCreditType = sellerCreditType;
	}

	public Integer getRegardedNum() {
		return regardedNum;
	}

	public void setRegardedNum(Integer regardedNum) {
		this.regardedNum = regardedNum;
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

	public BigDecimal getBillAmt5() {
		return billAmt5;
	}

	public void setBillAmt5(BigDecimal billAmt5) {
		this.billAmt5 = billAmt5;
	}

	public BigDecimal getBillAmt6() {
		return billAmt6;
	}

	public void setBillAmt6(BigDecimal billAmt6) {
		this.billAmt6 = billAmt6;
	}

	public BigDecimal getPayMonthAmt() {
		return payMonthAmt;
	}

	public void setPayMonthAmt(BigDecimal payMonthAmt) {
		this.payMonthAmt = payMonthAmt;
	}
	 
	

 

}
