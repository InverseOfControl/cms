package com.ymkj.cms.biz.api.vo.request.sign;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * 电子签名状态查询
 */
public class ReqSignElectronic  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1949288874982307981L;
	
	private String appNo;

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	

}
