package com.ymkj.cms.biz.entity.master;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSSysLogRecordEntity extends BaseEntity{

	private static final long serialVersionUID = -1578168006963712716L;

	private String id;
	/**
	 * 所属系统编码
	 */
	private String systemCode;
	/**
	 * 所属系统名称
	 */
	private String systemName;
	/**
	 * 二级目录code
	 */
	private String twoLevelDir;
	/**
	 * 操作模块
	 */
	private String optModule;
	/**
	 * 操作人姓名
	 */
	private String optName;
	/**
	 * 操作时间
	 */
	private String optTime;
	/**
	 * 请求地址
	 */
	private String requestUri;
	/**
	 * 所属岗位
	 */
	private String employeeType;
	/**
	 * 一级目录code
	 */
	private String firstLevelDir;
	/**
	 * 操作类型
	 */
	private String optType;
	/**
	 * 操作人工号
	 */
	private String optCode;
	/**
	 * 异常信息
	 */
	private String params;
	/**
	 * ip地址
	 */
	private String romoteAddr;
	/**
	 * 备注
	 */
	private String memo;
	private String version;
	private String isDelete;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getTwoLevelDir() {
		return twoLevelDir;
	}
	public void setTwoLevelDir(String twoLevelDir) {
		this.twoLevelDir = twoLevelDir;
	}
	public String getOptModule() {
		return optModule;
	}
	public void setOptModule(String optModule) {
		this.optModule = optModule;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getFirstLevelDir() {
		return firstLevelDir;
	}
	public void setFirstLevelDir(String firstLevelDir) {
		this.firstLevelDir = firstLevelDir;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public String getOptCode() {
		return optCode;
	}
	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getRomoteAddr() {
		return romoteAddr;
	}
	public void setRomoteAddr(String romoteAddr) {
		this.romoteAddr = romoteAddr;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
