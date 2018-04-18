package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月11日
 * @Description:批次更新request vo
 */
public class ReqUpdBatchVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5218910327533593373L;

	/**
	 * 批次号
	 */
	private String bacthNum;

	/**
	 * 初始借款编号
	 */
	private List<String> oldLoanNos;

	/**
	 * 当前借款编号
	 */
	private List<String> newLoanNos;

	/**
	 * 操作客服工号
	 */
	private String serviceCode;

	/**
	 * 操作客服姓名
	 */
	private String serviceName;

	/**
	 * 操作ip
	 */
	private String ip;

	public String getBacthNum() {
		return bacthNum;
	}

	public void setBacthNum(String bacthNum) {
		this.bacthNum = bacthNum;
	}

	public List<String> getOldLoanNos() {
		return oldLoanNos;
	}

	public void setOldLoanNos(List<String> oldLoanNos) {
		this.oldLoanNos = oldLoanNos;
	}

	public List<String> getNewLoanNos() {
		return newLoanNos;
	}

	public void setNewLoanNos(List<String> newLoanNos) {
		this.newLoanNos = newLoanNos;
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
