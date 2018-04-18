package com.ymkj.cms.biz.api.vo.response.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Res_VO_600006 implements Serializable {
	
	private static final long serialVersionUID = -4693205736573973772L;
	
	private String appNo;
	private String applicantName;
	private String status;
	private String rtfState;
	private String rtfNodeState;
	private String applyTime;
	private BigDecimal applyAccount;
	private String productName;
	private String idCardNoLastFourDigits;
	private Integer applyTerm;
	private String creditAppliacation;
	private String remark ;
	private String cusCode;
	private String cusName;
	private String backReason;
	private String backRemark;
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

	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
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
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getBackRemark() {
		return backRemark;
	}
	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}
	
	
}
