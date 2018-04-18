package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import com.ymkj.base.core.biz.api.message.Request;

public class FlowDetialsVO extends Request{

	private static final long serialVersionUID = -7118614992120715985L;
	
	/**
	 * 明细项目
	 */
	private String acctTitle;
	/**
	 * 明细项目名称
	 */
	private String acctTitleName;
	/**
	 * 期数
	 */
	private String time;
	/**
	 * 明细金额
	 */
	private double tradeAmount;
	
	public String getAcctTitle() {
		return acctTitle;
	}
	public void setAcctTitle(String acctTitle) {
		this.acctTitle = acctTitle;
	}
	public String getAcctTitleName() {
		return acctTitleName;
	}
	public void setAcctTitleName(String acctTitleName) {
		this.acctTitleName = acctTitleName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
}
