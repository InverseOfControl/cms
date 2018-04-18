package com.ymkj.cms.biz.entity.audit.first;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class ApplicationPartEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "000001,请求标识不能为空")
	private Long flag;//请求标识 1.终审通过件拒绝  2,通过件修改数据 3 拒绝件修改拒绝原因
	@NotNull(message = "000001,请求标识不能为空")
	private String loanNo;//借款单号
	
	private Long accLmt;//审批金额
	private Long accTerm;//审批期限
	
	private String firstLevelReasons;//一级原因
	private String firstLevelReasonsCode;//一级原因code
	private String twoLevelReasons;//二级原因
	private String twoLevelReasonsCode;//二级原因code
	
	@NotNull(message = "000001,操作人code不能为空")
	private String operatorCode;//操作人code
	
	@NotNull(message = "000001,操作人Nmae不能为空")
	private String operatorName;//操作人Nmae
	
	@NotNull(message = "000001,操作人IP不能为空")
	private String operatorIP;//操作人IP
	@NotNull(message = "000001,版本号不能为空")
	private Long version;//版本号
	private String remark;//备注
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Long getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(Long accLmt) {
		this.accLmt = accLmt;
	}
	public Long getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(Long accTerm) {
		this.accTerm = accTerm;
	}
	public String getFirstLevelReasons() {
		return firstLevelReasons;
	}
	public void setFirstLevelReasons(String firstLevelReasons) {
		this.firstLevelReasons = firstLevelReasons;
	}
	public String getFirstLevelReasonsCode() {
		return firstLevelReasonsCode;
	}
	public void setFirstLevelReasonsCode(String firstLevelReasonsCode) {
		this.firstLevelReasonsCode = firstLevelReasonsCode;
	}
	public String getTwoLevelReasons() {
		return twoLevelReasons;
	}
	public void setTwoLevelReasons(String twoLevelReasons) {
		this.twoLevelReasons = twoLevelReasons;
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
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void chack(){
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		if(this.operatorCode.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"operatorCode"});
		}
		if(this.flag != null && (this.flag == 1|| this.flag == 3)){
			if(StringUtils.isEmpty(this.firstLevelReasons)){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"firstLevelReasons"});
			}
			if(StringUtils.isEmpty(this.firstLevelReasonsCode)){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"firstLevelReasonsCode"});
			}
		}else if(this.flag != null && this.flag == 2){
			if(this.accLmt == null || this.accLmt == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"accLmt"});
			}
			if(this.accTerm == null || this.accTerm == 0){
				throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"accTerm"});
			}
			
		}else{
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"flag"} );
		}
	}
}
