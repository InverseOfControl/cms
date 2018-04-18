package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;

public class ResRedisManagerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2270560141069224086L;
	/**
	 * key
	 */
	private String key;
	/**
	 * name
	 */
	private String name;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
