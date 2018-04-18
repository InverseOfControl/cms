package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;


public class ReqSignReassignVO extends Request{
	
	private static final long serialVersionUID = 8089942344026525059L;
	
	public ReqSignReassignVO(){
		super.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
	}

}
