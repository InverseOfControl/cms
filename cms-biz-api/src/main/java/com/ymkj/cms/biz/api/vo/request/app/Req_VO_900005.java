package com.ymkj.cms.biz.api.vo.request.app;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_900005 extends Request {
	
	private static final long serialVersionUID = 6078517524180430821L;

	//申请信息
	@NotNull(message = "申请信息不能为空,900005")
	private Map<String,Object> applyInfoMap;
	
	@NotNull(message = "手机号不能为空,900005")
	private String cellphone;
	
	public Map<String, Object> getApplyInfoMap() {
		return applyInfoMap;
	}

	public void setApplyInfoMap(Map<String, Object> applyInfoMap) {
		this.applyInfoMap = applyInfoMap;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
}
