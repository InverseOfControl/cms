package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年5月8日
 * @Description:划拨申请书签章 req vo
 */
public class ReqdealEsignatureVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8653265370987866861L;
	/**
	 * 文件名称
	 */
	private String fileName;

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
	
	private Map<String, byte[]> fileByteMap;
	
	private String batchNum;
	
	private String fileBatchNum;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getFileBatchNum() {
		return fileBatchNum;
	}

	public void setFileBatchNum(String fileBatchNum) {
		this.fileBatchNum = fileBatchNum;
	}
	
	
	
}
