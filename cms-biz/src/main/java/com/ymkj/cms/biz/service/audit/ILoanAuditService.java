package com.ymkj.cms.biz.service.audit;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;

public interface ILoanAuditService extends BaseService<LoanAuditEntity> {
	
	public long insert(LoanAuditEntity ioanAuditEntity);
	
	public long update(LoanAuditEntity ioanAuditEntity);
	
	public LoanAuditEntity findByAudit(PersonHistoryLoanVO personHistoryLoanVO);
	
	public Integer updaeAudittRulesData(LoanAuditEntity loanAuditEntity); 
}
