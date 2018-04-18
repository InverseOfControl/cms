package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSLimitChannelVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5947768789242930259L;
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
	
    private Long creatorId ; 
	
	private String creator ; 
	
	private  Date creatorDate ; 
	
	private Long modifiedId ; 
	
	private String modified ; 
	
	private Date modifiedDate ; 
	
	private Long isDeleted; 
	
	private Long version ;
	
	
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatorDate() {
		return creatorDate;
	}

	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}

	public Long getModifiedId() {
		return modifiedId;
	}

	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
