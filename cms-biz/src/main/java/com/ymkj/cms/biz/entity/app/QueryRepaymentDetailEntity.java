package com.ymkj.cms.biz.entity.app;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class QueryRepaymentDetailEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 当前期数
	 */
	private Long currentTerm;
	/**
	 * 应还款日
	 */
	private Date returnDate;
	/**
	 * 实际还款日
	 */
	private Date factReturnDate;
	/**
	 * 还款金额
	 */
	private BigDecimal returneterm;
	/**
	 * 当期一次性还款金额
	 */
	private BigDecimal repaymentAll;
	/**
	 * 当期还款状态
	 */
	private String repaymentState;
	/**
	 * 当期剩余欠款
	 */
	private BigDecimal deficit;
	/**
	 * 还款方
	 */
	private String borrowerName;
	public Long getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Long currentTerm) {
		this.currentTerm = currentTerm;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getFactReturnDate() {
		return factReturnDate;
	}
	public void setFactReturnDate(Date factReturnDate) {
		this.factReturnDate = factReturnDate;
	}
	public BigDecimal getReturneterm() {
		return returneterm;
	}
	public void setReturneterm(BigDecimal returneterm) {
		this.returneterm = returneterm;
	}
	public BigDecimal getRepaymentAll() {
		return repaymentAll;
	}
	public void setRepaymentAll(BigDecimal repaymentAll) {
		this.repaymentAll = repaymentAll;
	}
	public String getRepaymentState() {
		return repaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		this.repaymentState = repaymentState;
	}
	public BigDecimal getDeficit() {
		return deficit;
	}
	public void setDeficit(BigDecimal deficit) {
		this.deficit = deficit;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	
	
}
