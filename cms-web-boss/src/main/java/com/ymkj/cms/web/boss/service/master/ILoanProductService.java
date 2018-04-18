package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanProductVO;

public interface ILoanProductService {
	/**
	 * 查询借款信息产品表
	 * @param reqLoanBaseVO
	 * @return
	 */
	public PageResult<ResBMSLoanProductVO> listPage(ReqBMSLoanProductVO reqLoanProductVO);

}
