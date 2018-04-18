package com.ymkj.cms.biz.entity.master;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSContractChangeFrequency  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7497192495878317812L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 客服code
	 */
	private Integer userCode;
	/**
	 * 分配基础值
	 */
	private Integer frequencyBase;
	/**
	 * 被分配实际次数
	 */
	private Integer FrequencyReal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public Integer getFrequencyBase() {
		return frequencyBase;
	}
	public void setFrequencyBase(Integer frequencyBase) {
		this.frequencyBase = frequencyBase;
	}
	public Integer getFrequencyReal() {
		return FrequencyReal;
	}
	public void setFrequencyReal(Integer frequencyReal) {
		FrequencyReal = frequencyReal;
	}
	
	
}
