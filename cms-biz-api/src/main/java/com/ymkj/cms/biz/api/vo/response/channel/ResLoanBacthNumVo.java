package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次号查询 response vo
 */
public class ResLoanBacthNumVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1570791066090421718L;
	
	/**
	 * 批次号
	 */
	private String batchNum;
	
	/**
	 * 生成时间
	 */
	private Date bacthNumCreteTime;

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public Date getBacthNumCreteTime() {
		return bacthNumCreteTime;
	}

	public void setBacthNumCreteTime(Date bacthNumCreteTime) {
		this.bacthNumCreteTime = bacthNumCreteTime;
	}
	
	


}
