package com.ymkj.cms.biz.common.loan;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.ISign;

/**
 * 签约节点处理
 * 
 * @author YM10138
 *
 */
public abstract class Loan<T,M> implements ILoan<T,M> {

	/**
	 * 业务处理前
	 * 
	 * @return
	 */
	public abstract boolean before(T reqVo, Response<M> res);

	/**
	 * 业务处理后
	 * 
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public abstract boolean after(T reqVo, Response<M> res);
	

}
