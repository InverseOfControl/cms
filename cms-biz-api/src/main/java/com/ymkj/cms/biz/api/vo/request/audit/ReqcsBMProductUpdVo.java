package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqcsBMProductUpdVo  extends Request{

	/**
	 * 序列化对象
	 */
	private static final long serialVersionUID = 8875230681477999L;

	private String loanNo;//	借款编号
	private String ifCreditRecode;//	有无信用记录
	private Double amoutInconme;//	收入证明金额
	private Double accLmt;//	审批额度
	private String productCd;//	产品编码
	private Integer accTerm;//	审批期限
	private Date accDate;//	审批日期
	private Long version;//	版本号
	
	private String onlineAWithin3MonthsAddress;//网购达人贷 3个月内收货地址   00001 同地址  00002同司址 00003其他
	private String onlineAWithin6MonthsAddress;//网购达人贷 3个月内收货地址    00001 同地址  00002同司址 00003其他
	private String onlineAWithin12MonthsAddress;//网购达人贷 3个月内收货地址  00001 同地址  00002同司址 00003其他
	
	private String policyCheckIsVerify;//保单贷是否核实
	private String carCheckIsVerify;//车辆贷是否核实
	
	private String operatorCode;//	操作人工号
	private String operatorIP;//	操作人IP
	
	
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIfCreditRecode() {
		return ifCreditRecode;
	}
	public void setIfCreditRecode(String ifCreditRecode) {
		this.ifCreditRecode = ifCreditRecode;
	}
	public Double getAmoutInconme() {
		return amoutInconme;
	}
	public void setAmoutInconme(Double amoutInconme) {
		this.amoutInconme = amoutInconme;
	}
	public Double getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(Double accLmt) {
		this.accLmt = accLmt;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public Integer getAccTerm() {
		return accTerm;
	}
	public void setAccTerm(Integer accTerm) {
		this.accTerm = accTerm;
	}
	public Date getAccDate() {
		return accDate;
	}
	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getOnlineAWithin3MonthsAddress() {
		return onlineAWithin3MonthsAddress;
	}
	public void setOnlineAWithin3MonthsAddress(String onlineAWithin3MonthsAddress) {
		this.onlineAWithin3MonthsAddress = onlineAWithin3MonthsAddress;
	}
	public String getOnlineAWithin6MonthsAddress() {
		return onlineAWithin6MonthsAddress;
	}
	public void setOnlineAWithin6MonthsAddress(String onlineAWithin6MonthsAddress) {
		this.onlineAWithin6MonthsAddress = onlineAWithin6MonthsAddress;
	}
	public String getOnlineAWithin12MonthsAddress() {
		return onlineAWithin12MonthsAddress;
	}
	public void setOnlineAWithin12MonthsAddress(String onlineAWithin12MonthsAddress) {
		this.onlineAWithin12MonthsAddress = onlineAWithin12MonthsAddress;
	}
	public String getPolicyCheckIsVerify() {
		return policyCheckIsVerify;
	}
	public void setPolicyCheckIsVerify(String policyCheckIsVerify) {
		this.policyCheckIsVerify = policyCheckIsVerify;
	}
	public String getCarCheckIsVerify() {
		return carCheckIsVerify;
	}
	public void setCarCheckIsVerify(String carCheckIsVerify) {
		this.carCheckIsVerify = carCheckIsVerify;
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
