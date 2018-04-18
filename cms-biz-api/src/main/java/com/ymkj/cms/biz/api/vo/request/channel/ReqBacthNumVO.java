package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:生成批次号 request vo
 */
public class ReqBacthNumVO extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5065467056593363901L;

	/**
	 * 借款编号集合
	 */
	private List<String> loanNos;

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
	
	private List<Long>loanBaseIds;

	public List<String> getLoanNos() {
		return loanNos;
	}

	public void setLoanNos(List<String> loanNos) {
		this.loanNos = loanNos;
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

	public List<Long> getLoanBaseIds() {
		return loanBaseIds;
	}

	public void setLoanBaseIds(List<Long> loanBaseIds) {
		this.loanBaseIds = loanBaseIds;
	}
	
}
