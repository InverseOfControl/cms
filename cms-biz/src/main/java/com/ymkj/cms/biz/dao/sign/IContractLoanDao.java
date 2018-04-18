package com.ymkj.cms.biz.dao.sign;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.sign.BMSContractLoan;

public interface IContractLoanDao extends BaseDao<BMSContractLoan>{
	
	public int selectConutByLoanBaseId(Long loanId);
	
	public boolean deleteByLoanBaseId(Long loanId);
}
