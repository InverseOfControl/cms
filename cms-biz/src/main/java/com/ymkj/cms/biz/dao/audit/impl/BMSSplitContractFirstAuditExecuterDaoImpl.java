package com.ymkj.cms.biz.dao.audit.impl;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.BMSSplitContractFirstAuditExecuterDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

public class BMSSplitContractFirstAuditExecuterDaoImpl extends BaseDaoImpl<BMSFirstAuditEntity> implements BMSSplitContractFirstAuditExecuterDao{

	@Override
	public List<BMSAutomaticDispatchEntity> automaticDispatchList(Map<String, Object> map) {
		return getSqlSession().selectList(getStatement("queryCsAutomaticDis"), map);
	}

}
