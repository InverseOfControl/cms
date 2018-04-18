package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSProductVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2987093844879978413L;
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
	 * 产品额度上限
	 */
	private BigDecimal floorLimit;
	/**
	 * 产品额度下限
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
	 * 调整基数
	 */
	private BigDecimal adjustBase;
	/**
	 *  产品期限配置，启用，禁用
	 */
	private Long blcIsDisabled;
	
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
	public String getName() {
		return name;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
