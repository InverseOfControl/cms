package com.ymkj.cms.biz.dao.master;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;

public interface IBMSLoanLogDao extends BaseDao<BMSLoanLog>{
	/**
	 * 获取最新日志
	 * @param paramMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月16日下午7:37:34
	 */
	BMSLoanLog findLastLog(Map<String, Object> paramMap);
	
	/**
	 * 查询
	 */
	BMSLoanLog findLogReasonByCode(BMSLoanLog loanLog);

}
