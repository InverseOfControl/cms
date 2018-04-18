package com.ymkj.cms.biz.api.vo.response.master;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Response;
/**
 * 
 * @author YM10161
 *
 */
public class ResLoanNoLogVo extends Response<ResLoanNoLogVo>{
	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = -8779449059275558164L;
	private Long id;       
	private Long loanBaseId; 
	private String operationModule; // 操作环节code
	private String operationType; // 操作类型code
	private String operator; // 操作人
	private String operatorCode; // 操作人工号
	private String firstLevleReasons; // 一级原因
	private String twoLevleReasons; // 二级原因
	private String firstLevleReasonsCode;//一级原因code
	private String twoLevleReasonsCode; // 二级原因code
	private Date operationTime; //操作时间
	
	private String remark; //备注
	private String loanNo;//借款编号
	private String appNo;//申请件编号
	private String status;//申请件状态
	private String rtfState;//流程状态
	private String rtfNodeState;//流程节点状态
	private String checkNodeState;//复核节点状态
	private String checkPerson;
	private String checkPersonCode;
	private String finalPerson;
	private String finalPersonCode;
	private String apppovalPersonCode;//协审员Code
	private String apppovalPerson;//协审员名称
	private String complexPerson;//复核人员
	private String complexPersonCode;//复核人员code
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
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
	public String getFirstLevleReasonsCode() {
		return firstLevleReasonsCode;
	}
	public void setFirstLevleReasonsCode(String firstLevleReasonsCode) {
		this.firstLevleReasonsCode = firstLevleReasonsCode;
	}
	public String getTwoLevleReasonsCode() {
		return twoLevleReasonsCode;
	}
	public void setTwoLevleReasonsCode(String twoLevleReasonsCode) {
		this.twoLevleReasonsCode = twoLevleReasonsCode;
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
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getFinalPerson() {
		return finalPerson;
	}
	public void setFinalPerson(String finalPerson) {
		this.finalPerson = finalPerson;
	}
	public String getFinalPersonCode() {
		return finalPersonCode;
	}
	public void setFinalPersonCode(String finalPersonCode) {
		this.finalPersonCode = finalPersonCode;
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
	public String getComplexPerson() {
		return complexPerson;
	}
	public void setComplexPerson(String complexPerson) {
		this.complexPerson = complexPerson;
	}
	public String getComplexPersonCode() {
		return complexPersonCode;
	}
	public void setComplexPersonCode(String complexPersonCode) {
		this.complexPersonCode = complexPersonCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
