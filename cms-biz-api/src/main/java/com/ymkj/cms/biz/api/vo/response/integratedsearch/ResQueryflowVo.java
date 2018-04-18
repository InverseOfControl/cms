package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResQueryLoanVo</p>
 * <p>Description:查看借款响应对象</p>
 * @uthor YM10159
 * @date 2017年3月14日 上午10:42:18
 */
public class ResQueryflowVo extends Request{

	private static final long serialVersionUID = -4663753391701369876L;
	
	/**
	 * 交易日期，格式为yyyy-MM-dd
	 */
	private Date tradeDate;
	/**
	 * 交易方式
	 */
	private String tradeType;
	/**
	 * 交易类型
	 */
	private String tradeCode;
	/**
	 * 交易类型名称
	 */
	private String tradeName;
	/**
	 * 期初余额
	 */
	private double beginBalance;
	/**
	 * 收入
	 */
	private double income;
	/**
	 * 支出
	 */
	private double outlay;
	/**
	 * 期末余额
	 */
	private double endBalance;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 流水号
	 */
	private String tradeNo;
	/**
	 * 流水明细信息列表
	 */
	private List<FlowDetialsVO> flowDetialsVOList;
	
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public double getBeginBalance() {
		return beginBalance;
	}
	public void setBeginBalance(double beginBalance) {
		this.beginBalance = beginBalance;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getOutlay() {
		return outlay;
	}
	public void setOutlay(double outlay) {
		this.outlay = outlay;
	}
	public double getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public List<FlowDetialsVO> getFlowDetialsVOList() {
		return flowDetialsVOList;
	}
	public void setFlowDetialsVOList(List<FlowDetialsVO> flowDetialsVOList) {
		this.flowDetialsVOList = flowDetialsVOList;
	}
	
}
