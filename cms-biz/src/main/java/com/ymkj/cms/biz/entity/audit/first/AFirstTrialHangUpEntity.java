package com.ymkj.cms.biz.entity.audit.first;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class AFirstTrialHangUpEntity  extends BaseEntity{

	/**
	 * 初审拒绝
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "000001,借款编号不能为空")
	private String loanNo;//	借款编号
	
	private String rtfNodeStatus;//流程节点状态
	
	private String checkNodeStatus;//复核节点状态
	
	@NotNull(message = "000001,初审人员Code不能为空")
	private String cSPersonCode;//	初审人员ID
	@NotNull(message = "000001,一级原因Code不能为空")
	private String firstLevelReasonCode;//	一级原因Code
	private String twoLevelReasonsCode;//	二级原因Code
	@NotNull(message = "000001,操作人工号不能为空")
	private String operatorCode;//	操作人工号	
	@NotNull(message = "000001,版本号不能为空")
	private int version;//	版本号
	@NotNull(message = "000001,IP不能为空")
	private String operatorIP;//IP
	private String complexPersonCode;//	复核人员
	private String remark;//	备注
	
	
	public String getCheckNodeStatus() {
		return checkNodeStatus;
	}
	public void setCheckNodeStatus(String checkNodeStatus) {
		this.checkNodeStatus = checkNodeStatus;
	}
	public String getRtfNodeStatus() {
		return rtfNodeStatus;
	}
	public void setRtfNodeStatus(String rtfNodeStatus) {
		this.rtfNodeStatus = rtfNodeStatus;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getcSPersonCode() {
		return cSPersonCode;
	}
	public void setcSPersonCode(String cSPersonCode) {
		this.cSPersonCode = cSPersonCode;
	}
	public String getFirstLevelReasonCode() {
		return firstLevelReasonCode;
	}
	public void setFirstLevelReasonCode(String firstLevelReasonCode) {
		this.firstLevelReasonCode = firstLevelReasonCode;
	}
	public String getFirstLevelReasonsCode() {
		return firstLevelReasonCode;
	}
	public void setFirstLevelReasonsCode(String firstLevelReasonCode) {
		this.firstLevelReasonCode = firstLevelReasonCode;
	}
	public String getTwoLevelReasonsCode() {
		return twoLevelReasonsCode;
	}
	public void setTwoLevelReasonsCode(String twoLevelReasonsCode) {
		this.twoLevelReasonsCode = twoLevelReasonsCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public String getComplexPersonCode() {
		return complexPersonCode;
	}
	public void setComplexPersonCode(String complexPersonCode) {
		this.complexPersonCode = complexPersonCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 校验参数
	 */
	public void checkValue(){
		if (!StringUtils.isEmpty(this.checkNodeStatus)) {
			if (!ValidataUtil.isCheckNodeStatus(this.checkNodeStatus)) {
				throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"CheckNodeStatus"});
			}
		}
//		//校验是否信审初审拒绝
//		if (!EnumConstants.RtfNodeState.XSCSREJECT.getValue().equals(this.rtfNodeStatus)) {
//			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"RtfNodeStatus"});
//		}

		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"OperatorIP"});
		}
	}
}
