package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.util.Date;

public class ResReconsiderationLoanSearchVO implements Serializable{

	private static final long serialVersionUID = -4559674097361993780L;
	
	/**
	 * 客户姓名
	 */
	private String personName;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 申请期限
	 */
	private int applyLmt;
	/**
	 * 借款产品
	 */
	private String productName;
	/**
	 * 被拒绝时间
	 */
	private String rejectedTime;
	/**
	 * 拒绝一级原因
	 */
	private String firstLevelReason;
	/**
	 * 复议退回备注
	 */
	private String remark;
	/**
	 * 复议结果
	 */
	private String result;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 是否已读
	 */
	private String isRead;
	
	/**
	 * 提交信审时间
	 */
	private Date submitXsDate;
	
	/**
	 * 进件营业部
	 */
	private String enterBranch;
	
	/**
	 * 复议拒绝原因
	 */
	private String newRejectReason;
	
	/**
	 * 拒绝人CODE
	 */
	private String RejectPersonCode;
	/**
	 * 拒绝人NAME
	 */
	private String RejectPersonName;
	/**
	 * 复议操作人
	 */
	private String modifier;
	/**
	 * 复议操作时间
	 */
	private String modifierDate;
	
	private String reviewReason;
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public int getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(int applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRejectedTime() {
		return rejectedTime;
	}
	public void setRejectedTime(String rejectedTime) {
		this.rejectedTime = rejectedTime;
	}
	public String getFirstLevelReason() {
		return firstLevelReason;
	}
	public void setFirstLevelReason(String firstLevelReason) {
		this.firstLevelReason = firstLevelReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public Date getSubmitXsDate() {
		return submitXsDate;
	}
	public void setSubmitXsDate(Date submitXsDate) {
		this.submitXsDate = submitXsDate;
	}
	public String getEnterBranch() {
		return enterBranch;
	}
	public void setEnterBranch(String enterBranch) {
		this.enterBranch = enterBranch;
	}
	public String getNewRejectReason() {
		return newRejectReason;
	}
	public void setNewRejectReason(String newRejectReason) {
		this.newRejectReason = newRejectReason;
	}
	public String getRejectPersonCode() {
		return RejectPersonCode;
	}
	public void setRejectPersonCode(String rejectPersonCode) {
		RejectPersonCode = rejectPersonCode;
	}
	public String getRejectPersonName() {
		return RejectPersonName;
	}
	public void setRejectPersonName(String rejectPersonName) {
		RejectPersonName = rejectPersonName;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifierDate() {
		return modifierDate;
	}
	public void setModifierDate(String modifierDate) {
		this.modifierDate = modifierDate;
	}
	public String getReviewReason() {
		return reviewReason;
	}
	public void setReviewReason(String reviewReason) {
		this.reviewReason = reviewReason;
	}
	
}
