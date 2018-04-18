package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;

/**
 * 产品期限渠道表
 * @author cj
 *
 */
public class BMSLimitChannel extends BMSProductBaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7441996405200867359L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 审核期限ID
	 */
	private Long auditLimitId;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	
	/**
	 * 新增加属性
	 * @return
	 */
	/**
	 * 渠道代码
	 */
	private String channelCode;
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 产品代码
	 */
	private String productCode;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 审批期限
	 */
	private Integer auditLimit;
	/**
	 * 是否可用
	 */
	private Integer isDisabled;
	
	/**
	 * 审批下限
	 */
	private  BigDecimal floorLimit;
	/**
	 * 审批上限
	 */
	private BigDecimal upperLimit;
	
	/**
	 *门店ID
	 */
	private Long orgId; 
	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getAuditLimit() {
		return auditLimit;
	}

	public void setAuditLimit(Integer auditLimit) {
		this.auditLimit = auditLimit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuditLimitId() {
		return auditLimitId;
	}

	public void setAuditLimitId(Long auditLimitId) {
		this.auditLimitId = auditLimitId;
	}

	
	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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

}
