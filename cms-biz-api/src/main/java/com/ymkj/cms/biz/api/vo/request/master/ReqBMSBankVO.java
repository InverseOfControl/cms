package com.ymkj.cms.biz.api.vo.request.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSBankVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2020567199539074315L;
	
	public ReqBMSBankVO(){
		
	}
	public ReqBMSBankVO(String sysCode){
		super.setSysCode(sysCode);
	}

	private String flag;
	/**
	 * 银行ID
	 */
	private Long Id;
	
	/**
	 * 银行名称
	 */
	private String name;
	
	/**
	 * 银行编码
	 */
	private String code;
	
	/**
	 * 版本号
	 */
	/*private Long version;*/
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	/**
	 * 创建人
	 *//*
	private String creator;
	*//**
	 * 创建人ID
	 *//*
	private Long creatorId;
	*//**
	 * 创建时间
	 *//*
	private Date creatorDate;
	*//**
	 * 修改人
	 *//*
	private String modified;
	*//**
	 * 修改人Id
	 *//*
	private Long modifiedId;
	*//**
	 * 修改时间
	 *//*
	private Date modifiedDate;*/
	
	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
	
	private String channelIds;//渠道id列表
	/**
	 * 根据渠道ID获取银行信息
	 */
	private Long channelId;
	
	private Long isDisabled;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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
	
	/*public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}*/
	public String getName() {
		return name;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	
	/*public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}*/
	
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	/*
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreatorDate() {
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Long getModifiedId() {
		return modifiedId;
	}
	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}*/
	/**
	 * 备注的值(用于导入excel,生成sql)
	 */
	private String remarkExcel;
	
	
	public String getRemarkExcel() {
		return remarkExcel;
	}
	public void setRemarkExcel(String remarkExcel) {
		this.remarkExcel = remarkExcel;
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
	public Long getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}
	public String getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}
	
	
	
	

}
