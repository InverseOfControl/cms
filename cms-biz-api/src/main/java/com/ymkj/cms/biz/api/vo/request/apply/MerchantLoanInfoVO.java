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
public class MerchantLoanInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	
	private Long id;
	private Date setupShopDate; //开店时间
	private String sellerCreditLevel; //卖家信用等级
	private String sellerCreditType; //卖家信用类型
	private Integer regardedNum; //	近半年好评数 
	private BigDecimal billAmt1; //	近6个月账单金额依次为1
	private BigDecimal billAmt2; //	近6个月账单金额依次为2
	private BigDecimal billAmt3; //	近6个月账单金额依次为3
	private BigDecimal billAmt4; //	近6个月账单金额依次为4
	private BigDecimal billAmt5; //	近6个月账单金额依次为5
	private BigDecimal billAmt6; //	近6个月账单金额依次为6
	private BigDecimal payMonthAmt; //	月均
 
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
	public MerchantLoanInfoVO(){	
	}
	public MerchantLoanInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
