package com.ymkj.cms.biz.entity.master;

public class BMSChannel extends BMSProductBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598093013297842284L;

	private int id;

	private String channelId;

	private String name;

	private String code;

	private String calculateUrl;

	private String startSalesTime;

	private String stopSalesTime;
	/**
	 * 是否启用
	 */
	private Long isDisabled;
	/**
	 * 电子合同是否禁用（0：启用，1禁用）默认值1
	 */
	private Long econtractFlag;
	/**
	 * 纸质合同是否禁用（0：启用，1禁用）默认值1
	 */
	private Long pcontractFlag;
	
	private String sysCode;
	
	/**
	 * 是否可优惠 是否可优惠（0 是 1 不是）
	 */
	private Long isCanPreferential;
	
	
	public Long getIsCanPreferential() {
		return isCanPreferential;
	}

	public void setIsCanPreferential(Long isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCalculateUrl() {
		return calculateUrl;
	}

	public void setCalculateUrl(String calculateUrl) {
		this.calculateUrl = calculateUrl;
	}

	public String getStartSalesTime() {
		return startSalesTime;
	}

	public void setStartSalesTime(String startSalesTime) {
		this.startSalesTime = startSalesTime;
	}

	public String getStopSalesTime() {
		return stopSalesTime;
	}

	public void setStopSalesTime(String stopSalesTime) {
		this.stopSalesTime = stopSalesTime;
	}

	public Long getEcontractFlag() {
		return econtractFlag;
	}

	public void setEcontractFlag(Long econtractFlag) {
		this.econtractFlag = econtractFlag;
	}

	public Long getPcontractFlag() {
		return pcontractFlag;
	}

	public void setPcontractFlag(Long pcontractFlag) {
		this.pcontractFlag = pcontractFlag;
	}
	
}
