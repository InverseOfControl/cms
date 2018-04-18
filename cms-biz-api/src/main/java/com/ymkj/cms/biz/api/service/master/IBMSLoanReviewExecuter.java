package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanReviewVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanReviewVO;

public interface IBMSLoanReviewExecuter {

	public PageResponse<ResBMSLoanReviewVO> listPage(ReqBMSLoanReviewVO reqBMSLoanReviewVO);
}
