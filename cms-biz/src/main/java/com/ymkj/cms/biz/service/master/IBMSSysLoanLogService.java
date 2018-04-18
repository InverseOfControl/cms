package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;

public interface IBMSSysLoanLogService extends BaseService<BMSSysLoanLog>{
	public Long saveOrUpdate(BMSSysLoanLog sysLoanLog);
	
	/**
	 * 查询借款日志分页信息
	 * @param resDemoVO
	 * @return
	 */
	public List<BMSSysLoanLog> listPages(Map<String,Object> map);
	
	/**
	 * 查询第一次终审通过时间
	 */
	
	public BMSSysLoanLog findFirstOldPassTime(String loanNo);
}
