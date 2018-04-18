package com.ymkj.cms.biz.api.vo.request.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqRedisManagerVO extends Request {


	/**
	 * 
	 */
	private static final long serialVersionUID = 562201730334067075L;
	/**
	 * 属性名
	 */
	private String name;
	
	
	private String keys;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}


	
	
}
