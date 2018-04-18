package com.ymkj.cms.biz.api.vo.request.audit;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqAutomaticDispatchVO extends Request{

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = 8484195726955208779L;
	@NotNull(message="flag为必填项不能为空")
	private String flag;//状态	
	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
