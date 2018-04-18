package com.ymkj.cms.biz.entity.finance;

import java.util.List;


public class HttpLoanReturnEntity {
	
	private List<ResLoanFailEntity> createFaiVOs ;
	
	
	private List<ResLoanFailEntity> updateFailVos ;
	
	private String message;
	
	private String code;
	
	private String total;
	
	private String max;
	
	private String loanNo;
	private String loanState;
	private String loanFlowState;



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public List<ResLoanFailEntity> getCreateFaiVOs() {
		return createFaiVOs;
	}

	public void setCreateFaiVOs(List<ResLoanFailEntity> createFaiVOs) {
		this.createFaiVOs = createFaiVOs;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public List<ResLoanFailEntity> getUpdateFailVos() {
		return updateFailVos;
	}

	public void setUpdateFailVos(List<ResLoanFailEntity> updateFailVos) {
		this.updateFailVos = updateFailVos;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getLoanState() {
		return loanState;
	}

	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}

	public String getLoanFlowState() {
		return loanFlowState;
	}

	public void setLoanFlowState(String loanFlowState) {
		this.loanFlowState = loanFlowState;
	}

	
	

}
