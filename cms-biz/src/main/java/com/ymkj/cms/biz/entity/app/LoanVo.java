package com.ymkj.cms.biz.entity.app;

public class LoanVo{
	 private String loanNo;
	 
	 private String loanState;
	 
	 private String loanFlowState;
	 
	 private Long loanId;
	 
	 private String settlementType;
	 
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
		public Long getLoanId() {
			return loanId;
		}
		public void setLoanId(Long loanId) {
			this.loanId = loanId;
		}
		public String getSettlementType() {
			return settlementType;
		}
		public void setSettlementType(String settlementType) {
			this.settlementType = settlementType;
		}

	 
}
