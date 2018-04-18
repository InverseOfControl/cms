package com.ymkj.cms.biz.api.vo.request.apply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;



/**
 * 审批意见对外接口VO(规则引擎调用)
 * 
 * @author hcr
 * @date 2017年6月23日
 */
public class ApprovalOpinionsVO extends Request implements Serializable {
	private static final long serialVersionUID = -9096725085322993544L;
	// Fields
	// private Integer incomeCertificate;// 收入证明金额
	private List<Integer> personalWater = new ArrayList<Integer>();// 个人流水
	private List<Integer> personMonthAverage = new ArrayList<Integer>();// 个人流水月均
	private List<Integer> publicWater = new ArrayList<Integer>();// 对公流水
	private List<Integer> publicMonthAverage = new ArrayList<Integer>();// 对公流水月均
	private Integer monthAverage; // 月平均金额
	// private String creditRecord = "N";// 是否有信用记录
	private Integer threeMonthsCount;// 近三个月查询
	private Integer oneMonthsCount;// 近一个月查询
	private Integer creditLoanDebt;// 信用卡贷款负债
	private Integer outDebtTotal;// 外部负债总额
	private String approvalProductCd;// 审批产品
	private Integer approvalCheckIncome;// 核实收入
	private Integer approvalLimit;// 审批额度
	private Integer approvalTerm;// 审批期限
	public List<Integer> getPersonalWater() {
		return personalWater;
	}
	public void setPersonalWater(List<Integer> personalWater) {
		this.personalWater = personalWater;
	}
	public List<Integer> getPersonMonthAverage() {
		return personMonthAverage;
	}
	public void setPersonMonthAverage(List<Integer> personMonthAverage) {
		this.personMonthAverage = personMonthAverage;
	}
	public List<Integer> getPublicWater() {
		return publicWater;
	}
	public void setPublicWater(List<Integer> publicWater) {
		this.publicWater = publicWater;
	}
	public List<Integer> getPublicMonthAverage() {
		return publicMonthAverage;
	}
	public void setPublicMonthAverage(List<Integer> publicMonthAverage) {
		this.publicMonthAverage = publicMonthAverage;
	}
	public Integer getMonthAverage() {
		return monthAverage;
	}
	public void setMonthAverage(Integer monthAverage) {
		this.monthAverage = monthAverage;
	}
	public Integer getThreeMonthsCount() {
		return threeMonthsCount;
	}
	public void setThreeMonthsCount(Integer threeMonthsCount) {
		this.threeMonthsCount = threeMonthsCount;
	}
	public Integer getOneMonthsCount() {
		return oneMonthsCount;
	}
	public void setOneMonthsCount(Integer oneMonthsCount) {
		this.oneMonthsCount = oneMonthsCount;
	}
	public Integer getCreditLoanDebt() {
		return creditLoanDebt;
	}
	public void setCreditLoanDebt(Integer creditLoanDebt) {
		this.creditLoanDebt = creditLoanDebt;
	}
	public Integer getOutDebtTotal() {
		return outDebtTotal;
	}
	public void setOutDebtTotal(Integer outDebtTotal) {
		this.outDebtTotal = outDebtTotal;
	}
	public String getApprovalProductCd() {
		return approvalProductCd;
	}
	public void setApprovalProductCd(String approvalProductCd) {
		this.approvalProductCd = approvalProductCd;
	}
	public Integer getApprovalCheckIncome() {
		return approvalCheckIncome;
	}
	public void setApprovalCheckIncome(Integer approvalCheckIncome) {
		this.approvalCheckIncome = approvalCheckIncome;
	}
	public Integer getApprovalLimit() {
		return approvalLimit;
	}
	public void setApprovalLimit(Integer approvalLimit) {
		this.approvalLimit = approvalLimit;
	}
	public Integer getApprovalTerm() {
		return approvalTerm;
	}
	public void setApprovalTerm(Integer approvalTerm) {
		this.approvalTerm = approvalTerm;
	}
	
	
	
}
