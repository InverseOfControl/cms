package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseRelaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseRelaVO;

public interface IBMSLoanBaseRelaExecuter {
	public PageResponse<ResBMSLoanBaseRelaVO> listPage(ReqBMSLoanBaseRelaVO reqBMSLoanBaseRelaVO);
}
