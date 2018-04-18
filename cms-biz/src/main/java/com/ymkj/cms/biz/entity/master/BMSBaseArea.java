package com.ymkj.cms.biz.entity.master;

public class BMSBaseArea extends BMSProductBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3826646074452923105L;
	
	/**
	 * 地区ID
	 */
	private Long areaId;
	/**
	 * 地区名称
	 */
	private String name;
	/**
	 * 地区编码
	 */
	private String code;
	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 深度
	 */
	private Long deep;
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getDeep() {
		return deep;
	}
	public void setDeep(Long deep) {
		this.deep = deep;
	}
	
	
	
}
