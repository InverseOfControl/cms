package com.ymkj.cms.biz.api.vo.request.apply;

public class RepayVo {
	
	private String shouldRepaymentDate;//应还日期  yyyy-MM-dd
	
	
	private String realityRepaymentDate;//实际还款日期  yyyy-MM-dd
	
	
	/**
	 * 	0.结清  1.逾期 2.未还款
	 */
	private Integer repaymentStatue;//还款状态


	public String getShouldRepaymentDate() {
		return shouldRepaymentDate;
	}


	public void setShouldRepaymentDate(String shouldRepaymentDate) {
		this.shouldRepaymentDate = shouldRepaymentDate;
	}


	public String getRealityRepaymentDate() {
		return realityRepaymentDate;
	}


	public void setRealityRepaymentDate(String realityRepaymentDate) {
		this.realityRepaymentDate = realityRepaymentDate;
	}


	public Integer getRepaymentStatue() {
		return repaymentStatue;
	}


	public void setRepaymentStatue(Integer repaymentStatue) {
		this.repaymentStatue = repaymentStatue;
	}

	
}
