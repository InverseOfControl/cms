package com.ymkj.cms.biz.common.loan;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.exception.BizException;

/**
 * 签约空实现，避免子类代码不必要的实现。签约渠道必须继承此基类
 * 
 * @author YM10138
 *
 */
public class  BaseLoan<T,M> extends Loan<T,M> {



	@Override
	public boolean before(T reqVo, Response<M> res) {
		return true;
	}

	@Override
	public boolean after(T reqVo, Response<M> res) {
		return true;
	}

	
	@Override
	public boolean execute(T reqVo, Response<M> res) {
		return false;
	}

	/**
	 * 异常返回值处理
	 * 
	 * @param biz
	 * @param res
	 * @return
	 */
	protected Response<M> setError(BizException biz, Response<M> res) {
		res.setRepCode(biz.getRealCode());
		res.setRepMsg(biz.getMessage()+JsonUtils.encode(biz.getArguments()));
		return res;
	}




}
