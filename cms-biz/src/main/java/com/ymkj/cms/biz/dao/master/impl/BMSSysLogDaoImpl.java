package com.ymkj.cms.biz.dao.master.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSSysLogDao;
import com.ymkj.cms.biz.entity.master.BMSSysLog;
import com.ymkj.cms.biz.entity.master.BMSSysLogRecordEntity;

@Repository
public class BMSSysLogDaoImpl extends BaseDaoImpl<BMSSysLog> implements IBMSSysLogDao{

	@Override
	public int recordSysLog(BMSSysLogRecordEntity sysLogRecordEntity) {
		return getSessionTemplate().insert("recordSysLog",sysLogRecordEntity);
	}

}
