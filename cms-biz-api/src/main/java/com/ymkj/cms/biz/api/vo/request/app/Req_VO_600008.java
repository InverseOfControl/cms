package com.ymkj.cms.biz.api.vo.request.app;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class Req_VO_600008 implements Serializable{
	
	private static final long serialVersionUID = -5981524208792350920L;

	@NotEmpty(message = "渠道不能为空600008")
	private String channelCode;
	
	@NotEmpty(message = "申请产品不能为空600008")
	private String productCd;
	
	@NotEmpty(message = "申请金额不能为空600008")
	private String applyLmt;
	
	@NotEmpty(message = "申请期限不能为空600008")
	private String applyTerm;
	
	@NotEmpty(message = "预计首次还款日不能为空600008")
	private String fristPaymentDate;
	
	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getApplyLmt() {
		return applyLmt;
	}
	
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	
	public String getApplyTerm() {
		return applyTerm;
	}
	
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	
	public String getFristPaymentDate() {
		return fristPaymentDate;
	}
	
	public void setFristPaymentDate(String fristPaymentDate) {
		this.fristPaymentDate = fristPaymentDate;
	}
}

