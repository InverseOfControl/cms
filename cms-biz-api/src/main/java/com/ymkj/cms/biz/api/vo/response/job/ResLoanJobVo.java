package com.ymkj.cms.biz.api.vo.response.job;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

public class ResLoanJobVo extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 436694719553440999L;
	
	public ResLoanJobVo(){
		super.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
	}
}
