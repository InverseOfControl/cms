package com.ymkj.cms.biz.entity.master;

public class BMSContractTemplate extends BMSProductBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8285177659700763418L;
	/**
	 * 模板ID
	 */
	private Long id;
	/**
	 * 模板code
	 */
	private String code;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 打印类型
	 */
	private String printType;
	/**
	 * 模板URL
	 */
	private String templateUrl;
	/**
	 * 模板内容
	 */
	private String templateContent;
	/**
	 * 电子合同是否禁用（0：启用，1禁用）
	 */
	private Long eContractFlag;
	
	/**
	 * 合同类型(0：纸质版，1电子版)
	 */
	private Long contractType;
	/**
	 * 是否启用
	 */
	private Long isDisabled;
	
	
	
	
	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道code
	 */
	private Long channelId;
	/**
	 * 渠道code
	 */
	private String channelCd;
	/**
	 * 渠道模板关系id
	 */
	private Long contractChannelId;
	
	public Long getContractChannelId() {
		return contractChannelId;
	}

	public void setContractChannelId(Long contractChannelId) {
		this.contractChannelId = contractChannelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}



	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

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

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public String getChannelCd() {
		return channelCd;
	}

	public void setChannelCd(String channelCd) {
		this.channelCd = channelCd;
	}

	public Long geteContractFlag() {
		return eContractFlag;
	}

	public void seteContractFlag(Long eContractFlag) {
		this.eContractFlag = eContractFlag;
	}

	public Long getContractType() {
		return contractType;
	}

	public void setContractType(Long contractType) {
		this.contractType = contractType;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}
	

	
	

}
