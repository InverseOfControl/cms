package com.ymkj.cms.biz.api.vo.request.apply;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqQueryReviewMessageCountVO extends Request{
	
	private static final long serialVersionUID = -4868555004867638990L;

	public ReqQueryReviewMessageCountVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public ReqQueryReviewMessageCountVO(){}
	
	/**
	 * 员工编号
	 */
	@NotEmpty(message="用户编号字段不能为空")
	private String userCode;
	
	@NotEmpty(message="messageFlag字段不能为空")
	private String messageFlag;

	public String getMessageFlag() {
		return messageFlag;
	}

	public void setMessageFlag(String messageFlag) {
		this.messageFlag = messageFlag;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
