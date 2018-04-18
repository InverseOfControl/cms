package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMProductUpdVo extends Request{

	/**
	 * 序列化对象
	 */
	private static final long serialVersionUID = 8875230681477999L;

	private String reqFlag;//请求标识
	private String loanNo;//借款单号
	private String ifCreditRecode;//有无信用记录
	private String amoutIncome;//收入证明金额
	private String accLmt;//审批额度
	private String accTerm;//审批期限
	private String accDate;//审批日期
	private String productCd;//产品编号
	private String operatorCode;//操作人编码
	private String operatorIP;//操作人IP
	private int version;//版本号
	private String remark;//备注
	private String sysCheckLmt; //核实收入
	
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
	public String getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(String accLmt) {
		this.accLmt = accLmt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(String amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getIfCreditRecode() {
		return ifCreditRecode;
	}
	public void setIfCreditRecode(String ifCreditRecode) {
		this.ifCreditRecode = ifCreditRecode;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSysCheckLmt() {
		return sysCheckLmt;
	}
	public void setSysCheckLmt(String sysCheckLmt) {
		this.sysCheckLmt = sysCheckLmt;
	}

	
}
