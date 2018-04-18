package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanUrgentConfigDao;
import com.ymkj.cms.biz.entity.master.BMSLoanUrgentConfig;

@Repository
public class BMSLoanUrgentConfigDaoImpl extends BaseDaoImpl<BMSLoanUrgentConfig> implements IBMSLoanUrgentConfigDao{

	@Override
	public List<BMSLoanUrgentConfig> selectAllBmsLoanUrgentConfigs(Map<String, Object> map) {
		return super.getSqlSession().selectList(super.getStatement("selectAllBmsLoanUrgentConfigs"), map);
	}

	@Override
	public Integer getLongBaseCountById(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("getLongBaseCountById"), map);
	}

}
