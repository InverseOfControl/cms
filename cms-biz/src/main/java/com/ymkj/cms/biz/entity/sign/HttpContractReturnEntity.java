package com.ymkj.cms.biz.entity.sign;

import java.util.List;

public class HttpContractReturnEntity {
	
	private BMSLoanContract contract;
	
	private BMSContractLoan loan;
	
	private List<BMSRepaymentDetail> repaymentDetail;
	
	private String message;
	
	private String code;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BMSLoanContract bmsContractLoan() {
		return contract;
	}

	public void setContract(BMSLoanContract contract) {
		this.contract = contract;
	}

	public BMSContractLoan getLoan() {
		return loan;
	}

	public void setLoan(BMSContractLoan loan) {
		this.loan = loan;
	}

	public List<BMSRepaymentDetail> getRepaymentDetail() {
		return repaymentDetail;
	}

	public void setRepaymentDetail(List<BMSRepaymentDetail> repaymentDetail) {
		this.repaymentDetail = repaymentDetail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BMSLoanContract getContract() {
		return contract;
	}
	
	
	

	
	

}
