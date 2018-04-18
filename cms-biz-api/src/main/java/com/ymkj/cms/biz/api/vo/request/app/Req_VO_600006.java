package com.ymkj.cms.biz.api.vo.request.app;


import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600006 extends Request{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5390215355314537364L;

	@NotEmpty(message = "工号不能为空600006")
	private String userCode;

	private int pageNum=0;
	
	private int pageSize=20;
	
	@NotEmpty(message = "状态不能为空600006")
	private String status;
	
	public String getUserCode() {
		return userCode;
	}
	
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
