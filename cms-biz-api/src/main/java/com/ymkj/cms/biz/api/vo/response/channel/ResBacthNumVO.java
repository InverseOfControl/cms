package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:生成批次号 response vo
 */
public class ResBacthNumVO  implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4802037266165575701L;
	
	/**
	 * 响应码
	 */
	private String repCode;	
	
	/**
	 * 响应消息
	 */
	private String repMsg;
	
	/**
	 * 返回数据
	 */
	private String data;

	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}

	public String getRepMsg() {
		return repMsg;
	}

	public void setRepMsg(String repMsg) {
		this.repMsg = repMsg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
	
	

}
