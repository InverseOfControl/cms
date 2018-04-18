package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;

import com.ymkj.base.core.biz.api.message.Request;

public class AuditRulesVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanNo;//借款编号
	
	private Integer adviceVerifyIncome;//建议核实收入
	
	private Integer adviceAuditLines;//建议审批额度
	
	private BigDecimal internalDebtRatio;//  内部负债率
	
	private BigDecimal totalDebtRatio;// 总负债率
	
	private Integer scorecardScore;//  评分卡分数
	
	private String ccRuleSet;// 经过的CC规则集的名称
	
	private String isAntifraud;//是否欺诈可疑

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Integer getAdviceVerifyIncome() {
		return adviceVerifyIncome;
	}

	public void setAdviceVerifyIncome(Integer adviceVerifyIncome) {
		this.adviceVerifyIncome = adviceVerifyIncome;
	}

	public Integer getAdviceAuditLines() {
		return adviceAuditLines;
	}

	public void setAdviceAuditLines(Integer adviceAuditLines) {
		this.adviceAuditLines = adviceAuditLines;
	}

	public BigDecimal getInternalDebtRatio() {
		return internalDebtRatio;
	}

	public void setInternalDebtRatio(BigDecimal internalDebtRatio) {
		this.internalDebtRatio = internalDebtRatio;
	}

	public BigDecimal getTotalDebtRatio() {
		return totalDebtRatio;
	}

	public void setTotalDebtRatio(BigDecimal totalDebtRatio) {
		this.totalDebtRatio = totalDebtRatio;
	}

	public Integer getScorecardScore() {
		return scorecardScore;
	}

	public void setScorecardScore(Integer scorecardScore) {
		this.scorecardScore = scorecardScore;
	}

	public String getCcRuleSet() {
		return ccRuleSet;
	}

	public void setCcRuleSet(String ccRuleSet) {
		this.ccRuleSet = ccRuleSet;
	}

	public String getIsAntifraud() {
		return isAntifraud;
	}

	public void setIsAntifraud(String isAntifraud) {
		this.isAntifraud = isAntifraud;
	}
	
	
}
