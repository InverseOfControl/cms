package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanProductVO;

public interface IBMSLoanProductExecuter {
	/**
	 * 查询借款信息产品表的信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 */
	public PageResponse<ResBMSLoanProductVO> listPage(ReqBMSLoanProductVO reqBMSLoanProductVO);

}
