package com.ymkj.cms.biz.api.vo.request.channel;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * 龙信小贷导入
 * */
public class ReqImportDocumentVo extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8237579836601331938L;
	

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

	/**
	 * 数据
	 * */
	private List<ReqImportExcelVO> datas;

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

	public List<ReqImportExcelVO> getDatas() {
		return datas;
	}

	public void setDatas(List<ReqImportExcelVO> datas) {
		this.datas = datas;
	}

	
	

}
