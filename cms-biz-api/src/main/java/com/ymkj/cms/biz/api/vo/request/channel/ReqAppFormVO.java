package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;

import com.ymkj.base.core.biz.api.message.Request;


/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书reqVO
 */
public class ReqAppFormVO extends Request implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8531104973212627048L;

	/**
	 * 批次号
	 */
	private String batchNum;
	
	/**
	 * 渠道
	 */
	private String channelId;

	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 当前页数
	 */
	private int page;
	
	/**
	 * 每页显示条数 
	 */
	private int rows;
	

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
