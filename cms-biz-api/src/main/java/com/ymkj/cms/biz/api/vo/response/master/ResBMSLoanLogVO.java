package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResBMSLoanLogVO implements Serializable{
	
	
	private static final long serialVersionUID = -6401691737339605783L;


	private Integer id; //ID

	
	 private Integer loanBaseId; //借款id

	 
	 private String loanNo; //借款编号

	
	 private String appNo; //

	 
	 private String status; //申请件状态

	
	 private String rtfState; //流程状态

	 
	 private String rtfNodeState; //节点状态

	
	 private String checkNodeState; //借款审核环节节点状态

	
	 private String checkPersonCode; //初审人员Code

	
	 private String checkPerson; //初审人员

	 
	 private String finalPersonCode; //终审人员Code

	 
	 private String finalPerson; //终审人员

	 
	 private String apppovalPersonCode; //协审人员Code

	
	 private String apppovalPerson; //协审人员

	
	 private String complexPersonCode; //复核人员Code

	
	 private String complexPerson; //复核人员

	 
	 private String firstLevleReasonsCode; //一级原因Code

	 
	 private String firstLevleReasons; //一级原因

	
	 private String twoLevleReasonsCode; //二级原因Code

	 
	 private String twoLevleReasons; //二级原因

	 
	 private String operationModule; //操作模块Code

	
	 private String operationType; //操作类型Code

	
	 private String operator; //操作人

	
	 private String operatorCode; //操作人工号

	
	 private Date operationTime; //操作时间

	 
	 private String remark; //备注

	
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


	public String getLoanNo() {
		return loanNo;
	}


	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}


	public String getAppNo() {
		return appNo;
	}


	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRtfState() {
		return rtfState;
	}


	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}


	public String getRtfNodeState() {
		return rtfNodeState;
	}


	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}


	public String getCheckNodeState() {
		return checkNodeState;
	}


	public void setCheckNodeState(String checkNodeState) {
		this.checkNodeState = checkNodeState;
	}


	public String getCheckPersonCode() {
		return checkPersonCode;
	}


	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}


	public String getCheckPerson() {
		return checkPerson;
	}


	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}


	public String getFinalPersonCode() {
		return finalPersonCode;
	}


	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
	}


	public String getFinalPerson() {
		return finalPerson;
	}


	public void setFinalPerson(String finalPerson) {
		this.finalPerson = finalPerson;
	}


	public String getApppovalPersonCode() {
		return apppovalPersonCode;
	}


	public void setApppovalPersonCode(String apppovalPersonCode) {
		this.apppovalPersonCode = apppovalPersonCode;
	}


	public String getApppovalPerson() {
		return apppovalPerson;
	}


	public void setApppovalPerson(String apppovalPerson) {
		this.apppovalPerson = apppovalPerson;
	}


	public String getComplexPersonCode() {
		return complexPersonCode;
	}


	public void setComplexPersonCode(String complexPersonCode) {
		this.complexPersonCode = complexPersonCode;
	}


	public String getComplexPerson() {
		return complexPerson;
	}


	public void setComplexPerson(String complexPerson) {
		this.complexPerson = complexPerson;
	}


	public String getFirstLevleReasonsCode() {
		return firstLevleReasonsCode;
	}


	public void setFirstLevleReasonsCode(String firstLevleReasonsCode) {
		this.firstLevleReasonsCode = firstLevleReasonsCode;
	}


	public String getFirstLevleReasons() {
		return firstLevleReasons;
	}


	public void setFirstLevleReasons(String firstLevleReasons) {
		this.firstLevleReasons = firstLevleReasons;
	}


	public String getTwoLevleReasonsCode() {
		return twoLevleReasonsCode;
	}


	public void setTwoLevleReasonsCode(String twoLevleReasonsCode) {
		this.twoLevleReasonsCode = twoLevleReasonsCode;
	}


	public String getTwoLevleReasons() {
		return twoLevleReasons;
	}


	public void setTwoLevleReasons(String twoLevleReasons) {
		this.twoLevleReasons = twoLevleReasons;
	}


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


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
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
