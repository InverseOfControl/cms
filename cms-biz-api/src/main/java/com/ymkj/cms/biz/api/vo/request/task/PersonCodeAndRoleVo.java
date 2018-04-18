package com.ymkj.cms.biz.api.vo.request.task;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 工号、审核节点（角色）类
 * @author YM10166
 *
 */
public class PersonCodeAndRoleVo extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3931978400890234915L;

	/**
	 * 审核节点（角色：1：初审；2：终审）
	 */
	private String personRole;
	
	/**
	 * 工号（用户编码）
	 */
	private String personCode;
	
	/**
	 * 用户名称
	 */
	private String personName;
	
	/**
	 * 大组
	 */
	private String partenOrg;
	
	/**
	 * 小组
	 */
	private String org;
	
	/**
	 * 正常队列
	 */
	private Integer normalQueue;
	
	/**
	 * 优先队列
	 */
	private Integer priorityQueue;
	
	/**
	 * 挂起队列
	 */
	private Integer hangQueue;
	
	/**
	 * 是否接单
	 */
	private String isaccept;
	
	/**
	 * 大组code
	 */
	private String partenOrgCode;
	
	/**
	 * 小组code
	 */
	private String orgCode;

	public String getPersonRole() {
		return personRole;
	}

	public void setPersonRole(String personRole) {
		this.personRole = personRole;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPartenOrg() {
		return partenOrg;
	}

	public void setPartenOrg(String partenOrg) {
		this.partenOrg = partenOrg;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Integer getNormalQueue() {
		return normalQueue;
	}

	public void setNormalQueue(Integer normalQueue) {
		this.normalQueue = normalQueue;
	}

	public Integer getPriorityQueue() {
		return priorityQueue;
	}

	public void setPriorityQueue(Integer priorityQueue) {
		this.priorityQueue = priorityQueue;
	}

	public Integer getHangQueue() {
		return hangQueue;
	}

	public void setHangQueue(Integer hangQueue) {
		this.hangQueue = hangQueue;
	}

	public String getIsaccept() {
		return isaccept;
	}

	public void setIsaccept(String isaccept) {
		this.isaccept = isaccept;
	}

	public String getPartenOrgCode() {
		return partenOrgCode;
	}

	public void setPartenOrgCode(String partenOrgCode) {
		this.partenOrgCode = partenOrgCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
