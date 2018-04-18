package com.ymkj.cms.biz.api.vo.request.audit;


import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqCsPlupdateStatusVo extends Request{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6997425015791338879L;
	
	private List<ReqCsUpdStatusVo> list;
	private String operatorCode;//操作人编码
	private String operatorIP;//操作人IP地址
	private String firstLevelReasons;
	private String twoLevelReasons;
	private String FirstLevelReasonCode;
	private String TwoLevelReasonCode;
	private String operatorFlag;//操作流程节点状态标识
	private String remark;//备注
	
	public String getOperatorFlag() {
		return operatorFlag;
	}
	public void setOperatorFlag(String operatorFlag) {
		this.operatorFlag = operatorFlag;
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
		return FirstLevelReasonCode;
	}
	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		FirstLevelReasonCode = firstLevelReasonCode;
	}
	public String getTwoLevelReasonCode() {
		return TwoLevelReasonCode;
	}
	public void setTwoLevelReasonCode(String twoLevelReasonCode) {
		TwoLevelReasonCode = twoLevelReasonCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ReqCsUpdStatusVo> getList() {
		return list;
	}
	public void setList(List<ReqCsUpdStatusVo> list) {
		this.list = list;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
