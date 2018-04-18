package com.ymkj.cms.biz.common.loan;

import com.ymkj.base.core.biz.api.message.Response;


public interface ILoan<T,M> {

	/**
	 * 签约业务处理
	 * 
	 * @param reqLoanContractSignVo
	 * @param res
	 * @return
	 */
	boolean execute(T reqVo, Response<M> res);
	

	
}
