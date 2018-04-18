package com.ymkj.cms.biz.entity.app.asset;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:AppPolicyInfo</p>
 * <p>Description:保单信息表</p>
 * @uthor YM10159
 * @date 2017年5月2日 下午6:51:07
 */
public class AppPolicyInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id; //主键
	private Long loanBaseId; //借款主表ID
	private String org; //机构号
	private String appNo; //申请件编号
	private String loanNo; //借款编号
	private Long personId; //客户表主键
	private String policyId;
	
	private String insuranceAmt; //保险金额
	private String insuranceTerm; //保险年限
	private String paidTerm; //已缴年限
	private String lastPaymentDate; //最近一次缴费时间
	private String paymentMethod; //缴费方式
	private String policyRelation; //与被保险人关系
	private String yaerPaymentAmt; //年缴金额
	private String policyCheck; //保单真伪核实方式
	
	private String creator; //创建人
	private String creatorId; //创建人ID
	
	public AppPolicyInfo(Long loanBaseId,String loanNo,Long personId,String org,String creator,String creatorId){
		this.loanBaseId = loanBaseId;
		this.loanNo = loanNo;
		this.creator = creator;
		this.creatorId = creatorId;
		this.org = org;
		this.personId = personId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getInsuranceAmt() {
		return insuranceAmt;
	}
	public void setInsuranceAmt(String insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}
	public String getInsuranceTerm() {
		return insuranceTerm;
	}
	public void setInsuranceTerm(String insuranceTerm) {
		this.insuranceTerm = insuranceTerm;
	}
	public String getPaidTerm() {
		return paidTerm;
	}
	public void setPaidTerm(String paidTerm) {
		this.paidTerm = paidTerm;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
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
	public String getYaerPaymentAmt() {
		return yaerPaymentAmt;
	}
	public void setYaerPaymentAmt(String yaerPaymentAmt) {
		this.yaerPaymentAmt = yaerPaymentAmt;
	}
	public String getPolicyCheck() {
		return policyCheck;
	}
	public void setPolicyCheck(String policyCheck) {
		this.policyCheck = policyCheck;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

}
