package com.ymkj.cms.biz.entity.master;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSIntegraedSearchEntity extends BaseEntity{

	private static final long serialVersionUID = 391738522503190604L;
	
	/**
	 * 案件标识
	 */
	private String caseIdentify;
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 申请类型
	 */
	private String applyType;
	/**
	 * 单位名称
	 */
	private String corpName;
	/**
	 * 借款状态
	 */
	private String status;
	
	/**
	 * 审批金额
	 */
	private String accLmt;
	/**
	 * 创建时间
	 */
	private String createdTime;
	/**
	 * 营业部
	 */
	private String branch;
	/**
	 * 营业部属性
	 */
	private String branchAttr;
	/**
	 * 进件门店ID
	 */
	private String owningBranchId;
	/**
	 * 债权ID
	 */
	private String loanId;
	/**
	 * 一级原因
	 */
	private String primaryReason;
	/**
	 * 二级原因
	 */
	private String secondReason;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 申请产品
	 */
	private String initProductName;
	/**
	 * 审核产品
	 */
	private String productName;
	/**
	 * 初审员CODE
	 */
	private String checkPersonCode;
	
	private String applyDate;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	
	public String getCaseIdentify() {
		return caseIdentify;
	}
	public void setCaseIdentify(String caseIdentify) {
		this.caseIdentify = caseIdentify;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBranchAttr() {
		return branchAttr;
	}
	public void setBranchAttr(String branchAttr) {
		this.branchAttr = branchAttr;
	}
	public String getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getPrimaryReason() {
		return primaryReason;
	}
	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
	}
	public String getSecondReason() {
		return secondReason;
	}
	public void setSecondReason(String secondReason) {
		this.secondReason = secondReason;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
}
