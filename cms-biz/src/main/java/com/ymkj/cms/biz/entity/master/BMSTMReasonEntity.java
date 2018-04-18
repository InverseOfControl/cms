package com.ymkj.cms.biz.entity.master;

import java.util.List;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSTMReasonEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5459354728919853184L;
	
	private Long id;
	private String reason; //原因
	private String code; //原因码
	private String reasonTexplain;//原因解释
	private String type;//分类(1级 2级)
	private Long parentId;//父原因ID
	private Long level;//排序
	private String operationModule;//操作模块
	private String operationType;//操作类型
	private String remark;//备注
	private int isBlackList;
	private int canRequestDays;//限制天数
	
	private String conditionType;//类型
	
	private String text;
	
	private List<BMSTMReasonEntity> children;
	public int getCanRequestDays() {
		return canRequestDays;
	}
	public void setCanRequestDays(int canRequestDays) {
		this.canRequestDays = canRequestDays;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public int getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(int isBlackList) {
		this.isBlackList = isBlackList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public String getOperationModule() {
		return operationModule;
	}
	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
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
	
	public List<BMSTMReasonEntity> getChildren() {
		return children;
	}
	public void setChildren(List<BMSTMReasonEntity> children) {
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
