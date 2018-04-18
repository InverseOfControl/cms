package com.ymkj.cms.biz.api.vo.request.job;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;

public class ReqLoanJobVO extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9055740487691418755L;
	
	private String appInputFlag;//app进件标志
	private String appInputFlagFalse;//非app进件标志
	private String testLoanNo;//为测试预留
	private List<ResBMSLoanBaseVO> disposeList;//需要处理的数据
	
	
	public ReqLoanJobVO(){
		super.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
	}


	public String getAppInputFlag() {
		return appInputFlag;
	}


	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}


	public String getAppInputFlagFalse() {
		return appInputFlagFalse;
	}


	public void setAppInputFlagFalse(String appInputFlagFalse) {
		this.appInputFlagFalse = appInputFlagFalse;
	}


	public List<ResBMSLoanBaseVO> getDisposeList() {
		return disposeList;
	}


	public void setDisposeList(List<ResBMSLoanBaseVO> disposeList) {
		this.disposeList = disposeList;
	}


	public String getTestLoanNo() {
		return testLoanNo;
	}


	public void setTestLoanNo(String testLoanNo) {
		this.testLoanNo = testLoanNo;
	}

}
