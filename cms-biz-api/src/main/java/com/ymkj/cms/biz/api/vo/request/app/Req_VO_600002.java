package com.ymkj.cms.biz.api.vo.request.app;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600002 extends Request {
	
	private static final long serialVersionUID = -6634133355249488498L;
	
	@NotEmpty(message = "工号不能为空600002")
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
