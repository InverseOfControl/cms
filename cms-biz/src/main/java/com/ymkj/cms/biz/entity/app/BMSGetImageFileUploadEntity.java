package com.ymkj.cms.biz.entity.app;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSGetImageFileUploadEntity extends BaseEntity{

	private String appNo;//申请单号
	private String applicantName;//申请人姓名 
	private String status;//申请单状态
	private Date applyTimeOld;//申请时间 
	private BigDecimal applyAccount;//申请金额
	private String productName;//产品名称
	private String idCardNoLastFourDigits;//身份证后四位 
	private Integer applyTerm;//申请期限
	private String creditAppliacation;//贷款用途
	private String remark;//备注
	private String cusCode;//客服工号
	private String cusName;//客服姓名
	private String backReason;//回退原因
	private String bm_code;//客户经理编号
	private String bm_name;//客户经理姓名
	private Integer uploadState;//上传状态
	private String applicantCode;//申请人编号
	private String certifyBusinessDepart;//证件营业部
	private String applyTime;//申请时间
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public BigDecimal getApplyAccount() {
		return applyAccount;
	}
	public void setApplyAccount(BigDecimal applyAccount) {
		this.applyAccount = applyAccount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getIdCardNoLastFourDigits() {
		return idCardNoLastFourDigits;
	}
	public void setIdCardNoLastFourDigits(String idCardNoLastFourDigits) {
		this.idCardNoLastFourDigits = idCardNoLastFourDigits;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getCreditAppliacation() {
		return creditAppliacation;
	}
	public void setCreditAppliacation(String creditAppliacation) {
		this.creditAppliacation = creditAppliacation;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCusCode() {
		return cusCode;
	}
	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	public String getBm_code() {
		return bm_code;
	}
	public void setBm_code(String bm_code) {
		this.bm_code = bm_code;
	}
	public String getBm_name() {
		return bm_name;
	}
	public void setBm_name(String bm_name) {
		this.bm_name = bm_name;
	}
	public Integer getUploadState() {
		return uploadState;
	}
	public void setUploadState(Integer uploadState) {
		this.uploadState = uploadState;
	}
	public String getApplicantCode() {
		return applicantCode;
	}
	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	public String getCertifyBusinessDepart() {
		return certifyBusinessDepart;
	}
	public void setCertifyBusinessDepart(String certifyBusinessDepart) {
		this.certifyBusinessDepart = certifyBusinessDepart;
	}
	public Date getApplyTimeOld() {
		return applyTimeOld;
	}
	public void setApplyTimeOld(Date applyTimeOld) {
		this.applyTimeOld = applyTimeOld;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	
	
	
}
