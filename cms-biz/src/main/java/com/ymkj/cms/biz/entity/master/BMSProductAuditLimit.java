package com.ymkj.cms.biz.entity.master;

import java.math.BigDecimal;

public class BMSProductAuditLimit extends BMSProductBaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8429081012465864238L;
	/**
	 * 审批期限ID
	 */
	private Long auditLimitId;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 产品代码
	 */
	private String productCode;
	/**
	 * 审批期限
	 */
	private Long auditLimit;
	/**
	 * 阀值上限
	 */
	private BigDecimal upperLimit;
	/**
	 * 阀值下限
	 */
	private BigDecimal floorLimit; 
	/**
	 * 是否可用
	 */
	private Long isDisabled;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	
	/**
	 * 配置冲突
	 * @return
	 */
	private String configureConflict;
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public Long getAuditLimitId() {
		return auditLimitId;
	}
	public void setAuditLimitId(Long auditLimitId) {
		this.auditLimitId = auditLimitId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Long getAuditLimit() {
		return auditLimit;
	}
	public void setAuditLimit(Long auditLimit) {
		this.auditLimit = auditLimit;
	}
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}
	public BigDecimal getFloorLimit() {
		return floorLimit;
	}
	public void setFloorLimit(BigDecimal floorLimit) {
		this.floorLimit = floorLimit;
	}
	public Long getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}
	public String getConfigureConflict() {
		return configureConflict;
	}
	public void setConfigureConflict(String configureConflict) {
		this.configureConflict = configureConflict;
	}
	
	
	

}
