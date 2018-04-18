package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSContactChangeDao;
import com.ymkj.cms.biz.entity.master.BMSContractChange;

@Repository
public class BMSContractChangeDaoImpl extends BaseDaoImpl<BMSContractChange> implements IBMSContactChangeDao {

	@Override
	public BMSContractChange getProductIsDisabled(Map<String,Object> params) {
		return super.getSqlSession().selectOne(super.getStatement("getProductIsDisabled"), params);
	}

	@Override
	public Integer updateLoanBase(Map<String, Object> map) {
		return super.getSqlSession().update(super.getStatement("updateLoanBase"), map);
	}
	
	@Override
	public Integer getLoanBaseVersion(Integer id) {
		return super.getSqlSession().selectOne(super.getStatement("getLoanBaseVersion"), id);
	}

	@Override
	public Integer insertContractChangeFrequency(Map<String, Object> map) {
		return super.getSqlSession().insert(super.getStatement("insertContractChangeFrequency"), map);
	}

	@Override
	public Integer selectContractChangeFrequencyBase(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("selectContractChangeFrequencyBase"), map);
	}

	@Override
	public String getUserCodeFrequencyLow(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("getUserCodeFrequencyLow"),map);
	}

	@Override
	public Integer deleteContractChangeFrequency(Map<String, Object> map) {
		return super.getSqlSession().update(super.getStatement("deleteContractChangeFrequency"), map);

	}

	@Override
	public Integer increaseContractChangeFrequency(Map<String, Object> map) {
		return super.getSqlSession().update(super.getStatement("increaseContractChangeFrequency"), map);

	}
	
}
