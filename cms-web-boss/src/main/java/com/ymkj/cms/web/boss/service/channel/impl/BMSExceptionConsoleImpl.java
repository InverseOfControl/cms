package com.ymkj.cms.web.boss.service.channel.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.cms.web.boss.service.channel.IBMSExceptionConsole;

@Service
public class BMSExceptionConsoleImpl implements IBMSExceptionConsole {

	@Override
	public void printException(Exception e, Response<?> response,Logger logger,String excMsg) {
		if(logger!=null){
			logger.error(excMsg, e);
		}
		response.setRepCode(CoreErrorCode.SYSTEM_ERROR.getErrorCode());
		response.setRepMsg(CoreErrorCode.SYSTEM_ERROR.getDefaultMessage());

	}

	@Override
	public void printBusinessException(BusinessException e, Response<?> response,Logger logger,String excMsg) {
		if(logger!=null){
			logger.error(excMsg, e);
		}
		response.setRepCode(e.getRealCode());
		response.setRepMsg(e.getErrorMsg());
	}

}
