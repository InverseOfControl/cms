package com.ymkj.cms.biz.service.finance;


import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.finance.BMSLoanBank;


public interface ILoanBankService extends BaseService<BMSLoanBank> {
	
	public BMSLoanBank saveOrUpdate(BMSLoanBank loanBank);
}
