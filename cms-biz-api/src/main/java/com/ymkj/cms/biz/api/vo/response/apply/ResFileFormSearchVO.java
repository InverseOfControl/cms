package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResFileFormSearchVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1292101758278964611L;
	
	private String name; //姓名
	private String productName; //申请产品
	private String idNo; //身份证号码
	private String branchManagerName; //客服经理
	private String serviceName; //客服
	private BigDecimal accLmt;  //审批额度
	private Date loanDate;  //放款日期
	private Integer accTerm; //审批期限
	private String loanNo;  //借款编号
	private String status;  //状态
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
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
	public BigDecimal getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(BigDecimal accLmt) {
		this.accLmt = accLmt;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public Integer getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(Integer accTerm) {
		this.accTerm = accTerm;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
