package com.ymkj.cms.biz.dao.sign;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;


public interface ILoanContractDao extends BaseDao<BMSLoanContract>{
	
	public int selectConutByLoanBaseId(Long loanId);
	
	public boolean deleteByLoanBaseId(Long loanId);
	
	public BMSLoanContract findByLoanNo(String loanNo);
}
