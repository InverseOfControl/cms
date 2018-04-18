package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSSysLog;
import com.ymkj.cms.biz.entity.master.BMSSysLogRecordEntity;

public interface IBMSSysLogService extends BaseService<BMSSysLog>{
	
	
	public Long saveOrUpdate(BMSSysLog sysLog);
	
	/**
	 * <p>Description:记录系统日志</p>
	 * @uthor YM10159
	 * @date 2017年3月24日 下午3:14:20
	 * @param @param BMSSysLogRecordEntity
	 */
	public int recordSysLog(Object reqVO, Object...args);

}
