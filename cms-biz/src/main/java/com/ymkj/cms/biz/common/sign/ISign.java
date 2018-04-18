/**
 * 
 */
package com.ymkj.cms.biz.common.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;

/**
 * 签约接口
 * 
 * @author YM10138
 *
 */
public interface ISign<T> {
	/**
	 * 签约业务处理
	 * 
	 * @param reqLoanContractSignVo
	 * @param res
	 * @return
	 */
	boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<T> res);
}
