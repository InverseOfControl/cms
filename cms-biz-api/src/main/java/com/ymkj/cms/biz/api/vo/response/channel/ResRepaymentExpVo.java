package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年5月12日
 * @Description:还款计划导出 request vo
 */
public class ResRepaymentExpVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2496155020999828120L;

	/**
	 * 信托项目简码
	 */
	private String creditCode;

	/**
	 * 借款编号
	 */
	private String loanNo;

	/**
	 * 还款类型
	 */
	private String repaymentType;

	/**
	 * 期次
	 */
	private String currentTerm;

	/**
	 * 应还款日期
	 */
	private String returnDate;

	/**
	 * 应还本金
	 */
	private String currentPrincipal;

	/**
	 * 应还利息
	 */
	private String currentAccrual;

	/**
	 * 应还手续费
	 */
	private String counterFee;

	/**
	 * 应还担保费
	 */
	private String assureFee;

	/**
	 * 应还服务费
	 */
	private String serviceFee;

	/**
	 * 应还其他费1
	 */
	private String fee1;

	/**
	 * 应还其他费2
	 */
	private String fee2;

	/**
	 * 应还其他费3
	 */
	private String fee3;

	/**
	 * 剩余本金
	 */
	private String principalBalance;

	/**
	 * 一次性结清应还金额
	 */
	private String repaymentAll;

	

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getCurrentTerm() {
		return currentTerm;
	}

	public void setCurrentTerm(String currentTerm) {
		this.currentTerm = currentTerm;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getCurrentPrincipal() {
		return currentPrincipal;
	}

	public void setCurrentPrincipal(String currentPrincipal) {
		this.currentPrincipal = currentPrincipal;
	}

	public String getCurrentAccrual() {
		return currentAccrual;
	}

	public void setCurrentAccrual(String currentAccrual) {
		this.currentAccrual = currentAccrual;
	}

	public String getCounterFee() {
		return counterFee;
	}

	public void setCounterFee(String counterFee) {
		this.counterFee = counterFee;
	}

	public String getAssureFee() {
		return assureFee;
	}

	public void setAssureFee(String assureFee) {
		this.assureFee = assureFee;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getFee1() {
		return fee1;
	}

	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}

	public String getFee2() {
		return fee2;
	}

	public void setFee2(String fee2) {
		this.fee2 = fee2;
	}

	public String getFee3() {
		return fee3;
	}

	public void setFee3(String fee3) {
		this.fee3 = fee3;
	}

	public String getPrincipalBalance() {
		return principalBalance;
	}

	public void setPrincipalBalance(String principalBalance) {
		this.principalBalance = principalBalance;
	}

	public String getRepaymentAll() {
		return repaymentAll;
	}

	public void setRepaymentAll(String repaymentAll) {
		this.repaymentAll = repaymentAll;
	}

}
