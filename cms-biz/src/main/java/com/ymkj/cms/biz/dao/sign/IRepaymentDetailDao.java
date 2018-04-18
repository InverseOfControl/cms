package com.ymkj.cms.biz.dao.sign;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;

public interface IRepaymentDetailDao extends BaseDao<BMSRepaymentDetail>{
	
	public int selectConutByLoanBaseId(Long loanId);
	
	public boolean deleteByLoanBaseId(Long loanId);
	

}
