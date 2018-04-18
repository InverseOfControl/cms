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
public class PolicyInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private Long id; //保单信息id
	private BigDecimal insuranceAmt; // 	保险金额
	private Integer insuranceTerm; // 	保险年限
	private Integer paidTerm; // 	已缴年限	
	private Date lastPaymentDate; // 	最近1次缴纳时间
	private String paymentMethod; // 	缴费方式
	private String policyRelation; // 	与被保险人关系
	private BigDecimal yearPaymentAmt; //年缴金额
	private String policyCheck; //保单真伪核实方式
			
	private Long ifEmpty = 0L;//对象是否是否为空  0是  1不是
	
	private String policyCheckIsVerify;//保单贷是否核实
	
	
	
	public String getPolicyCheckIsVerify() {
		return policyCheckIsVerify;
	}
	public void setPolicyCheckIsVerify(String policyCheckIsVerify) {
		this.policyCheckIsVerify = policyCheckIsVerify;
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
	public BigDecimal getInsuranceAmt() {
		return insuranceAmt;
	}
	public void setInsuranceAmt(BigDecimal insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}
	public Integer getInsuranceTerm() {
		return insuranceTerm;
	}
	public void setInsuranceTerm(Integer insuranceTerm) {
		this.insuranceTerm = insuranceTerm;
	}
	public Integer getPaidTerm() {
		return paidTerm;
	}
	public void setPaidTerm(Integer paidTerm) {
		this.paidTerm = paidTerm;
	}
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	 
	public BigDecimal getYearPaymentAmt() {
		return yearPaymentAmt;
	}
	public void setYearPaymentAmt(BigDecimal yearPaymentAmt) {
		this.yearPaymentAmt = yearPaymentAmt;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPolicyRelation() {
		return policyRelation;
	}
	public void setPolicyRelation(String policyRelation) {
		this.policyRelation = policyRelation;
	}
	public String getPolicyCheck() {
		return policyCheck;
	}
	public void setPolicyCheck(String policyCheck) {
		this.policyCheck = policyCheck;
	}
	public PolicyInfoVO(){	
	}
	public PolicyInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
