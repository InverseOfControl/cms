package com.ymkj.cms.biz.api.vo.request.app;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600004 extends Request {
	
	private static final long serialVersionUID = 6078517524180430821L;

	@NotEmpty(message = "员工工号不能为空,600004")
	private String userCode;
	
	//是否是征信白户
	@NotEmpty(message = "是否是征信白户不能为空,600004")
	private String isZXBH;
	
	//申请日期
	@NotEmpty(message = "申请日期不能为空,600004")
	private String applyDate;

	//申请信息
	@NotNull(message = "申请信息不能为空,600004")
	private Map<String,Object> applyInfoMap;

	private String ip;
	
	private String operatorName; //操作人姓名
	private String operatorCode; //操作人工号
	
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

}
