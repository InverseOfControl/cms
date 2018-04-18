package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResLoanTrialVO</p>
 * <p>Description:贷前试算接口响应对象</p>
 * @uthor YM10159
 * @date 2017年3月8日 下午3:29:34
 */
public class ResTrialBeforeCreditVO implements Serializable{
	
	private static final long serialVersionUID = 7588645883432679229L;
	
	private Long currentTerm;
	private String returnDate; 
	private double repaymentAll;
	private double returneterm;
	
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
	public double getRepaymentAll() {
		return repaymentAll;
	}
	public void setRepaymentAll(double repaymentAll) {
		this.repaymentAll = repaymentAll;
	}
	public double getReturneterm() {
		return returneterm;
	}
	public void setReturneterm(double returneterm) {
		this.returneterm = returneterm;
	}
}
