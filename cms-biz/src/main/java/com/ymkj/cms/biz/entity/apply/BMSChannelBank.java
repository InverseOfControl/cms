package com.ymkj.cms.biz.entity.apply;

import com.ymkj.cms.biz.entity.master.BMSProductBaseEntity;

public class BMSChannelBank extends BMSProductBaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 渠道id
	 */
	private Long channelId;
	/**
	 * 银行id
	 */
	private Long bankId;
	/**
	 * 是否启用
	 */
	private Long isDisabled;

	/**
	 * 渠道代码
	 */
	private String channeCode;
	/**
	 * 渠道名称
	 */
	private String channeName;
	/**
	 * 银行名称
	 */
	private String bankName;

	private String sysCode;
	
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}


	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getChanneCode() {
		return channeCode;
	}

	public void setChanneCode(String channeCode) {
		this.channeCode = channeCode;
	}

	public String getChanneName() {
		return channeName;
	}

	public void setChanneName(String channeName) {
		this.channeName = channeName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
