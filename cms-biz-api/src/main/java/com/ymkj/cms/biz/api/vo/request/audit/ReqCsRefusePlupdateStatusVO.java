package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqCsRefusePlupdateStatusVO  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ReqCsRefuseUpdStatusVO> list;
	private String operatorCode;//操作人编码
	private String operatorName;//操作人名称
	private String operatorIP;//操作人IP地址
	private String firstLevelReasons;//一级原因文本
	private String twoLevelReasons;//二级原因文本
	private String FirstLevelReasonCode;//一级原因Code
	private String TwoLevelReasonCode;//二级原因Code
	private String remark;//备注
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
	public List<ReqCsRefuseUpdStatusVO> getList() {
		return list;
	}
	public void setList(List<ReqCsRefuseUpdStatusVO> list) {
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
