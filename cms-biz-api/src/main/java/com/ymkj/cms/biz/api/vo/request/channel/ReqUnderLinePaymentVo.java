package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月8日
 * @Description:报盘生成request vo
 */
public class ReqUnderLinePaymentVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2633233620875913210L;

	/**
	 * 批次号
	 */
	private String batchNum;

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

	/**
	 * 合同来源
	 */
	private String fundsSource;

	private List<Map<String, String>> datas;

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
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

	public String getFundsSource() {
		return fundsSource;
	}

	public void setFundsSource(String fundsSource) {
		this.fundsSource = fundsSource;
	}

	public List<Map<String, String>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, String>> datas) {
		this.datas = datas;
	}

}
