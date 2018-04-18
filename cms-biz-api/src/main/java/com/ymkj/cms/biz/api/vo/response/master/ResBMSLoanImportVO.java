package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSLoanImportVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8000356973885860505L;
	
	private BigDecimal contractNum;//合同金额
	
	private Date applyDate;//放款申请时间
	
	private String loanNo;//借款编号
	
	private Long loanId;//借款主表id
	
	private String batchNum;//批次号
	
	private String contractBranch;//签约营业厅
	
	private String contractBranchId;//签约营业厅id
	
	private String loanStatus;//借款状态,防止重复放款
	
	private String rtfState;//借款环节，防止重复放款
	
	private String rtfNodeState;//借款审核环节节点状态，防止重复放款
	
	

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public String getRtfNodeState() {
		return rtfNodeState;
	}

	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}

	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public BigDecimal getContractNum() {
		return contractNum;
	}

	public void setContractNum(BigDecimal contractNum) {
		this.contractNum = contractNum;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	

}
