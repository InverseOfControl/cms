package com.ymkj.cms.biz.api.vo.request.apply;

public class RecordVO implements java.io.Serializable {
	/**
	 *   规则编码，规则结果 是否命中 调用类型 业务类型
	 */
	private static final long serialVersionUID = 1L;
	private String loanNo; 			// 借款编号
	private String idNo; 			// 身份证id
	private String name; 			// 姓名
	private String rtfState; 		// 环节状态
	private String sdsCode; 		// 规则编码
	private String sdsName; 		// 规则名称
	private String sdsRes;			//规则结果
	private String whetherHit;		//是否命中
	private String callType;		//调用类型
	private String businessType;	//业务类型
	
	private String incomingData; 	//传入的参数
	private String operator; 		// 操作人
	private String operatorCode; 	// 操作人工号
	private String remark; 			//备注
	
	
	public String getIncomingData() {
		return incomingData;
	}
	public void setIncomingData(String incomingData) {
		this.incomingData = incomingData;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRtfState() {
		return rtfState;
	}
	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}
	public String getSdsCode() {
		return sdsCode;
	}
	public void setSdsCode(String sdsCode) {
		this.sdsCode = sdsCode;
	}
	public String getSdsName() {
		return sdsName;
	}
	public void setSdsName(String sdsName) {
		this.sdsName = sdsName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
