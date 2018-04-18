package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqCreditCheckVO extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean ifTrigger = false;//是否触发规则
	
	private Integer type=1; //当前用户的类型  1 无报告获取为空   2征信白户   3信用不良（包含当前逾期造成信用不良，非当前逾期造成信用不良）
	
	private String reportId; //央行征信报告id
	
	private String msg =  "无"; //返回描述

	public Boolean getIfTrigger() {
		return ifTrigger;
	}

	public void setIfTrigger(Boolean ifTrigger) {
		this.ifTrigger = ifTrigger;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
