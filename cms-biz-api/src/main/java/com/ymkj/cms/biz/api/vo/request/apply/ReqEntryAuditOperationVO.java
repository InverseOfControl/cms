package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqEntryAuditOperationVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanNo;//	借款编号
	private String firstLevelReason;//	一级原因
	private String firstLevelReasonCode;//	一级原因
	private String twoLevelReason;//	二级原因	
	private String twoLevelReasonCode;//	二级原因	
	private String remark;//	备注
	private String serviceCode;//	操作人工号	
	private String serviceName;//	操作人姓名	String	Y	
	private String ip;//	操作ip
	
	private String version;//版本号
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getFirstLevelReason() {
		return firstLevelReason;
	}
	public void setFirstLevelReason(String firstLevelReason) {
		this.firstLevelReason = firstLevelReason;
	}
	public String getFirstLevelReasonCode() {
		return firstLevelReasonCode;
	}
	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		this.firstLevelReasonCode = firstLevelReasonCode;
	}
	public String getTwoLevelReason() {
		return twoLevelReason;
	}
	public void setTwoLevelReason(String twoLevelReason) {
		this.twoLevelReason = twoLevelReason;
	}
	public String getTwoLevelReasonCode() {
		return twoLevelReasonCode;
	}
	public void setTwoLevelReasonCode(String twoLevelReasonCode) {
		this.twoLevelReasonCode = twoLevelReasonCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
}
