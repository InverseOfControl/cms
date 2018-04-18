package com.ymkj.cms.biz.service.master.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanLogDao;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;
import com.ymkj.cms.biz.service.master.IBMSLoanLogService;
@Service
public class BMSLoanLogServiceImpl extends BaseServiceImpl<BMSLoanLog> implements IBMSLoanLogService {
	@Autowired
	private IBMSLoanLogDao bmsLoanLogDao;
	@Override
	protected BaseDao<BMSLoanLog> getDao() {
		return bmsLoanLogDao;
	}
	@Override
	public BMSLoanLog findLastLog(Map<String, Object> paramMap) {
		return bmsLoanLogDao.findLastLog(paramMap);
	}
	@Override
	public BMSLoanLog findLogReasonByCode(BMSLoanLog loanLog) {
		return bmsLoanLogDao.findLogReasonByCode(loanLog);
	}

}
