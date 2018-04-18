package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqUpdateReviewReadStatusVO extends Request{
	
	private static final long serialVersionUID = -1901402909322798832L;

	public ReqUpdateReviewReadStatusVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public ReqUpdateReviewReadStatusVO(){}
	
	/**
	 * 借款编号集合
	 */
	@NotEmpty(message="借款编号集合不能为空")
	private List<String> loanNoList;
	
	/**
	 * 操作人工号
	 */
	@NotEmpty(message="操作人工号不能为空")
	private String serviceCode;
	/**
	 * 操作人姓名
	 */
	@NotEmpty(message="操作人姓名不能为空")
	private String serviceName;
	/**
	 * 操作ip
	 */
	@NotEmpty(message="操作ip不能为空")
	private String ip;

	public List<String> getLoanNoList() {
		return loanNoList;
	}

	public void setLoanNoList(List<String> loanNoList) {
		this.loanNoList = loanNoList;
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
