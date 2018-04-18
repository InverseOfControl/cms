package com.ymkj.cms.biz.dao.master.impl;

import java.util.Map;


import org.springframework.stereotype.Repository;


import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanLogDao;
import com.ymkj.cms.biz.entity.master.BMSLoanLog;
@Repository
public class BMSLoanLogDaoImpl extends BaseDaoImpl<BMSLoanLog> implements IBMSLoanLogDao {

	@Override
	public BMSLoanLog findLastLog(Map<String, Object> paramMap) {
		
		return super.getSqlSession().selectOne(super.getStatement("findLastLog"), paramMap);
	}

	@Override
	public BMSLoanLog findLogReasonByCode(BMSLoanLog loanLog) {
	
		return super.getSqlSession().selectOne(super.getStatement("findLogReasonByCode"), loanLog);
	}


}
