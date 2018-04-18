package com.ymkj.cms.biz.api.vo.request.app;


import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600007 extends Request{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5390215355314537364L;

	@NotEmpty(message = "工号不能为空600006")
	private String userCode;

	public String getUserCode() {
		return userCode;
	}
	@NotEmpty(message = "pageNum不能为空600006")
	private int pageNum;
	
	@NotEmpty(message = "pageSize不能为空600006")
	private int pageSize;
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
