package com.ymkj.cms.biz.api.vo.request.sign;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

public class RequestVo extends Request{

	private static final long serialVersionUID = 8863441964675134001L;
	
	public RequestVo(){
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}
	
	private String borrowNo;//借款编号
	private Long loanBaseId;//借款主表id
	private String appNo;//申请编号
	private String papersType;//文件类型
	private String files;//文件名和地址
	private String reason;//终止原因
	private String sign;//签名
	private String serviceCode;//签名
	private String serviceName;//签名
	private String signDate;//签约日期
	
	public String getBorrowNo() {
		return borrowNo;
	}
	public void setBorrowNo(String borrowNo) {
		this.borrowNo = borrowNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPapersType() {
		return papersType;
	}
	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	

}
