package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YM10189
 * @date 2017年5月9日
 * @Description:导出划拨申请书 response vo
 */
public class ResAppBookVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6680361214943748990L;

	/**
	 * 批次号
	 */
	private String bacthNum;

	/**
	 * 贷款类型
	 */
	private String loanType;

	/**
	 * 应发放贷款本金
	 */
	private String sumPactMoney;

	/**
	 * 笔数
	 */
	private String quantity;
	
	private BigDecimal diffMoney;

	public String getBacthNum() {
		return bacthNum;
	}

	public void setBacthNum(String bacthNum) {
		this.bacthNum = bacthNum;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getSumPactMoney() {
		return sumPactMoney;
	}

	public void setSumPactMoney(String sumPactMoney) {
		this.sumPactMoney = sumPactMoney;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDiffMoney() {
		return diffMoney;
	}

	public void setDiffMoney(BigDecimal diffMoney) {
		this.diffMoney = diffMoney;
	}
	
	

}
