package com.ymkj.cms.biz.dao.sign.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.sign.ILoanContractDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;

@Repository
public class LoanContractDaoImpl extends BaseDaoImpl<BMSLoanContract> implements ILoanContractDao{

	@Override
	public int selectConutByLoanBaseId(Long loanId) {
		
		return super.getSqlSession().selectOne(super.getStatement("selectConutByLoanBaseId"),loanId);
	}

	@Override
	public boolean deleteByLoanBaseId(Long loanId) {
		boolean dflag=false;
		int conut= super.getSqlSession().update(super.getStatement("deleteByLoanBaseId"), loanId);
		
		if(conut > 0){
			dflag=true;
		}
		return dflag;
	}

	@Override
	public BMSLoanContract findByLoanNo(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("findByLoanNo"), loanNo);
	}

}
