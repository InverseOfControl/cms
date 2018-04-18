package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSReassignmentUpdVo extends Request{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8583688477911157316L;

	private String reqFlag;//请求标识 1:CS;2:ZS
	private List<ReqBMSLoansAndVersionsVO> list;//借款单号和锁标识集合
	private String auditPersonCode;//审核人员 备注：对应reqFlag
	private String operatorCode;//操作人code
	private String operatorIP;//操作人IP
	public String getReqFlag() {
		return reqFlag;
	}
	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}
	public String getAuditPersonCode() {
		return auditPersonCode;
	}
	public void setAuditPersonCode(String auditPersonCode) {
		this.auditPersonCode = auditPersonCode;
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
	public List<ReqBMSLoansAndVersionsVO> getList() {
		return list;
	}
	public void setList(List<ReqBMSLoansAndVersionsVO> list) {
		this.list = list;
	}
	
}
