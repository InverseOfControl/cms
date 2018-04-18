package com.ymkj.cms.biz.api.vo.request.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSSysLogEntityVO extends Request{

	private static final long serialVersionUID = -7581191746104873228L;
	
	private Integer id; //

	 private String systemCode; //所属系统编码

	 private String systemName; //所属系统名称

	
	 private String twoLevelDir; //二级目录code


	 private String optModule; //操作模块


	 private String optName; //操作人姓名

	
	 private Date optTime; //操作时间


	 private String requestUri; //请求地址


	 private String employeeType; //所属岗位

	
	 private String firstLevelDir; //一级目录code

	
	 private String optType; //操作类型


	 private String optCode; //操作人工号


	 private String params; //异常信息

	
	 private String romoteAddr; //ip地址


	 private String memo; //备注

	
	 private Integer version; //默认值是1

	
	 private Integer isDelete; //默认是0,删除是1
	 private Date optTimeStart; //开始时间
	 private Date optTimeEnd; //结束时间


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public Date getOptTime() {
		return optTime;
	}


	public void setOptTime(Date optTime) {
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


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Integer getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	 
	public Date getOptTimeStart() {
		return optTimeStart;
	}


	public void setOptTimeStart(Date optTimeStart) {
		this.optTimeStart = optTimeStart;
	}


	public Date getOptTimeEnd() {
		return optTimeEnd;
	}


	public void setOptTimeEnd(Date optTimeEnd) {
		this.optTimeEnd = optTimeEnd;
	}

	private int pageNum; // 当前页数
	private int pageSize;
	private int rows;// 行数
	private int page;// 页数

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
	
	
	

}
