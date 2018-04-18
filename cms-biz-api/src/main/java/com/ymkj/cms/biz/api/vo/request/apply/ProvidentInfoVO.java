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
public class ProvidentInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private Long id;//公积金ID
	private Date openAccountDate; //开户时间
	private Double depositRate; // 	缴存比例/%	
	private BigDecimal monthDepositAmt; // 月缴存额
	private BigDecimal depositBase; // 缴存基数
	private String providentInfo; //公积金材料
	private String paymentUnit; //缴纳单位同申请单位
	private Integer paymentMonthNum; //申请单位已缴月数
	
 
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
	public Date getOpenAccountDate() {
		return openAccountDate;
	}
	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
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
	public BigDecimal getDepositBase() {
		return depositBase;
	}
	public void setDepositBase(BigDecimal depositBase) {
		this.depositBase = depositBase;
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
	public ProvidentInfoVO(){	
	}
	public ProvidentInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
