package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSQueryLoanLogVO implements Serializable{

	private static final long serialVersionUID = -29188518778780182L;
	
	private String operationModule;
	private String operationType;
	private String operator;
	private Date operationTime;
	private String remark;
	private String firstLevleReasons;
	private String twoLevleReasons;
	
	private String operatorCode;
	public String getOperationModule() {
		return operationModule;
	}
	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFirstLevleReasons() {
		return firstLevleReasons;
	}
	public void setFirstLevleReasons(String firstLevleReasons) {
		this.firstLevleReasons = firstLevleReasons;
	}
	public String getTwoLevleReasons() {
		return twoLevleReasons;
	}
	public void setTwoLevleReasons(String twoLevleReasons) {
		this.twoLevleReasons = twoLevleReasons;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

}
