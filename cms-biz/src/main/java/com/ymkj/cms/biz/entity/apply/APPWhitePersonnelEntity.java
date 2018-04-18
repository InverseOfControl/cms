package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 * @author YM10152--CYB
 *
 */
public class APPWhitePersonnelEntity extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	private Integer id;				//id
	private String userCode;		//员工工号
	private Integer validity;		//是否有效	 	0:禁止使用 1:允许使用
	private Integer whiteType;		//白名单类型 	1:客服接单
	private Long creatorId ; 		//创建用户ID
	private String creator ; 		//创建用户
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdTime ; 		//创建时间
	private Long modifierId ; 		//修改用户ID
	private String modifier ; 		//修改用户
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifiedTime ; 	//修改时间
	private Integer isDelete ; 		//版本号
	private Integer verson ;		//是否删除
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public Integer getWhiteType() {
		return whiteType;
	}
	public void setWhiteType(Integer whiteType) {
		this.whiteType = whiteType;
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
	public Integer getVerson() {
		return verson;
	}
	public void setVerson(Integer verson) {
		this.verson = verson;
	}
	
	
	
}
