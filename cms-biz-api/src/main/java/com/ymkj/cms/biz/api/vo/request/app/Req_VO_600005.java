package com.ymkj.cms.biz.api.vo.request.app;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

public class Req_VO_600005 extends Request {
	
	private static final long serialVersionUID = 6078517524180430821L;

	@NotEmpty(message = "身份证号不能为空")
	private String idCardNo;
	
	private String productCd;
	
	@NotEmpty(message = "工号不能为空")
	private String userCode;

	@NotEmpty(message = "申请人姓名不能为空")
	private String name;
	//申请信息
	private Map<String,Object> applyInfoMap;
	
	private String link;//环节
	
	private String isExists;
	
	private String executeType;

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIsExists() {
		return isExists;
	}

	public void setIsExists(String isExists) {
		this.isExists = isExists;
	}

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	public Map<String, Object> getApplyInfoMap() {
		return applyInfoMap;
	}

	public void setApplyInfoMap(Map<String, Object> applyInfoMap) {
		this.applyInfoMap = applyInfoMap;
	}
	
}
