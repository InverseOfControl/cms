package com.ymkj.cms.biz.api.vo.request.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSLoanChangeLogVO extends Request{

	private static final long serialVersionUID = 4835862847308885268L;
	
	 private Integer id; //

	 private Integer loanBaseId; //

	 private String operationModule; //操作模块Code

	 private String operator; //操作人

	 private String operatorCode; //操作人工号

	 private Date operationStart; //操作时间

	 private String content; //变更内容

	 private Date createdTime; //创建时间

	 private Integer version; //默认值是1

	 private Integer isDelete; //默认是0,删除是1

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoanBaseId() {
		return loanBaseId;
	}

	public void setLoanBaseId(Integer loanBaseId) {
		this.loanBaseId = loanBaseId;
	}

	public String getOperationModule() {
		return operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Date getOperationStart() {
		return operationStart;
	}

	public void setOperationStart(Date operationStart) {
		this.operationStart = operationStart;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
