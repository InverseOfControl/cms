package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqZsUpdVO extends Request{

	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = -5088895296888745582L;
	private String loanNo;//借款编号
	private String status;//借款状态
	private String loanNoTopClass;//申请件层级
	private String rtfNodeStatus;//流程节点状态
	private String zSPersonCode;//终审核人员code
	private String apppovalPersonCode;//协审员Code
	private String firstLevelReasons;//一级原因
	private String twoLevelReasons;//二级原因
	private String firstLevelReasonCode;//一级原因Code
	private String twoLevelReasonCode;//二级原因Code
	private String operatorCode;//操作人工号
	private String accLmt;//审批额度
	private String accTime;//审批期限
	private String accDate;//审批日期
	private String allotDate;//终审分配时间
	private String blackList;//黑名单Id
	private int version;//版本号
	private String operatorIP;//操作人IP
	private String remark;//备注
	private String ifNewLoanNo;//是否新生件 0：不是；1：是
	private String zSIfNewLoanNo;//zs新生件标识
	
	private String rtfStatus;   //流程环节
	public String getzSIfNewLoanNo() {
		return zSIfNewLoanNo;
	}
	public void setzSIfNewLoanNo(String zSIfNewLoanNo) {
		this.zSIfNewLoanNo = zSIfNewLoanNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoanNoTopClass() {
		return loanNoTopClass;
	}
	public void setLoanNoTopClass(String loanNoTopClass) {
		this.loanNoTopClass = loanNoTopClass;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public String getApppovalPersonCode() {
		return apppovalPersonCode;
	}
	public void setApppovalPersonCode(String apppovalPersonCode) {
		this.apppovalPersonCode = apppovalPersonCode;
	}
	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}
	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}
	public String getTwoLevelReasons() {
		return twoLevelReasons;
	}
	public void setTwoLevelReasons(String twoLevelReasons) {
		this.twoLevelReasons = twoLevelReasons;
	}
	public String getFirstLevelReasonCode() {
		return firstLevelReasonCode;
	}
	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		this.firstLevelReasonCode = firstLevelReasonCode;
	}
	public String getTwoLevelReasonCode() {
		return twoLevelReasonCode;
	}
	public void setTwoLevelReasonCode(String twoLevelReasonCode) {
		this.twoLevelReasonCode = twoLevelReasonCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getAccTime() {
		return accTime;
	}
	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getAllotDate() {
		return allotDate;
	}
	public void setAllotDate(String allotDate) {
		this.allotDate = allotDate;
	}
	public String getBlackList() {
		return blackList;
	}
	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIfNewLoanNo() {
		return ifNewLoanNo;
	}
	public void setIfNewLoanNo(String ifNewLoanNo) {
		this.ifNewLoanNo = ifNewLoanNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getzSPersonCode() {
		return zSPersonCode;
	}
	public void setzSPersonCode(String zSPersonCode) {
		this.zSPersonCode = zSPersonCode;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public String getRtfStatus() {
		return rtfStatus;
	}
	public void setRtfStatus(String rtfStatus) {
		this.rtfStatus = rtfStatus;
	}
	
}
