package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReasonVO  extends Request {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String queryReasonCode;//原因code
	
	private String firstLevelReason;//一级原因
	private String firstLevelReasonCode;//一级原因
	private String twoLevelReason;//二级原因
	private String twoLevelReasonCode;//二级原因
	private String reasonType; //原因类别 1.常规原因 2.特殊原因
	
	
	public String getQueryReasonCode() {
		return queryReasonCode;
	}
	public void setQueryReasonCode(String queryReasonCode) {
		this.queryReasonCode = queryReasonCode;
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
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	
	
}
