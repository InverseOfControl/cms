package com.ymkj.cms.biz.dao.audit;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;

public interface ILoanAuditDao extends BaseDao<LoanAuditEntity>{
	
	public LoanAuditEntity findByAudit(Map<String,Object> map);
	
	public Integer updaeAudittRulesData(LoanAuditEntity loanAuditEntity); 
}
