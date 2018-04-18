package com.ymkj.cms.biz.dao.audit.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.ISignReassignmentDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;

@Repository
public class SignReassignmentDaoImpl extends BaseDaoImpl<LoanBaseEntity> implements ISignReassignmentDao{

	@Override
	public List<LoanBaseEntity> getSignReassignList() {
		return getSqlSession().selectList(getStatement("getSignReassignList"));
	}

	@Override
	public List<Map<String,Object>> getSignCodeListByOrgId(String orgId) {
		return getSqlSession().selectList(getStatement("getSignCodeListByOrgId"),orgId);
	}

	@Override
	public int signReassign(LoanBaseEntity loanBaseEntity) {
		return getSqlSession().update(getStatement("signReassign"),loanBaseEntity);
	}

	@Override
	public List<Map<String, Object>> getWhitePerson() {
		return getSqlSession().selectList(getStatement("getWhitePerson"));
	}

	@Override
	public List<Map<String, Object>> getSignCodeListBy(Map<String, Object> paramMap) {
		return getSqlSession().selectList(getStatement("getSignCodeListBy"),paramMap);
	}

}
