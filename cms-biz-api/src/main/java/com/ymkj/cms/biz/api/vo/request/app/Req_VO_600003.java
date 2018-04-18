package com.ymkj.cms.biz.api.vo.request.app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600003 extends Request {
	
	private static final long serialVersionUID = 6078517524180430821L;

	@NotEmpty(message = "员工工号不能为空,600003")
	private String userCode;
	
	//是否是征信白户
	@NotEmpty(message = "是否是征信白户不能为空,600003")
	private String isZXBH;
	
	//申请日期
	@NotEmpty(message = "申请日期不能为空,600003")
	private String applyDate;

	//申请信息
	@NotNull(message = "申请信息不能为空,600003")
	private Map<String,Object> applyInfoMap;
	
	@NotNull(message = "申请编号不能为空,600003")
	private String appNo;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getIsZXBH() {
		return isZXBH;
	}

	public void setIsZXBH(String isZXBH) {
		this.isZXBH = isZXBH;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Map<String, Object> getApplyInfoMap() {
		return applyInfoMap;
	}

	public void setApplyInfoMap(Map<String, Object> applyInfoMap) {
		this.applyInfoMap = applyInfoMap;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

}
