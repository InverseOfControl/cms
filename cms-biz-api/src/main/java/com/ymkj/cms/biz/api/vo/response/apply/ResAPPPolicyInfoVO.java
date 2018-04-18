package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类 对应 表 demo
 */
public class ResAPPPolicyInfoVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
 
	private Long id;
	
 
	private BigDecimal insuranceAmt;//保险金额
	 
	private Integer insuranceTerm;//保险年限
 
	private Integer paidTerm;//已缴年限
	 
	private Date lastPaymentDate;//最近1次缴纳时间
 
	private String paymentMethod;//缴费方式
 
	private String policyRelation;//与被保险人关系
	 
	private BigDecimal yearPaymentAmt;//年缴金额
	 
	private String policyCheck;//保单真伪核实方式

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

	public BigDecimal getYearPaymentAmt() {
		return yearPaymentAmt;
	}

	public void setYearPaymentAmt(BigDecimal yearPaymentAmt) {
		this.yearPaymentAmt = yearPaymentAmt;
	}

	public String getPolicyCheck() {
		return policyCheck;
	}

	public void setPolicyCheck(String policyCheck) {
		this.policyCheck = policyCheck;
	}

	
 

}
