package com.ymkj.cms.biz.api.vo.request.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSTmParameterVO extends Request {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数ID
	 */
	private Long id;

	/**
	 * 属性标识
	 */
	private String code;
	/**
	 * 属性名
	 */
	private String name;
	/**
	 * 属性值
	 */
	private String parameterValue;
	/**
	 * 1:文本框，2:下拉选择框，值为“是/否”
	 */
	private Long inputType;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建用户
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 创建用户ID
	 */
	private Long creatorId;
	/**
	 * 修改用户
	 */
	private String modifier;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;

	/**
	 * 修改用户id
	 */
	private Long modifierId;
	/**
	 * 版本号
	 */
	private Long version;
	/**
	 * 是否删除
	 */
	private Long isDelete;

	private int pageNum; // 当前页数
	private int pageSize;

	private int rows;// 行数
	private int page;// 页数

	private String loanNo;
	private String serviceCode;
	public ReqBMSTmParameterVO() {
	}

	public ReqBMSTmParameterVO(String sysCode) {
		super.setSysCode(sysCode);
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

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Long getInputType() {
		return inputType;
	}

	public void setInputType(Long inputType) {
		this.inputType = inputType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
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

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
}
