package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;

public class BMSProduct extends BMSProductBaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8752185080815384757L;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 产品CODE
	 */
	private String code;
	/**
	 * 产品描述
	 */
	private String depict;
	/**
	 * 产品额度下限
	 */
	private BigDecimal floorLimit;
	/**
	 * 产品额度上限
	 */
	private  BigDecimal upperLimit;
	/**
	 * 费率
	 */
	private BigDecimal rate;
	
	/**
	 * 优惠费率
	 */
	private BigDecimal preferentialRate;

	/**
	 * 调整基数
	 */
	private BigDecimal adjustBase;
	
	/**
	 * 产品期限配置，启用，禁用
	 */
	private Long blcIsDisabled;
	
	private String sysCode;
	/**
	 * 总负债率
	 */
	private BigDecimal debtRadio;
	
	
	
	public BigDecimal getPreferentialRate() {
		return preferentialRate;
	}
	public void setPreferentialRate(BigDecimal preferentialRate) {
		this.preferentialRate = preferentialRate;
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
	public BigDecimal getAdjustBase() {
		return adjustBase;
	}
	public void setAdjustBase(BigDecimal adjustBase) {
		this.adjustBase = adjustBase;
	}
	/**
	 * 渠道ID
	 */
	private Long channelId;
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;

	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	public BigDecimal getFloorLimit() {
		return floorLimit;
	}
	public void setFloorLimit(BigDecimal floorLimit) {
		this.floorLimit = floorLimit;
	}
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Long getBlcIsDisabled() {
		return blcIsDisabled;
	}
	public void setBlcIsDisabled(Long blcIsDisabled) {
		this.blcIsDisabled = blcIsDisabled;
	}
	public BigDecimal getDebtRadio() {
		return debtRadio;
	}
	public void setDebtRadio(BigDecimal debtRadio) {
		this.debtRadio = debtRadio;
	}
	

}
