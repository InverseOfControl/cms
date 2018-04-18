package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSSysLogVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	private String postCode; //  岗位;
	private String operatorCode; // 操作人工号
	private String operator; // 操作人
	private String firstLevelDir; // 一级目录code
	private String twoLevelDir; // 二级目录code
	private String operationType; //操作类型code
	private Date operationTime; //操作时间
	private String ip; //ip 
	private String remark; //备注
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getFirstLevelDir() {
		return firstLevelDir;
	}
	public void setFirstLevelDir(String firstLevelDir) {
		this.firstLevelDir = firstLevelDir;
	}
	public String getTwoLevelDir() {
		return twoLevelDir;
	}
	public void setTwoLevelDir(String twoLevelDir) {
		this.twoLevelDir = twoLevelDir;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
