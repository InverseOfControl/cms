package com.ymkj.cms.biz.service.task.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.task.IBMSTaskNumberDao;
import com.ymkj.cms.biz.entity.task.BMSTaskNumberQueEntity;
import com.ymkj.cms.biz.service.task.IBMSTaskNumberService;

/**
 * 任务数接口Service层实现
 * @author YM10166
 *
 */
@Service
public class BMSTaskNumberServiceImpl extends BaseServiceImpl<BMSTaskNumberQueEntity> implements IBMSTaskNumberService {
	
	@Autowired
	private IBMSTaskNumberDao bmsTaskNumberDao;

	@Override
	public Integer queryNormalQueCount(Map<String, Object> map) {
		return bmsTaskNumberDao.queryNormalQueCount(map);
	}
	
	@Override
	public Integer queryPriorityQueCount(Map<String, Object> map) {
		return bmsTaskNumberDao.queryPriorityQueCount(map);
	}

	@Override
	public Integer queryPendingQueCount(Map<String, Object> map) {
		return bmsTaskNumberDao.queryPendingQueCount(map);
	}

	@Override
	protected BaseDao<BMSTaskNumberQueEntity> getDao() {
		return bmsTaskNumberDao;
	}

}
