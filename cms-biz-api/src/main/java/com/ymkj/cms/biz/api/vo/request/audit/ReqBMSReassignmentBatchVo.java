package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSReassignmentBatchVo  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ReqBMSLoansChackVO> list;//借款单号和锁标识集合
	
	private String auditPersonCode;//审核人员Code
	private String auditPersonName;//审核人员Name
	
	private String operatorCode;//操作人code
	private String operatorName;//操作人Name
	
	private String operatorIP;//操作人IP
	
	
	public String getAuditPersonName() {
		return auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public List<ReqBMSLoansChackVO> getList() {
		return list;
	}

	public void setList(List<ReqBMSLoansChackVO> list) {
		this.list = list;
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
	
	
	
}
