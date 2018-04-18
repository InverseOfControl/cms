package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSRejectReason extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3826646074452923105L;
	
	
	private String code;
	
	private String reason;
	/** 原因解释*/
	private String resonExplain;
	/** 拒绝分类*/
	private String type;
	/**父原因id*/
	private Long parentId;
	/**排序*/
	private Integer levelOrder;
	/**类型*/
	private Integer operationType;
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	private Integer isBlackList;
	
	
	
	public Integer getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(Integer isBlackList) {
		this.isBlackList = isBlackList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Long getModifierId() {
		return modifierId;
	}
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getVerson() {
		return verson;
	}
	public void setVerson(Integer verson) {
		this.verson = verson;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**是否逻辑删除*/
	private Integer isDeleted;
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**备注*/
	private String remark;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResonExplain() {
		return resonExplain;
	}
	public void setResonExplain(String resonExplain) {
		this.resonExplain = resonExplain;
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
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	 
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

 
	
	
}
