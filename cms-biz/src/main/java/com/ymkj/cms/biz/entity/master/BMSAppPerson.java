package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 BMS_APP_PERSON
 */
public class BMSAppPerson extends BaseEntity {

	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 890426503957871012L;

	private Long id;
	
	/*@NotNull(message = "000001, BasicInfoVO.personInfoVO不能为空")*/
	private String name; // 名称
	
	private String perosnNo;
	
	
   //@Pattern(regexp = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$", message = "000003,参数不合法")
	/*@NotNull(message = "000001, BasicInfoVO.personInfoVO不能为空")*/
	private String idNo;
	
	private String idType ; 
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer version ;
	
	
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerosnNo() {
		return perosnNo;
	}

	public void setPerosnNo(String perosnNo) {
		this.perosnNo = perosnNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
