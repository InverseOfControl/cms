package com.ymkj.cms.biz.api.vo.response.sign;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;


public class ResLoanContractInfoVo implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1819670118331045598L;
	
	private BigDecimal pactMoney ;	//合同金额：XXX元 
	private BigDecimal referRate;  //咨询费：XXX元  
	private BigDecimal evalRate; //评估费：XXX元
	private BigDecimal manageRate;  //管理费：XXX元
	private BigDecimal risk ; //风险金：XXX元  

	
	private Long currentTerm; //期数 
	private String returnDate;  //应还款日
	private BigDecimal returneterm;  //还款金额
	private BigDecimal repaymentAll; //当期一次性还款金额 
	
	public BigDecimal getPactMoney() {
		return pactMoney;
	}
	public void setPactMoney(BigDecimal pactMoney) {
		this.pactMoney = pactMoney;
	}
	public BigDecimal getReferRate() {
		return referRate;
	}
	public void setReferRate(BigDecimal referRate) {
		this.referRate = referRate;
	}
	public BigDecimal getEvalRate() {
		return evalRate;
	}
	public void setEvalRate(BigDecimal evalRate) {
		this.evalRate = evalRate;
	}
	public BigDecimal getManageRate() {
		return manageRate;
	}
	public void setManageRate(BigDecimal manageRate) {
		this.manageRate = manageRate;
	}
	public BigDecimal getRisk() {
		return risk;
	}
	public void setRisk(BigDecimal risk) {
		this.risk = risk;
	}
	public Long getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Long currentTerm) {
		this.currentTerm = currentTerm;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
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

	

		


		
}
