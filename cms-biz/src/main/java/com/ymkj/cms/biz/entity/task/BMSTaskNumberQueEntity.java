package com.ymkj.cms.biz.entity.task;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 任务数队列信息实体
 * @author YM10166
 *
 */
public class BMSTaskNumberQueEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1876439682057472817L;

	/**
	 * 工号
	 */
	private String personCode;
	
	/**
	 * 正常队列
	 */
	private Integer normalQueCount;
	
	/**
	 * 优先队列
	 */
	private Integer priorityQueCount;
	
	/**
	 * 挂起队列
	 */
	private Integer pendingQueCount;

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Integer getNormalQueCount() {
		return normalQueCount;
	}

	public void setNormalQueCount(Integer normalQueCount) {
		this.normalQueCount = normalQueCount;
	}

	public Integer getPriorityQueCount() {
		return priorityQueCount;
	}

	public void setPriorityQueCount(Integer priorityQueCount) {
		this.priorityQueCount = priorityQueCount;
	}

	public Integer getPendingQueCount() {
		return pendingQueCount;
	}

	public void setPendingQueCount(Integer pendingQueCount) {
		this.pendingQueCount = pendingQueCount;
	}

	@Override
	public String toString() {
		return "BMSTaskNumberQueEntity [personCode=" + personCode + ", normalQueCount=" + normalQueCount
				+ ", priorityQueCount=" + priorityQueCount + ", pendingQueCount=" + pendingQueCount + "]";
	}
	
}
