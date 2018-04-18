package com.ymkj.cms.biz.entity.audit.first;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.common.util.BmsVerifyHelper;
import com.ymkj.cms.biz.common.util.ParametersType;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class AFirstAgreeOrDisagreeEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "000001,借款编号不能为空")
	private String loanNo;//	借款编号	
	@NotNull(message = "000001,复核节点状态不能为空")
	private String checkNodeStatus;//	复核节点状态	
	@NotNull(message = "000001,版本号不能为空")
	private int version;//	版本号
	@NotNull(message = "000001,操作人工号不能为空")
	private String operatorCode;//	操作人工号	
	@NotNull(message = "000001,操作人IP不能为空")
	private String operatorIP;//	操作人IP	
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCheckNodeStatus() {
		return checkNodeStatus;
	}
	public void setCheckNodeStatus(String checkNodeStatus) {
		this.checkNodeStatus = checkNodeStatus;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	
	public void chack(){
//		if (!ValidataUtil.isCheckNodeStatus(this.checkNodeStatus)) {
//			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"checkNodeStatus"});
//		}
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		
		if (this.loanNo.equals("")) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if (this.operatorCode.equals("")) {
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operatorCode"});
		}
		BmsVerifyHelper.verifyContainsKeyName("复核节点状态 checkNodeStatus", this.checkNodeStatus, ParametersType.ReviewAgreeOrDisagreeCheckNodeStatus.class);
	}

}
