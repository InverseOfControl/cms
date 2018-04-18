package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSProductAuditLimitVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2987093844879978413L;
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
	 * 版本号
	 */
	private Long version;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建人ID
	 */
	private Long creatorId;
	/**
	 * 创建时间
	 */
	private Date creatorDate;
	/**
	 * 修改人
	 */
	private String modified;
	/**
	 * 修改人Id
	 */
	private Long modifiedId;
	/**
	 * 修改时间
	 */
	private Date modifiedDate;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	
	
	/**
	 * 配置冲突
	 * @return
	 */
	private String configureConflict;
	/**
	 * 期限名称
	 * @return
	 */
	private String auditLimitName;
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreatorDate() {
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Long getModifiedId() {
		return modifiedId;
	}
	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public Long getAuditLimitId() {
		return auditLimitId;
	}
	public void setAuditLimitId(Long auditLimitId) {
		this.auditLimitId = auditLimitId;
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
	public String getAuditLimitName() {
		return auditLimitName;
	}
	public void setAuditLimitName(String auditLimitName) {
		this.auditLimitName = auditLimitName;
	}
	

}
