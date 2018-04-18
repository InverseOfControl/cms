package com.ymkj.cms.biz.entity.app;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSExtendsionAdapaterEntity extends BaseEntity {
	
	private static final long serialVersionUID = 6276630147446738194L;
	
	private String id;
	private String code;
	private String name;
	private String nameCn;
	private String codeType;
	private String codeIndex;
	private String org;
	private String status;
	private String createTime;
	private String creator;
	private String lastModifyTime;
	private String lastModifyPerson;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeIndex() {
		return codeIndex;
	}
	public void setCodeIndex(String codeIndex) {
		this.codeIndex = codeIndex;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getLastModifyPerson() {
		return lastModifyPerson;
	}
	public void setLastModifyPerson(String lastModifyPerson) {
		this.lastModifyPerson = lastModifyPerson;
	}
}
