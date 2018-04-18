package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSLoanChangeLogVO implements Serializable{

	private static final long serialVersionUID = 4814127940221703317L;
	
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
	 
	 
	

}
