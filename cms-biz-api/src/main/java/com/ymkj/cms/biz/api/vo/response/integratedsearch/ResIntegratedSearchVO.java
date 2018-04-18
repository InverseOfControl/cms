package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqIntegratedSearchVO</p>
 * <p>Description:综合查询列表响应对象</p>
 * @uthor YM10159
 * @date 2017年3月9日 上午11:50:21
 */
public class ResIntegratedSearchVO extends Request{

	private static final long serialVersionUID = 4599200839786561244L;
	
	/**
	 * 案件标识
	 */
	private String caseIdentify;
	/**
	 * 借款编号
	 */
	private String loanNo;//排序
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 客户姓名
	 */
	private String name;//排序
	/**
	 * 证件号码
	 */
	private String idNo;//排序
	/**
	 * 申请类型
	 */
	private String applyType;//排序
	/**
	 * 单位名称
	 */
	private String corpName;//排序
	/**
	 * 借款状态
	 */
	private String status;//排序
	/**
	 * 审批结论
	 */
	private String conclusion;//无法排序
	
	/**
	 * 借款创建时间
	 */
	private String createdTime;
	/**
	 * 营业部
	 */
	private String branch;//排序
	/**
	 * 营业部属性
	 */
	private String branchAttr;//排序
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 申请产品
	 */
	private String initProductName;//排序
	/**
	 * 审核产品
	 */
	private String productName;//排序
	/**
	 * 初审员CODE
	 */
	private String checkPersonCode;
	
	/**
	 * 初次提交信审时间
	 */
	private String applyDate;//排序
	
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
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
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
