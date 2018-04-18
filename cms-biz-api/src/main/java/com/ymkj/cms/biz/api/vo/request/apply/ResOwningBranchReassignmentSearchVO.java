package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;

import com.ymkj.base.core.biz.api.message.Request;

public class ResOwningBranchReassignmentSearchVO extends Request{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loanNo;// 	借款编号
	private String personName;//	客户姓名
	private String idNo;// 	证件号码
	private String contractBranch;//	营业部	
	private String productName;//	借款产品
	private String status;//	申请状态	
	private String operatorCode;//	处理人code	
	private String operator;//	处理人	
	
	private Long contractBranchId;	//签约营业部ID	
	private String applyInputFlag;	//申请件渠道标识 	applyInput 普通进件 directApplyInput 直通车进件
	
	private Long owningBranchId;//进件门店ID
	private String owningBranch;//进件门店
	
	private String version;//版本
	
	private  BigDecimal pactMoney;//合同金额
	
	private String sqlrUserCode;//申请录入提交工号
	
	private String initProductName;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public Long getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(Long contractBranchId) {
		this.contractBranchId = contractBranchId;
	}
	public String getApplyInputFlag() {
		return applyInputFlag;
	}
	public void setApplyInputFlag(String applyInputFlag) {
		this.applyInputFlag = applyInputFlag;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
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
	public String getContractBranch() {
		return contractBranch;
	}
	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public BigDecimal getPactMoney() {
		return pactMoney;
	}
	public void setPactMoney(BigDecimal pactMoney) {
		this.pactMoney = pactMoney;
	}
	public String getSqlrUserCode() {
		return sqlrUserCode;
	}
	public void setSqlrUserCode(String sqlrUserCode) {
		this.sqlrUserCode = sqlrUserCode;
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}

	
}
