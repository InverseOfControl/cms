package com.ymkj.cms.biz.dao.task.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.task.IBMSTaskNumberDao;
import com.ymkj.cms.biz.entity.task.BMSTaskNumberQueEntity;

/**
 * 任务数Dao层实现
 * @author YM10166
 *
 */
@Repository
public class BMSTaskNumberDaoImpl extends BaseDaoImpl<BMSTaskNumberQueEntity> implements IBMSTaskNumberDao {

	@Override
	public Integer queryNormalQueCount(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("queryNormalQueCount"), map);
	}

	@Override
	public Integer queryPriorityQueCount(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("queryPriorityQueCount"), map);
	}

	@Override
	public Integer queryPendingQueCount(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("queryPendingQueCount"), map);
	}

}
