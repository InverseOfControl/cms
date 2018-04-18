package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * 实体类 对应 表 demo
 */
public class ResLoanBaseVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	private Long id ; 


	 
	private String branchManagerCode;//客户经理Code
	private String serviceCode;
	private String remark;
	private String reviewCode;
 
	private String contractBranch;//签约营业部
 
	private Long contractBranchId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchManagerCode() {
		return branchManagerCode;
	}

	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}

	public Long getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(Long contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	

	
	

 
 
 
	 
 
	 
	 
 
 
	 	
	 
 
 
 
	 
 
	 
 
	 
 
	 
	 
 
	 
	
	

}
