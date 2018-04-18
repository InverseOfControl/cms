package com.ymkj.cms.biz.entity.channel;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSBhxtCitOrg extends BaseEntity{

	private Long id;
	
	private Long orgId;
	
	private String code;
	
	private String name;
	
	private Date createTime;
	
	private String creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	
}
