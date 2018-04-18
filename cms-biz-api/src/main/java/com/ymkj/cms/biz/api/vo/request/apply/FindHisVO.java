package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FindHisVO {
	private String ifHaveHistoryApply = "N";//是否有历史借款
	
	private String ifHaveBreaks = "N";//上笔结清是否有减免
	private List<Integer> repaymentTerm;//还款期数
	
	private List<Date> realityRepaymentDate;//实际还款日期
	private List<Date> shouldRepaymentDate;//应还日期
	private List<Integer> repaymentStatue;//还款状态
	private List<BigDecimal> grantMoney;//放款金额
	public String getIfHaveHistoryApply() {
		return ifHaveHistoryApply;
	}
	public void setIfHaveHistoryApply(String ifHaveHistoryApply) {
		this.ifHaveHistoryApply = ifHaveHistoryApply;
	}
	public String getIfHaveBreaks() {
		return ifHaveBreaks;
	}
	public void setIfHaveBreaks(String ifHaveBreaks) {
		this.ifHaveBreaks = ifHaveBreaks;
	}
	public List<Integer> getRepaymentTerm() {
		return repaymentTerm;
	}
	public void setRepaymentTerm(List<Integer> repaymentTerm) {
		this.repaymentTerm = repaymentTerm;
	}
	public List<Date> getRealityRepaymentDate() {
		return realityRepaymentDate;
	}
	public void setRealityRepaymentDate(List<Date> realityRepaymentDate) {
		this.realityRepaymentDate = realityRepaymentDate;
	}
	public List<Date> getShouldRepaymentDate() {
		return shouldRepaymentDate;
	}
	public void setShouldRepaymentDate(List<Date> shouldRepaymentDate) {
		this.shouldRepaymentDate = shouldRepaymentDate;
	}
	public List<Integer> getRepaymentStatue() {
		return repaymentStatue;
	}
	public void setRepaymentStatue(List<Integer> repaymentStatue) {
		this.repaymentStatue = repaymentStatue;
	}
	public List<BigDecimal> getGrantMoney() {
		return grantMoney;
	}
	public void setGrantMoney(List<BigDecimal> grantMoney) {
		this.grantMoney = grantMoney;
	}
	
}
