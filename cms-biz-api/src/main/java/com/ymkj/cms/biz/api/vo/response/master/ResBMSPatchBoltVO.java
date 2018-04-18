package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;


public class ResBMSPatchBoltVO implements Serializable{

	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = -8431461527081340873L;
	
	private String customerName;//客户姓名
	private String customerIDNO;//客户身份证
	private String productName;//产品名称
	private String branchManagerName;//客户经理名称
	private String serviceName;//客服名称
	private String contractLmt;//合同金额
	private String contractTerm;//合同期限
	private String applyLmt;//合同额度
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIDNO() {
		return customerIDNO;
	}
	public void setCustomerIDNO(String customerIDNO) {
		this.customerIDNO = customerIDNO;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getContractLmt() {
		return contractLmt;
	}
	public void setContractLmt(String contractLmt) {
		this.contractLmt = contractLmt;
	}
	public String getContractTerm() {
		return contractTerm;
	}
	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
