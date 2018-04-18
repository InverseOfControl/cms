package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqOwningBranchReassignmentVO extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1923222822749905375L;
	private String loanNo;//	借款编号		
	private String code;//  改派人工号
	private String name;//	改派人名称
	private String isThroughTrain;//	是否直通车			 0 不是 1是
	private String branchId;//	改派门店ID
	private String status;//	申请状态		SQLR 录入修改
//											HTQY 合同签约
//											HTQR 合同确认	
	
	private String serviceCode;//	操作人工号	
	private String serviceName;//	操作人姓名
	private String ip;//	操作ip

	private String version;//版本号
	
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsThroughTrain() {
		return isThroughTrain;
	}
	public void setIsThroughTrain(String isThroughTrain) {
		this.isThroughTrain = isThroughTrain;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	
}
