package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSContractSignSearchVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	
	
	private String loanNo;//借款编号
	private String name;//客户姓名
	private String productName;//产品名称
	private BigDecimal auditLmt;//审批金额
	private Integer productTerm;//产品期限
	private String owningBranch;//录单网点
	private Date endTime;//完成时间;
	
	
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
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
	public BigDecimal getAuditLmt() {
		return auditLmt;
	}
	public void setAuditLmt(BigDecimal auditLmt) {
		this.auditLmt = auditLmt;
	}
	public Integer getProductTerm() {
		return productTerm;
	}
	public void setProductTerm(Integer productTerm) {
		this.productTerm = productTerm;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	
}
