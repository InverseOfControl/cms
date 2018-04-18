package com.ymkj.cms.biz.dao.audit.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.BMSAPPHistoryDao;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;

@Repository
public class BMSAPPHistoryDaoImpl extends BaseDaoImpl<BMSAPPHistoryEntity> implements BMSAPPHistoryDao{

	@Override
	public List<BMSAPPHistoryEntity> findTmAppHistoryByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getStatement("findTmAppHistoryByParams"), params);
	}

}
