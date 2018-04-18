package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.List;

public class FindHistoryLoanVO {
	
	/**
	 * 	Y有  N无
	  	取最近一笔结清的借款，判断是否有过减免操作
	 */
	private String ifHaveBreaks;//上笔结清是否有减免
	
	
	/**
	 * 	数组   [0,12,12]
		第一项：取最近一次结清借款总期数，若无给0
		第二项：按放款日期取最近一笔正常或逾期的总期数，若无给0
		第三项：按放款日期取最近第二笔正常或逾期的总期数，若无给0
	 */
	private Integer[] borrowingTerm;//借款期数
	
	/**
	 * 借款数据次序与借款期数对应
	 */
	private List<RepayListVo> loanList;//借款列表

	/**
	 * 放款金额 
	 * @return
	 */
	private  BigDecimal[]  grantMoney;
	public String getIfHaveBreaks() {
		return ifHaveBreaks;
	}

	public void setIfHaveBreaks(String ifHaveBreaks) {
		this.ifHaveBreaks = ifHaveBreaks;
	}

	public Integer[] getBorrowingTerm() {
		return borrowingTerm;
	}

	public void setBorrowingTerm(Integer[] borrowingTerm) {
		this.borrowingTerm = borrowingTerm;
	}

	public List<RepayListVo> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<RepayListVo> loanList) {
		this.loanList = loanList;
	}

	public BigDecimal[] getGrantMoney() {
		return grantMoney;
	}

	public void setGrantMoney(BigDecimal[] grantMoney) {
		this.grantMoney = grantMoney;
	}
	
}
