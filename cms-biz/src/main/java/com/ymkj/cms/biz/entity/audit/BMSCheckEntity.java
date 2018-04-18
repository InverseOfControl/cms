package com.ymkj.cms.biz.entity.audit;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSCheckEntity extends BaseEntity{
	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = -2237206391797645530L;
	private String loanNo;//借款单号
	private String accDate;//审批时间
	private String productName;//产品名称
	private String applyType;//申请件类型
	private String personName;//客户姓名
	private String complexPersonCode;//复核人员code
	private String checkPersonCode;//初审人员code
	private String rtfNodeStatus;//流程节点状态
	private String primaryReason;//一级原因
	private String secodeReason;//二级原因
	private String accLmt;//审批额度
	private String proxyGroup;//代理小组
	private String assistStatus;//辅助流程节点状态页面展示用
	private int version;//申请主表版本号
	private String checkPersonName;
	public String getCheckPersonCode() {
		return checkPersonCode;
	}
	public void setCheckPersonCode(String checkPersonCode) {
		this.checkPersonCode = checkPersonCode;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getAssistStatus() {
		return assistStatus;
	}
	public void setAssistStatus(String assistStatus) {
		this.assistStatus = assistStatus;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public String getPrimaryReason() {
		return primaryReason;
	}
	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
	}
	public String getSecodeReason() {
		return secodeReason;
	}
	public void setSecodeReason(String secodeReason) {
		this.secodeReason = secodeReason;
	}
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProxyGroup() {
		return proxyGroup;
	}
	public void setProxyGroup(String proxyGroup) {
		this.proxyGroup = proxyGroup;
	}
	public String getComplexPersonCode() {
		return complexPersonCode;
	}
	public void setComplexPersonCode(String complexPersonCode) {
		this.complexPersonCode = complexPersonCode;
	}
	public String getCheckPersonName() {
		return checkPersonName;
	}
	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}
	
}
