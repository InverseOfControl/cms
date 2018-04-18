package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSContractTemplateVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2020567199539074315L;
	
	public ReqBMSContractTemplateVO(){
		
	}
	public ReqBMSContractTemplateVO(String sysCode){
		super.setSysCode(sysCode);
	}
	/**
	 * 渠道ID
	 */
	private Long channelId;
	
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
	 * 模板id
	 */
	private Long id;
	/**
	 * 渠道模板关系id
	 */
	private Long contractChannelId;
	/**
	 * 是否启用
	 */
	private Long isDisabled;
	/**
	 * 合同类型
	 */
	private Long contractType;
	
	
	public Long getContractChannelId() {
		return contractChannelId;
	}
	public void setContractChannelId(Long contractChannelId) {
		this.contractChannelId = contractChannelId;
	}
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
	private int page;     // 当前页数
	private int rows; //每页显示条数 
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 模板内容
	 */
	private String templateContent;
	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public Long getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Long getContractType() {
		return contractType;
	}
	public void setContractType(Long contractType) {
		this.contractType = contractType;
	}
	

}
