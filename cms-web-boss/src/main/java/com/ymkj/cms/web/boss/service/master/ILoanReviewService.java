package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanReviewVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanReviewVO;

public interface ILoanReviewService {
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSLoanReviewVO> listPage(ReqBMSLoanReviewVO reqLoanReviewVO);
}
