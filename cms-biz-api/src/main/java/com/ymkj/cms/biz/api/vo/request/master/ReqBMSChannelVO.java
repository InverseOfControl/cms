package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSChannelVO extends Request {

	private static final long serialVersionUID = -3665170324690759297L;

	private int id;

	private String channelId;

	private String name;

	private String code;

	private String calculateUrl;

	private String startSalesTime;

	private String stopSalesTime;

	private String ids;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	/**
	 * 是否启用
	 */
	private Long isDisabled;
	/**
	 * 电子合同是否启用
	 */
	private Long econtractFlag;
	/**
	 * 纸质合同是否启用
	 */
	private Long pcontractFlag;

	private int pageNum; // 当前页数
	private int pageSize;

	private int rows;// 行数
	private int page;// 页数
	/**
	 * 用户code
	 */
	private String userCode;
	/**
	 * 门店ID
	 */
	private Long orgId;
	
	/**
	 * 是否可优惠
	 */
	private Long isCanPreferential;
	
	private String orgIds;
	
	
	
	public Long getIsCanPreferential() {
		return isCanPreferential;
	}

	public void setIsCanPreferential(Long isCanPreferential) {
		this.isCanPreferential = isCanPreferential;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}

	public ReqBMSChannelVO() {

	}

	public ReqBMSChannelVO(String sysCode) {
		super.setSysCode(sysCode);
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

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public String getCalculateUrl() {
		return calculateUrl;
	}

	public void setCalculateUrl(String calculateUrl) {
		this.calculateUrl = calculateUrl;
	}

	public String getStartSalesTime() {
		return startSalesTime;
	}

	public void setStartSalesTime(String startSalesTime) {
		this.startSalesTime = startSalesTime;
	}

	public String getStopSalesTime() {
		return stopSalesTime;
	}

	public void setStopSalesTime(String stopSalesTime) {
		this.stopSalesTime = stopSalesTime;
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
	 * 错误的起售时间
	 */
	private String mistakeStartSalesTime;
	
	/**
	 * 错误的停售时间
	 */
	private String mistakeStopSalesTime;



	public String getMistakeStartSalesTime() {
		return mistakeStartSalesTime;
	}

	public void setMistakeStartSalesTime(String mistakeStartSalesTime) {
		this.mistakeStartSalesTime = mistakeStartSalesTime;
	}

	public String getMistakeStopSalesTime() {
		return mistakeStopSalesTime;
	}

	public void setMistakeStopSalesTime(String mistakeStopSalesTime) {
		this.mistakeStopSalesTime = mistakeStopSalesTime;
	}

	public Long getEcontractFlag() {
		return econtractFlag;
	}

	public void setEcontractFlag(Long econtractFlag) {
		this.econtractFlag = econtractFlag;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Long getPcontractFlag() {
		return pcontractFlag;
	}

	public void setPcontractFlag(Long pcontractFlag) {
		this.pcontractFlag = pcontractFlag;
	}

	

	
	
	

}
