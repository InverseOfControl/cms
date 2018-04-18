package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;

import com.ymkj.base.core.biz.api.message.Request;


/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次号查询 request vo
 */
public class ReqLoanBacthNumVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2965321589303542586L;
	
	/**
	 * 渠道ID
	 */
	private String channelId;	
	
	/**
	 * 批次号
	 */
	private String bacthNum;	
	
	/**
	 * 状态
	 */
	private String state;	
	
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
	 * 当前页数
	 */
	private int page;	
	
	/**
	 * 分页条数
	 */
	private int rows;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getBacthNum() {
		return bacthNum;
	}

	public void setBacthNum(String bacthNum) {
		this.bacthNum = bacthNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
