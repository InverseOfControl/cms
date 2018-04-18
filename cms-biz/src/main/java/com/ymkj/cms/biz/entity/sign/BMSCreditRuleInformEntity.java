package com.ymkj.cms.biz.entity.sign;

import com.ymkj.cms.biz.entity.base.BMSBaseEntity;

public class BMSCreditRuleInformEntity extends BMSBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2507664901652851243L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 借款ID
	 */
	private Long loanBaseId;
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 渠道code
	 */
	private String channelCode;
	/**
	 * 是否可签约 true 可以签约 false 不可以
	 */
	private String result;
	/**
	 * 规则类型
	 */
	private String checkRule;
	/**
	 * 征信系统消息提示
	 */
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCheckRule() {
		return checkRule;
	}
	public void setCheckRule(String checkRule) {
		this.checkRule = checkRule;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
