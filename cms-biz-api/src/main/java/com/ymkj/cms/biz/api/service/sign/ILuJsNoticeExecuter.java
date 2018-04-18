package com.ymkj.cms.biz.api.service.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;

public interface ILuJsNoticeExecuter {
	/**
	 * 满标通知
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<RequestVo> bidSuccessNotice(RequestVo requestVo);
	
	/**
	 * 标的放款通知，会将放款审核，放款确认都做掉
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月15日下午5:11:39
	 */
	public Response<RequestVo> targetLoan(RequestVo requestVo);
	
	/**
	 * 流标通知
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午1:38:56
	 */
	public Response<RequestVo> bidFailureNotice(RequestVo requestVo);
}
