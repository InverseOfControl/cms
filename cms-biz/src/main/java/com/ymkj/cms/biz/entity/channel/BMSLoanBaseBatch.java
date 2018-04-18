package com.ymkj.cms.biz.entity.channel;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:查询借款信息 response vo
 */
public class BMSLoanBaseBatch  extends BaseEntity{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3182644225276388436L;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 证件号码
	 */
	private String idNo;
	
	/**
	 * 借款编号
	 */
	private String loanNo;	
	
	/**
	 * 产品类型
	 */
	private String productName;
	
	/**
	 * 利率
	 */
	private String rateey;
	
	/**
	 * 	期限
	 */
	private String time;
	
	/**
	 * 合同金额
	 */
	private String pactMoney;
	
	/**
	 * 放款金额
	 */
	private String grantMoney;
	
	/**
	 * 首期还款日
	 */
	private String startRDateForT1;
	
	private String loanBaseId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRateey() {
		return rateey;
	}

	public void setRateey(String rateey) {
		this.rateey = rateey;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPactMoney() {
		return pactMoney;
	}

	public void setPactMoney(String pactMoney) {
		this.pactMoney = pactMoney;
	}

	public String getGrantMoney() {
		return grantMoney;
	}

	public void setGrantMoney(String grantMoney) {
		this.grantMoney = grantMoney;
	}

	public String getStartRDateForT1() {
		return startRDateForT1;
	}

	public void setStartRDateForT1(String startRDateForT1) {
		this.startRDateForT1 = startRDateForT1;
	}

	public String getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(String loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	
	

}
