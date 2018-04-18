package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSRejectReasonVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	 
	private Long id;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	
		
}
