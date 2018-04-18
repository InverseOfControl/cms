package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResQueryLoanVo</p>
 * <p>Description:还款详细信息接口响应对象</p>
 * @uthor YM10159
 * @date 2017年3月14日 上午10:42:18
 */
public class ResQueryRepaymentDetailVo extends Request{

	private static final long serialVersionUID = 855147674187849120L;
	
	/**
	 * 当前期数
	 */
	private String currentTerm;
	/**
	 * 应还款日
	 */
	private String returnDate;
	/**
	 * 实际还款日
	 */
	private String factReturnDate;
	/**
	 * 还款金额
	 */
	private String returneterm;
	/**
	 * 当期一次性还款金额
	 */
	private String repaymentAll;
	/**
	 * 当期还款状态
	 */
	private String repaymentState;
	/**
	 * 当期剩余欠款
	 */
	private String deficit;
	/**
	 * 还款方
	 */
	private String borrowerName;
	
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
	public String getFactReturnDate() {
		return factReturnDate;
	}
	public void setFactReturnDate(String factReturnDate) {
		this.factReturnDate = factReturnDate;
	}
	public String getReturneterm() {
		return returneterm;
	}
	public void setReturneterm(String returneterm) {
		this.returneterm = returneterm;
	}
	public String getRepaymentAll() {
		return repaymentAll;
	}
	public void setRepaymentAll(String repaymentAll) {
		this.repaymentAll = repaymentAll;
	}
	public String getRepaymentState() {
		return repaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		this.repaymentState = repaymentState;
	}
	public String getDeficit() {
		return deficit;
	}
	public void setDeficit(String deficit) {
		this.deficit = deficit;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	
}
