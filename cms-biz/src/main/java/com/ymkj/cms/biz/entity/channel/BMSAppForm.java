package com.ymkj.cms.biz.entity.channel;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书enity
 */
public class BMSAppForm extends BaseEntity{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 510689373980330056L;
	
	/**
	 * 批次号
	 */
	private String batchNum;
	
	/**
	 * 生成时间
	 */
	private Date createTime;

	/**
	 * 状态
	 */
	private String status;
	
	/**
	 *渠道名 
	 **/
	private String channelName;
	
	/**
	 * 渠道code
	 * */
	private String channelCode;
	
	

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	
	

}
