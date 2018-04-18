package com.ymkj.cms.biz.service.master;

import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;

public interface IBMSLoanLogService extends BaseService<BMSLoanLog>{
	
	/**
	 * 获取最新日志
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月16日下午7:36:48
	 */
	BMSLoanLog findLastLog(Map<String, Object> paramMap);
	
	
	public BMSLoanLog findLogReasonByCode(BMSLoanLog loanLog);

}
