package com.ymkj.cms.biz.entity.sign;

import java.math.BigDecimal;

import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;


public class BMSRepaymentDetail extends BMSProductBaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2752121299018388421L;
	
	private BigDecimal currentAccrual;          //当期利息
	private Long currentTerm;                //当前期数
	private BigDecimal giveBackRate;            //一次性还款退费
	private Long loanId;                         //借款ID
	private BigDecimal principalBalance;       //本金余额
	private BigDecimal repaymentAll;            //一次性还款金额
	private String returnDate;                    //还款日期
	private BigDecimal deficit;                  //剩余欠款,用于记录不足额部分
	private String repaymentState;             //当前还款状态
	private String factReturnDate;               //结清日期
	private String penaltyDate;                  //罚息起算日期
	private BigDecimal returneterm;            //每期还款金额
	private BigDecimal penalty;                //违约金
	private BigDecimal accrualRevise;         //对应第三方的贴息或扣息 (积木盒子)
	private Long id;
	private Long loanBaseId;
	private String loanNo;

    
	public BigDecimal getCurrentAccrual() {
		return currentAccrual;
	}
	public void setCurrentAccrual(BigDecimal currentAccrual) {
		this.currentAccrual = currentAccrual;
	}
	public Long getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Long currentTerm) {
		this.currentTerm = currentTerm;
	}
	public BigDecimal getGiveBackRate() {
		return giveBackRate;
	}
	public void setGiveBackRate(BigDecimal giveBackRate) {
		this.giveBackRate = giveBackRate;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public BigDecimal getPrincipalBalance() {
		return principalBalance;
	}
	public void setPrincipalBalance(BigDecimal principalBalance) {
		this.principalBalance = principalBalance;
	}
	public BigDecimal getRepaymentAll() {
		return repaymentAll;
	}
	public void setRepaymentAll(BigDecimal repaymentAll) {
		this.repaymentAll = repaymentAll;
	}

	public BigDecimal getDeficit() {
		return deficit;
	}
	public void setDeficit(BigDecimal deficit) {
		this.deficit = deficit;
	}
	public String getRepaymentState() {
		return repaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		this.repaymentState = repaymentState;
	}

	public BigDecimal getReturneterm() {
		return returneterm;
	}
	public void setReturneterm(BigDecimal returneterm) {
		this.returneterm = returneterm;
	}
	public BigDecimal getPenalty() {
		return penalty;
	}
	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}
	public BigDecimal getAccrualRevise() {
		return accrualRevise;
	}
	public void setAccrualRevise(BigDecimal accrualRevise) {
		this.accrualRevise = accrualRevise;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReturnDate() {
		return returnDate;
	}
	//
	public String getReturnSimpDate() {
		String returnDate =this.returnDate;
		if(!StringUtils.isBlank(returnDate)){
			returnDate=returnDate.substring(0,10);
		}
		return returnDate;
	} 
	
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getFactReturnDate() {
		return factReturnDate;
	}
	public void setFactReturnDate(String factReturnDate) {
		this.factReturnDate = factReturnDate;
	}
	public String getPenaltyDate() {
		return penaltyDate;
	}
	public void setPenaltyDate(String penaltyDate) {
		this.penaltyDate = penaltyDate;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
/*	public static void main(String[] args) {
		String returnDate = "2018-06-01 00:00:00.000";
		String returnDateformat =returnDate.substring(0,10);
		System.err.println(returnDateformat);
	}*/
	 
}

