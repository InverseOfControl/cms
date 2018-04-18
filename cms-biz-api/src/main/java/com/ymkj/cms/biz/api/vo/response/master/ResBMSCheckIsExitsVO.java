package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;

public class ResBMSCheckIsExitsVO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6778704165232295894L;
	
	private String isFlag;

	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
