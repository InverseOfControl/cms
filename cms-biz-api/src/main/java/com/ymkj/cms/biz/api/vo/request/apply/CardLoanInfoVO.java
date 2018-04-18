package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class CardLoanInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private Long id;
	private Date issuerDate; //发卡时间
	private BigDecimal creditLimit; //额度
	private BigDecimal billAmt1; //近4个月账单金额依次为1
	private BigDecimal billAmt2; //4个月账单金额依次为2金额依次为1			
	private BigDecimal billAmt3; //近4个月账单金额依次为3				
	private BigDecimal billAmt4; //近4个月账单金额依次为4			
	private BigDecimal payMonthAmt; //月均
	
	private Long ifEmpty = 0L;//对象是否是否为空  0是  1不是
	
	
	public Long getIfEmpty() {
		return ifEmpty;
	}
	public void setIfEmpty(Long ifEmpty) {
		this.ifEmpty = ifEmpty;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getIssuerDate() {
		return issuerDate;
	}
	public void setIssuerDate(Date issuerDate) {
		this.issuerDate = issuerDate;
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
 
	public CardLoanInfoVO(){	
	}
	public CardLoanInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
