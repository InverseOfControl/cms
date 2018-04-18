package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSContractChannelVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8235642854752129627L;
	
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
	
	
	/**
	 * 导出结果(用于导入excel,生成sql)
	 */
	private String derivedResult;
	/**
	 * 失败原因(用于导入excel,生成sql)
	 */
	private String failureCause;

	public String getDerivedResult() {
		return derivedResult;
	}

	public void setDerivedResult(String derivedResult) {
		this.derivedResult = derivedResult;
	}

	public String getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}
	
	/**
	 * 渠道代码(用于导入excel,生成sql)
	 */
	private String channelCode;
	
	/**
	 * 渠道名称(用于导入excel,生成sql)
	 */
	private String channelName;
	
	/**
	 * 合同模板名称(用于导入excel,生成sql)
	 */
	private String templateName;
	
	/**
	 * 打印类型(用于导入excel,生成sql)
	 */
	private String printType;
	
	/**
	 * 模板文件(用于导入excel,生成sql)
	 */
	private String templateUrl;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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
	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	
	

}
