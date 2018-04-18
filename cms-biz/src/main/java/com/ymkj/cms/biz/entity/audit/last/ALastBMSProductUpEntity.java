package com.ymkj.cms.biz.entity.audit.last;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.common.util.ValidataUtil;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class ALastBMSProductUpEntity {
	private String reqFlag;//请求标识
	
	@NotNull(message = "000001,借款编号不能为空")
	private String loanNo;//借款单号
	
	
	@NotNull(message = "000001,审批额度不能为空")
	private String accLmt;//审批额度
	@NotNull(message = "000001,审批期限不能为空")
	private String accTerm;//审批期限
	@NotNull(message = "000001,审批日期不能为空")
	private String accDate;//审批日期
	
	@NotNull(message = "000001,产品编码不能为空")
	private String productCd;//产品编号
	
	@NotNull(message = "000001,操作人工号不能为空")
	private String operatorCode;//操作人编码
	
	@NotNull(message = "000001,操作人IP不能为空")
	private String operatorIP;//操作人IP
	@NotNull(message = "000001,版本号不能为空")
	private int version;//版本号
	
	private String remark;//备注
	
	@NotNull(message = "000001,核实收入不能为空")
	private String sysCheckLmt; //核实收入

	public String getReqFlag() {
		return reqFlag;
	}

	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getAccLmt() {
		return accLmt;
	}

	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}

	public String getAccTerm() {
		return accTerm;
	}

	public void setAccTerm(String accTerm) {
		this.accTerm = accTerm;
	}

	public String getAccDate() {
		return accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSysCheckLmt() {
		return sysCheckLmt;
	}

	public void setSysCheckLmt(String sysCheckLmt) {
		this.sysCheckLmt = sysCheckLmt;
	}
	
	public void checkValue(){
		if(this.loanNo.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"loanNo"});
		}
		
		if(this.productCd.equals("")){
			throw new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, new Object[]{"productCd"});
		}
		
		if (!ValidataUtil.isIP(this.operatorIP)) {
			throw new BizException(BizErrorCode.APPLY_VALUE_EOERR, new Object[]{"operatorIP"});
		}
		
	}
	
	

}
