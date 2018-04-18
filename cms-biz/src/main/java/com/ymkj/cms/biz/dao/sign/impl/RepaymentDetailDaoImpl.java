package com.ymkj.cms.biz.dao.sign.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.sign.IRepaymentDetailDao;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;

@Repository
public class RepaymentDetailDaoImpl extends BaseDaoImpl<BMSRepaymentDetail> implements IRepaymentDetailDao{

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
}
