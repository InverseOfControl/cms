package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class AuditApplyEntryVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AuditEntryVO auditApplyEntryVO;//当前版本
	
	private AuditEntryVO submitAuditApplyEntryVO;//上个环节    提交版本
	
	private AuditEntryVO backAuditApplyEntryVO;//当前环节	回退版本

	public AuditEntryVO getAuditApplyEntryVO() {
		return auditApplyEntryVO;
	}

	public void setAuditApplyEntryVO(AuditEntryVO auditApplyEntryVO) {
		this.auditApplyEntryVO = auditApplyEntryVO;
	}

	public AuditEntryVO getSubmitAuditApplyEntryVO() {
		return submitAuditApplyEntryVO;
	}

	public void setSubmitAuditApplyEntryVO(AuditEntryVO submitAuditApplyEntryVO) {
		this.submitAuditApplyEntryVO = submitAuditApplyEntryVO;
	}

	public AuditEntryVO getBackAuditApplyEntryVO() {
		return backAuditApplyEntryVO;
	}

	public void setBackAuditApplyEntryVO(AuditEntryVO backAuditApplyEntryVO) {
		this.backAuditApplyEntryVO = backAuditApplyEntryVO;
	}
}
