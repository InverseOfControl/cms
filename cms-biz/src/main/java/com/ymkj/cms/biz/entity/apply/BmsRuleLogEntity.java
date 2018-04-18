package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;


public class BmsRuleLogEntity extends BaseEntity {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; //ID

	 private String loanNo; //借款编号

	 private String idNo; //身份证id

	 private String name; //姓名

	 private String rtfState; //环节状态

	 private String sdsCode; //规则编码

	 private String sdsName; //规则名称
	 
	 private String sdsRes;			//规则结果
	 
	 private String whetherHit;		//是否命中
	 
	 private String callType;		//调用类型
	 
	 private String businessType;	//业务类型

	 private String operator; //操作人

	 private String operatorCode; //操作人工号

	 private Date operationTime; //操作时间

	 private String remark; //备注

	 private Integer version; //默认值是1

	 private Integer isDelete; //默认是0,删除是1
	 
	 private String incomingData; 	//传入的参数

	 
	public String getIncomingData() {
		return incomingData;
	}

	public void setIncomingData(String incomingData) {
		this.incomingData = incomingData;
	}

	public String getSdsRes() {
		return sdsRes;
	}

	public void setSdsRes(String sdsRes) {
		this.sdsRes = sdsRes;
	}

	public String getWhetherHit() {
		return whetherHit;
	}

	public void setWhetherHit(String whetherHit) {
		this.whetherHit = whetherHit;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}

	public String getLoanNo(){
		return this.loanNo;
	}

	public void setIdNo(String idNo){
		this.idNo = idNo;
	}

	public String getIdNo(){
		return this.idNo;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setRtfState(String rtfState){
		this.rtfState = rtfState;
	}

	public String getRtfState(){
		return this.rtfState;
	}

	public void setSdsCode(String sdsCode){
		this.sdsCode = sdsCode;
	}

	public String getSdsCode(){
		return this.sdsCode;
	}

	public void setSdsName(String sdsName){
		this.sdsName = sdsName;
	}

	public String getSdsName(){
		return this.sdsName;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperator(){
		return this.operator;
	}

	public void setOperatorCode(String operatorCode){
		this.operatorCode = operatorCode;
	}

	public String getOperatorCode(){
		return this.operatorCode;
	}

	public void setOperationTime(Date operationTime){
		this.operationTime = operationTime;
	}

	public Date getOperationTime(){
		return this.operationTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setVersion(Integer version){
		this.version = version;
	}

	public Integer getVersion(){
		return this.version;
	}

	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}

}
