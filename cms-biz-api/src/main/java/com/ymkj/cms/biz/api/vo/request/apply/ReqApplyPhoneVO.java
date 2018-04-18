package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqApplyPhoneVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanNo;//借款编号
	
	private String cellphone; //手机1
	
	private String cellphoneSec;  //手机2
		
	private String corpPhone; // 单电1
	
	private String corpPhoneSec; // 单电2

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCellphoneSec() {
		return cellphoneSec;
	}

	public void setCellphoneSec(String cellphoneSec) {
		this.cellphoneSec = cellphoneSec;
	}

	public String getCorpPhone() {
		return corpPhone;
	}

	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}

	public String getCorpPhoneSec() {
		return corpPhoneSec;
	}

	public void setCorpPhoneSec(String corpPhoneSec) {
		this.corpPhoneSec = corpPhoneSec;
	}
	
	
}
