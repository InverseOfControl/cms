package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月9日
 * @Description:导出划拨申请 request vo
 */
public class ReqBatchNumVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7431599162082177474L;

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
	
	private Map<String,byte[]>fileByteMap;

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

	public Map<String, byte[]> getFileByteMap() {
		return fileByteMap;
	}

	public void setFileByteMap(Map<String, byte[]> fileByteMap) {
		this.fileByteMap = fileByteMap;
	}
	
	

}
