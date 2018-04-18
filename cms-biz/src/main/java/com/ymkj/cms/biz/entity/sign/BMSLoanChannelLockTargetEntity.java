package com.ymkj.cms.biz.entity.sign;

import com.ymkj.cms.biz.entity.base.BMSBaseEntity;

public class BMSLoanChannelLockTargetEntity extends BMSBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8272244149528243975L;
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
	 * 锁定Y，退回N
	 */
	private String lockTarget;
	
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
	public String getLockTarget() {
		return lockTarget;
	}
	public void setLockTarget(String lockTarget) {
		this.lockTarget = lockTarget;
	}
	
}
