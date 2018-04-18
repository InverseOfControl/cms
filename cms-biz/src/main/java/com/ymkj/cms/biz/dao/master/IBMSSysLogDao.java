package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSSysLog;
import com.ymkj.cms.biz.entity.master.BMSSysLogRecordEntity;

public interface IBMSSysLogDao extends BaseDao<BMSSysLog>{
	
	public int recordSysLog(BMSSysLogRecordEntity sysLogRecordEntity);

}
