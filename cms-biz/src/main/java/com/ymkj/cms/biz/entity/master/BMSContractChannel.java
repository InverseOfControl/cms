package com.ymkj.cms.biz.entity.master;

public class BMSContractChannel extends BMSProductBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8285177659700763418L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 渠道ID
	 */
	private Long channelId;
	/**
	 * 模板ID
	 */
	private Long templateId;
	/**
	 * 是否可用
	 */
	private Long isDisabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}
	


}
