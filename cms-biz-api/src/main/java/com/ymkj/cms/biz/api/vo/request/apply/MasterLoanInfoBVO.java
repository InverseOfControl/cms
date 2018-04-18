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
public class MasterLoanInfoBVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private Long id;
	private Date acctRegisterDate; //账号注册时间
 
	private String buyerCreditLevel; //买家信用等级
	private String buyerCreditType; //买家信用类型
	private BigDecimal goodRate; //	好评率/%
	private BigDecimal lastYearPayAmt; //	上一年度支出额
	private Integer sesameCreditValue; //	芝麻信用分
	private BigDecimal payAmt3; //	近3个月支出总额3
	private BigDecimal payAmt2; //	近3个月支出总额2
	private BigDecimal payAmt1; //	近3个月支出总额1
	private BigDecimal payMonthAmt; //	月均	
			
	private String jiDongUserLevel; //	京东用户等级
	private Integer whiteCreditValue; //	小白信用分
	private BigDecimal pastYearShoppingAmount; //	近一年实际消费金额
			
 
	private String onlineAWithin3MonthsAddress;//网购达人贷 3个月内收货地址   00001 同地址  00002同司址 00003其他
	private String onlineAWithin6MonthsAddress;//网购达人贷 3个月内收货地址    00001 同地址  00002同司址 00003其他
	private String onlineAWithin12MonthsAddress;//网购达人贷 3个月内收货地址  00001 同地址  00002同司址 00003其他
	
	private Long ifEmpty = 0L;//对象是否是否为空  0是  1不是
	
	
	public String getOnlineAWithin3MonthsAddress() {
		return onlineAWithin3MonthsAddress;
	}
	public void setOnlineAWithin3MonthsAddress(String onlineAWithin3MonthsAddress) {
		this.onlineAWithin3MonthsAddress = onlineAWithin3MonthsAddress;
	}
	public String getOnlineAWithin6MonthsAddress() {
		return onlineAWithin6MonthsAddress;
	}
	public void setOnlineAWithin6MonthsAddress(String onlineAWithin6MonthsAddress) {
		this.onlineAWithin6MonthsAddress = onlineAWithin6MonthsAddress;
	}
	public String getOnlineAWithin12MonthsAddress() {
		return onlineAWithin12MonthsAddress;
	}
	public void setOnlineAWithin12MonthsAddress(String onlineAWithin12MonthsAddress) {
		this.onlineAWithin12MonthsAddress = onlineAWithin12MonthsAddress;
	}
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
	public BigDecimal getPayAmt2() {
		return payAmt2;
	}
	public void setPayAmt2(BigDecimal payAmt2) {
		this.payAmt2 = payAmt2;
	}
	public BigDecimal getPayAmt1() {
		return payAmt1;
	}
	public void setPayAmt1(BigDecimal payAmt1) {
		this.payAmt1 = payAmt1;
	}
	 
	 
	public Date getAcctRegisterDate() {
		return acctRegisterDate;
	}
	public void setAcctRegisterDate(Date acctRegisterDate) {
		this.acctRegisterDate = acctRegisterDate;
	}
	public BigDecimal getGoodRate() {
		return goodRate;
	}
	public void setGoodRate(BigDecimal goodRate) {
		this.goodRate = goodRate;
	}
	public BigDecimal getLastYearPayAmt() {
		return lastYearPayAmt;
	}
	public void setLastYearPayAmt(BigDecimal lastYearPayAmt) {
		this.lastYearPayAmt = lastYearPayAmt;
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
	public MasterLoanInfoBVO(){	
	}
	public MasterLoanInfoBVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public Integer getSesameCreditValue() {
		return sesameCreditValue;
	}
	public void setSesameCreditValue(Integer sesameCreditValue) {
		this.sesameCreditValue = sesameCreditValue;
	}
	public Integer getWhiteCreditValue() {
		return whiteCreditValue;
	}
	public void setWhiteCreditValue(Integer whiteCreditValue) {
		this.whiteCreditValue = whiteCreditValue;
	}
	
		
}
