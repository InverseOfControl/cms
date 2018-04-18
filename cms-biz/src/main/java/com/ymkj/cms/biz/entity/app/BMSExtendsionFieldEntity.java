package com.ymkj.cms.biz.entity.app;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSExtendsionFieldEntity extends BaseEntity {
	
	private static final long serialVersionUID = 4534633115634354143L;
	
	private String id;
	private String fieldCode;
	private String fieldName;
	private String fieldType;
	private String isRequested;
	private String isEdited;
	private String relationCodeType;
	private String defaultValue;
	private String parentGroupCode;
	private String codeIndex;
	private String isHide;
	private String hideLabelCode;
	private String hideLabelName;
	private String labelIndex;
	private String creator;
	private String createTime;
	private String updator;
	private String updateTime;
	private String fieldRegex;
	private String fieldTip;
	private String isRequestedAssets;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getIsRequested() {
		return isRequested;
	}
	public void setIsRequested(String isRequested) {
		this.isRequested = isRequested;
	}
	public String getIsEdited() {
		return isEdited;
	}
	public void setIsEdited(String isEdited) {
		this.isEdited = isEdited;
	}
	public String getRelationCodeType() {
		return relationCodeType;
	}
	public void setRelationCodeType(String relationCodeType) {
		this.relationCodeType = relationCodeType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getParentGroupCode() {
		return parentGroupCode;
	}
	public void setParentGroupCode(String parentGroupCode) {
		this.parentGroupCode = parentGroupCode;
	}
	public String getCodeIndex() {
		return codeIndex;
	}
	public void setCodeIndex(String codeIndex) {
		this.codeIndex = codeIndex;
	}
	public String getIsHide() {
		return isHide;
	}
	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}
	public String getHideLabelCode() {
		return hideLabelCode;
	}
	public void setHideLabelCode(String hideLabelCode) {
		this.hideLabelCode = hideLabelCode;
	}
	public String getHideLabelName() {
		return hideLabelName;
	}
	public void setHideLabelName(String hideLabelName) {
		this.hideLabelName = hideLabelName;
	}
	public String getLabelIndex() {
		return labelIndex;
	}
	public void setLabelIndex(String labelIndex) {
		this.labelIndex = labelIndex;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFieldRegex() {
		return fieldRegex;
	}
	public void setFieldRegex(String fieldRegex) {
		this.fieldRegex = fieldRegex;
	}
	public String getFieldTip() {
		return fieldTip;
	}
	public void setFieldTip(String fieldTip) {
		this.fieldTip = fieldTip;
	}
	public String getIsRequestedAssets() {
		return isRequestedAssets;
	}
	public void setIsRequestedAssets(String isRequestedAssets) {
		this.isRequestedAssets = isRequestedAssets;
	}
}
