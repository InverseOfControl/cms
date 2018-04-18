package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;

public class ResBMSLoanReviewVO implements Serializable{

	private static final long serialVersionUID = 257298230172552886L;
	private long id;
	private long loanBaseId;
	/**
	 * 借款编号
	 */
	private String loanNo;
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 客户id
	 */
	private long perosnId;
	/**
	 * 申请期限
	 */
	private long applyTerm;
	/**
	 * 申请产品
	 */
	private String productCd;
	/**
	 * 申请产品名称
	 */
	private String productName;
	/**
	 * 被拒绝时间
	 */
	private String refuseDate;
	/**
	 * 拒绝一级原因
	 */
	private String primaryReason;
	/**
	 * 客户经理code
	 */
	private String branchManagerCode;
	/**
	 * 客户经理名字
	 */
	private String branchManagerName;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否已读
	 */
	private Integer isRead;
	/**
	 * 创建用户
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private String createdTime;
	/**
	 * 创建用户ID
	 */
	private long creatorId;
	/**
	 * 修改用户
	 */
	private String modifier;
	/**
	 * 修改时间
	 */
	private String modifiedTime;
	/**
	 * 修改用户id
	 */
	private long modifierId;
	/**
	 * 版本
	 */
	private Integer version;
	/**
	 * 默认是0,删除是1
	 */
	private Integer isDelete;
	/**
	 * 复议原因
	 */
	private String reviewReason;
	/**
	 * 复议备注
	 */
	private String reviewRemark;
	/**
	 * 复议结果
	 */
	private int reviewResult;
	public String getReviewReason() {
		return reviewReason;
	}
	public void setReviewReason(String reviewReason) {
		this.reviewReason = reviewReason;
	}
	public String getReviewRemark() {
		return reviewRemark;
	}
	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}
	public int getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(int reviewResult) {
		this.reviewResult = reviewResult;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public long getPerosnId() {
		return perosnId;
	}
	public void setPerosnId(long perosnId) {
		this.perosnId = perosnId;
	}
	public long getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(long applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRefuseDate() {
		return refuseDate;
	}
	public void setRefuseDate(String refuseDate) {
		this.refuseDate = refuseDate;
	}
	public String getPrimaryReason() {
		return primaryReason;
	}
	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
	}
	public String getBranchManagerCode() {
		return branchManagerCode;
	}
	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public long getModifierId() {
		return modifierId;
	}
	public void setModifierId(long modifierId) {
		this.modifierId = modifierId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	

}
