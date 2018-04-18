package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSReasonVO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3594026422304420488L;
	
	
	/**
	 * 主键ID
	 */
	private Long Id;
	
	/**
	 * 原因
	 */
	private String reason;
	/**
	 * 原因码
	 */
	private String code;
	/**
	 * 原因解释
	 */
	private String reasonTexplain;
	/**
	 * 分类(1级,2级)
	 */
	private String type;
	/**
	 * 父原因ID
	 */
	private Long parentId;
	/**
	 * 排序
	 */
	private Integer levelOrder;
	/**
	 * 类型
	 */
	private String operationType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 操作模块
	 */
	private String operationModule;
	/**
	 * 是否擦入黑名单
	 */
	private Integer isBlacklist;
	/**
	 * 限制天数 
	 */
	private Integer canRequestDays;
	
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
	private String firstReason;
	private String moduleName;
	private Integer isDisabled;  //是否禁用
	
	private String conditionType;  //状态类型
	
	private String relationCode;
	
	private String texPlain;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReasonTexplain() {
		return reasonTexplain;
	}
	public void setReasonTexplain(String reasonTexplain) {
		this.reasonTexplain = reasonTexplain;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getLevelOrder() {
		return levelOrder;
	}
	public void setLevelOrder(Integer levelOrder) {
		this.levelOrder = levelOrder;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperationModule() {
		return operationModule;
	}
	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}
	public Integer getIsBlacklist() {
		return isBlacklist;
	}
	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	public Integer getCanRequestDays() {
		return canRequestDays;
	}
	public void setCanRequestDays(Integer canRequestDays) {
		this.canRequestDays = canRequestDays;
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
	public String getFirstReason() {
		return firstReason;
	}
	public void setFirstReason(String firstReason) {
		this.firstReason = firstReason;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	public String getTexPlain() {
		return texPlain;
	}
	public void setTexPlain(String texPlain) {
		this.texPlain = texPlain;
	}
	

}
