package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;


public class BMSOrgLimitChannel extends BMSProductBaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5439062446060761605L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 审核期限ID
	 */
	private Long auditLimitId;
	/**
	 *门店ID
	 */
	private Long orgId;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	/**
	 * 产品配置类型
	 */
	private String productDeployCode;
	
	
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
	 * 产品下限
	 */
	private BigDecimal proFloorLimit;
	/**
	 * 产品上限
	 */
	private BigDecimal proUpperLimit;
	/**
	 * 产品调整基数
	 */
	private BigDecimal proAdjustBase;
	
	
	/**
	 * 配置冲突
	 * @return
	 */
	private String configureConflict;
	/**
	 * 产品调整基数
	 */
	private Integer isCanPreferential;
	
	public BigDecimal getProFloorLimit() {
		return proFloorLimit;
	}

	public void setProFloorLimit(BigDecimal proFloorLimit) {
		this.proFloorLimit = proFloorLimit;
	}

	public BigDecimal getProUpperLimit() {
		return proUpperLimit;
	}

	public void setProUpperLimit(BigDecimal proUpperLimit) {
		this.proUpperLimit = proUpperLimit;
	}

	public BigDecimal getProAdjustBase() {
		return proAdjustBase;
	}

	public void setProAdjustBase(BigDecimal proAdjustBase) {
		this.proAdjustBase = proAdjustBase;
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

	

	public Integer getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
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

	public String getProductDeployCode() {
		return productDeployCode;
	}

	public void setProductDeployCode(String productDeployCode) {
		this.productDeployCode = productDeployCode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getConfigureConflict() {
		return configureConflict;
	}

	public void setConfigureConflict(String configureConflict) {
		this.configureConflict = configureConflict;
	}
	
	public Integer getIsCanPreferential() {
		return isCanPreferential;
	}

	public void setIsCanPreferential(Integer isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}
	
	
	
}
