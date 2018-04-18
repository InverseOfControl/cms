package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqLoanTrialVO</p>
 * <p>Description:贷前试算请求对象</p>
 * @uthor YM10159
 * @date 2017年3月7日 下午5:22:18
 */
public class ReqLoanTrialVO extends Request{

	private static final long serialVersionUID = 7116962623972817901L;
	
	public ReqLoanTrialVO(){
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 贷款类型编码
	 */
	private String loanType;
	/**
	 * 借款金额
	 */
	private float money;
	/**
	 * 借款期数
	 */
	private long time;
	/**
	 * 预计首次还款日  yyyy-MM-dd
	 */
	private String firstRepaymentDate;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getFirstRepaymentDate() {
		return firstRepaymentDate;
	}
	public void setFirstRepaymentDate(String firstRepaymentDate) {
		this.firstRepaymentDate = firstRepaymentDate;
	}
	
}
