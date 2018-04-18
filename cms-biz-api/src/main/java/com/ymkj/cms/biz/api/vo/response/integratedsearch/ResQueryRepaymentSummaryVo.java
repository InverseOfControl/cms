package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResQueryRepaymentSummaryVo</p>
 * <p>Description:还款汇总信息接口响应对象</p>
 * @uthor YM10159
 * @date 2017年3月14日 上午10:42:18
 */
public class ResQueryRepaymentSummaryVo extends Request{

	private static final long serialVersionUID = 5973053361625787084L;
	
	/**
	 * 债权唯一码
	 */
	private String loanNo;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 身份证号
	 */
	private String idNum;
	/**
	 * 手机
	 */
	private String mphone;
	/**
	 * 逾期起始日
	 */
	private String overDueDate;
	/**
	 * 逾期总数
	 */
	private String overDueTerm;
	/**
	 * 逾期利息
	 */
	private String overInterest;
	/**
	 * 逾期本金
	 */
	private String overCorpus;
	/**
	 * 罚息起算日
	 */
	private String fineDate;
	/**
	 * 罚息天数
	 */
	private String fineDay;
	/**
	 * 剩余本息和
	 */
	private String remnant;
	/**
	 * 罚息金额
	 */
	private String fine;
	/**
	 * 当期还款日
	 */
	private String currDate;
	/**
	 * 当前期数
	 */
	private String currTerm;
	/**
	 * 当期利息
	 */
	private String currInterest;
	/**
	 * 当期本金
	 */
	private String currCorpus;
	/**
	 * 挂账金额
	 */
	private String accAmount;
	/**
	 * 应还总额（不含当期）
	 */
	private String overdueAmount;
	/**
	 * 应还总额（包含当期）
	 */
	private String currAmount;
	/**
	 * 一次性还款金额合计
	 */
	private String oneTimeRepayment;
	/**
	 * 查询时间
	 */
	private String tradeDate;
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getOverDueDate() {
		return overDueDate;
	}
	public void setOverDueDate(String overDueDate) {
		this.overDueDate = overDueDate;
	}
	public String getOverDueTerm() {
		return overDueTerm;
	}
	public void setOverDueTerm(String overDueTerm) {
		this.overDueTerm = overDueTerm;
	}
	public String getOverInterest() {
		return overInterest;
	}
	public void setOverInterest(String overInterest) {
		this.overInterest = overInterest;
	}
	public String getOverCorpus() {
		return overCorpus;
	}
	public void setOverCorpus(String overCorpus) {
		this.overCorpus = overCorpus;
	}
	public String getFineDate() {
		return fineDate;
	}
	public void setFineDate(String fineDate) {
		this.fineDate = fineDate;
	}
	public String getFineDay() {
		return fineDay;
	}
	public void setFineDay(String fineDay) {
		this.fineDay = fineDay;
	}
	public String getRemnant() {
		return remnant;
	}
	public void setRemnant(String remnant) {
		this.remnant = remnant;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	public String getCurrDate() {
		return currDate;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	public String getCurrTerm() {
		return currTerm;
	}
	public void setCurrTerm(String currTerm) {
		this.currTerm = currTerm;
	}
	public String getCurrInterest() {
		return currInterest;
	}
	public void setCurrInterest(String currInterest) {
		this.currInterest = currInterest;
	}
	public String getCurrCorpus() {
		return currCorpus;
	}
	public void setCurrCorpus(String currCorpus) {
		this.currCorpus = currCorpus;
	}
	public String getAccAmount() {
		return accAmount;
	}
	public void setAccAmount(String accAmount) {
		this.accAmount = accAmount;
	}
	public String getOverdueAmount() {
		return overdueAmount;
	}
	public void setOverdueAmount(String overdueAmount) {
		this.overdueAmount = overdueAmount;
	}
	public String getCurrAmount() {
		return currAmount;
	}
	public void setCurrAmount(String currAmount) {
		this.currAmount = currAmount;
	}
	public String getOneTimeRepayment() {
		return oneTimeRepayment;
	}
	public void setOneTimeRepayment(String oneTimeRepayment) {
		this.oneTimeRepayment = oneTimeRepayment;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
}
