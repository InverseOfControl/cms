package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;

import com.ymkj.base.core.biz.api.message.Request;

public class ResLineNumberUploadExcelResultVO implements Serializable{

	private String bankCode;
	private String bankName;
	private String bankType;
	private String bankNo;
	//返回成功或者失败
	private String returnStatus;
	//失败原因
	private String returnMessage;
	private String sysCode="bms";
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	
	
}
