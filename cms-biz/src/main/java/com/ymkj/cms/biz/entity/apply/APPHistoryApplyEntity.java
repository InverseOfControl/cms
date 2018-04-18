package com.ymkj.cms.biz.entity.apply;

import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class APPHistoryApplyEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ifHaveHistoryApply;//是否有历史借款
	private List<Date> shouldRepaymentDate;//应还日期
	private List<Date> realityRepaymentDate;//实际还款日期
	private String ifHaveBreaks;//上笔结清是否有减免
	private List<String> repaymentStatue;//还款状态
	private List<Integer> repaymentTerm;//还款期数
	
	
	public String getIfHaveHistoryApply() {
		return ifHaveHistoryApply;
	}
	public void setIfHaveHistoryApply(String ifHaveHistoryApply) {
		this.ifHaveHistoryApply = ifHaveHistoryApply;
	}
	public List<Date> getShouldRepaymentDate() {
		return shouldRepaymentDate;
	}
	public void setShouldRepaymentDate(List<Date> shouldRepaymentDate) {
		this.shouldRepaymentDate = shouldRepaymentDate;
	}
	public List<Date> getRealityRepaymentDate() {
		return realityRepaymentDate;
	}
	public void setRealityRepaymentDate(List<Date> realityRepaymentDate) {
		this.realityRepaymentDate = realityRepaymentDate;
	}
	public String getIfHaveBreaks() {
		return ifHaveBreaks;
	}
	public void setIfHaveBreaks(String ifHaveBreaks) {
		this.ifHaveBreaks = ifHaveBreaks;
	}
	public List<String> getRepaymentStatue() {
		return repaymentStatue;
	}
	public void setRepaymentStatue(List<String> repaymentStatue) {
		this.repaymentStatue = repaymentStatue;
	}
	public List<Integer> getRepaymentTerm() {
		return repaymentTerm;
	}
	public void setRepaymentTerm(List<Integer> repaymentTerm) {
		this.repaymentTerm = repaymentTerm;
	}

	
	
}

