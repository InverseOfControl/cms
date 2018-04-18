package com.ymkj.cms.biz.entity.audit.last;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class AXsSystemRejectEntity {
	@NotNull(message = "000001,借款编号不能为空")
	private String loanNo;//借款单号
	
	@NotNull(message = "000001,流程状态不能为空")
	private String rtfStatus;
	
	@NotNull(message = "000001,流程节点状态不能为空")
	private String rtfNodeStatus;
	
	@NotNull(message = "000001,一级原因不能为空")
	private String firstLevelReasons;
	
	@NotNull(message = "000001,一级原因CODE不能为空")
	private String firstLevelReasonCode;
	
	@NotNull(message = "000001,操作人工号不能为空")
	private String operatorCode;//操作人编码
	
	@NotNull(message = "000001,操作人IP不能为空")
	private String operatorIP;//操作人IP
	@NotNull(message = "000001,版本号不能为空")
	private int version;//版本号
	
	

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRtfStatus() {
		return rtfStatus;
	}

	public void setRtfStatus(String rtfStatus) {
		this.rtfStatus = rtfStatus;
	}

	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}

	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}

	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}

	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}



	public String getFirstLevelReasonCode() {
		return firstLevelReasonCode;
	}

	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		this.firstLevelReasonCode = firstLevelReasonCode;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void checkValue(){
		if(this.loanNo.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		
	}
	
	
}
