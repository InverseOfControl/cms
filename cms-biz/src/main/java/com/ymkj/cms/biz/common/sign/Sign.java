package com.ymkj.cms.biz.common.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;

/**
 * 签约节点处理
 * 
 * @author YM10138
 *
 */
public abstract class Sign<T> implements ISign<T> {

	/**
	 * 业务处理前
	 * 
	 * @return
	 */
	public abstract boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<T> res);

	/**
	 * 业务处理后
	 * 
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public abstract boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<T> res);
}
