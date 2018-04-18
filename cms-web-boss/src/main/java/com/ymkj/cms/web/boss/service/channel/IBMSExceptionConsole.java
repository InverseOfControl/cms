package com.ymkj.cms.web.boss.service.channel;

import org.apache.log4j.Logger;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.web.boss.exception.BusinessException;

/**
 * @author YM10189
 * 异常信息输出
 */
public interface IBMSExceptionConsole {
	
	void printException(Exception e,Response<?> response,Logger logger,String excMsg);
	
	void printBusinessException(BusinessException e,Response<?> response,Logger logger,String excMsg);

}
