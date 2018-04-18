package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSChannelBankVO extends Request {

	private static final long serialVersionUID = 1L;
	private String flag;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 渠道id
	 */
	private Long channelId;
	/**
	 * 银行id
	 */
	private Long bankId;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	/**
	 * 是否启用
	 */
	private Long isDisabled;

	private String ids;

	private int pageNum; // 当前页数
	private int pageSize;

	private int rows;// 行数
	private int page;// 页数
	
	private String channelIds;//渠道id列表
	
	private String bankIds;//银行id列表

	public ReqBMSChannelBankVO() {
	}

	public ReqBMSChannelBankVO(String sysCode) {
		super.setSysCode(sysCode);
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

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
	
	/**
	 * 导出结果(用于导入excel,生成sql)
	 */
	private String derivedResult;
	/**
	 * 失败原因(用于导入excel,生成sql)
	 */
	private String failureCause;
	/**
	 * 渠道代码(用于导入excel,生成sql)
	 */
	private String channelCode;
	/**
	 * 渠道名称(用于导入excel,生成sql)
	 */
	private String channelName;
	/**
	 * 银行名称(用于导入excel,生成sql)
	 */
	private String bankName;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public String getBankIds() {
		return bankIds;
	}

	public void setBankIds(String bankIds) {
		this.bankIds = bankIds;
	}
	
	
}
