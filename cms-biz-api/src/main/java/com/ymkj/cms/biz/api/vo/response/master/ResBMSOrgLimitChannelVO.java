package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResBMSOrgLimitChannelVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5323905307243378211L;
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
	 * 营业网点名称
	 */
	private String orgName;
	
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
	 * 父ID
	 */
	private Long orgParentId;
	/**
	 * 深度
	 */
	private String orgDeep;
	/**
	 * 借款编号
	 */
	private String loanNo;
	
	/**
	 * 配置冲突
	 * @return
	 */
	
	private String configureConflict;
	/**
	 * 优惠配置
	 */
	private Integer isCanPreferential;
	
	public Long getOrgParentId() {
		return orgParentId;
	}
	public void setOrgParentId(Long orgParentId) {
		this.orgParentId = orgParentId;
	}
	public String getOrgDeep() {
		return orgDeep;
	}
	public void setOrgDeep(String orgDeep) {
		this.orgDeep = orgDeep;
	}
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
