package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSTmParameterVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数ID
	 */
	private Long id;

	/**
	 * 属性标识
	 */
	private String code;
	/**
	 * 属性名
	 */
	private String name;
	/**
	 * 属性值
	 */
	private String parameterValue;
	/**
	 * 1:文本框，2:下拉选择框，值为“是/否”
	 */
	private Long inputType;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建用户
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 创建用户ID
	 */
	private Long creatorId;
	/**
	 * 修改用户
	 */
	private String modifier;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;

	/**
	 * 修改用户id
	 */
	private Long modifierId;
	/**
	 * 版本号
	 */
	private Long version;
	/**
	 * 是否删除
	 */
	private Long isDelete;
    
	private String totalDebtRadio;
	
	private String internalDebtRadio;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Long getInputType() {
		return inputType;
	}

	public void setInputType(Long inputType) {
		this.inputType = inputType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public String getTotalDebtRadio() {
		return totalDebtRadio;
	}

	public void setTotalDebtRadio(String totalDebtRadio) {
		this.totalDebtRadio = totalDebtRadio;
	}

	public String getInternalDebtRadio() {
		return internalDebtRadio;
	}

	public void setInternalDebtRadio(String internalDebtRadio) {
		this.internalDebtRadio = internalDebtRadio;
	}
	
}
