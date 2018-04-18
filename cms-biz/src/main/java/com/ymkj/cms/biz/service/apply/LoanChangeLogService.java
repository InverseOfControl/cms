package com.ymkj.cms.biz.service.apply;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.BmsLoanChangeLogEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;

public interface LoanChangeLogService  extends BaseService<BmsLoanChangeLogEntity>{
	
	/**
	 * 
	 * @param loanBaseEntity
	 * @return
	 */
	public Long saveOrUpdate(BmsLoanChangeLogEntity bmsLoanChangeLogEntity);
}
