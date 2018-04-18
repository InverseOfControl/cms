package com.ymkj.cms.biz.common.sign;

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
public class  BaseSign<T> extends Sign<T> {

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<T> res) {
		return true;
	}

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<T> res) {
		return true;
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<T> res) {
		return true;
	}

	/**
	 * 异常返回值处理
	 * 
	 * @param biz
	 * @param res
	 * @return
	 */
	protected Response<T> setError(BizException biz, Response<T> res) {
		res.setRepCode(biz.getRealCode());
		if(null == biz.getArguments()){
			res.setRepMsg(biz.getMessage());
		}else{
			res.setRepMsg(biz.getMessage()+JsonUtils.encode(biz.getArguments()));
		}
		return res;
	}
}
