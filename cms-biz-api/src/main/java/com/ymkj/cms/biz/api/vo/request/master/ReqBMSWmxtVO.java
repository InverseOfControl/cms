package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSWmxtVO extends Request{
	//机构代码
	private String organizationCode;
	//合同
	private String accountNo;
	//放款结果
	private String loanResult;
	//银行回盘时间
	private String bankReturnTime;
	//失败原因
	private String errorReason;
	//审批结果
	private String approvalResult;
	//审批拒绝原因
	private String approvalReason;
	//是否导入放款信息成功或者失败
	private String isSuccessStatus;
	//导入失败原因
	private String failReason;
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getLoanResult() {
		return loanResult;
	}
	public void setLoanResult(String loanResult) {
		this.loanResult = loanResult;
	}
	public String getBankReturnTime() {
		return bankReturnTime;
	}
	public void setBankReturnTime(String bankReturnTime) {
		this.bankReturnTime = bankReturnTime;
	}
	public String getErrorReason() {
		return errorReason;
	}
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
	public String getApprovalResult() {
		return approvalResult;
	}
	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}
	public String getApprovalReason() {
		return approvalReason;
	}
	public void setApprovalReason(String approvalReason) {
		this.approvalReason = approvalReason;
	}
	public String getIsSuccessStatus() {
		return isSuccessStatus;
	}
	public void setIsSuccessStatus(String isSuccessStatus) {
		this.isSuccessStatus = isSuccessStatus;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	

}
