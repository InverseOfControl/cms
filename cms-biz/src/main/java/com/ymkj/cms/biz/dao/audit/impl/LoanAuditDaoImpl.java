package com.ymkj.cms.biz.dao.audit.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.ILoanAuditDao;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;

@Repository
public class LoanAuditDaoImpl extends BaseDaoImpl<LoanAuditEntity> implements ILoanAuditDao{
	@Override
	public LoanAuditEntity findByAudit(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("findByAudit"),map);
	}

	@Override
	public Integer updaeAudittRulesData(LoanAuditEntity loanAuditEntity) {
		return super.getSqlSession().update(super.getStatement("updaeAudittRulesData"), loanAuditEntity);
	}
}
