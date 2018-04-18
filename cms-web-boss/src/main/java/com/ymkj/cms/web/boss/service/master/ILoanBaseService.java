package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.web.boss.common.ResponsePage;

public interface ILoanBaseService {
	/**
	 * 查询申请信息表
	 * @param reqLoanBaseVO
	 * @return
	 */
	public PageResult<ResBMSLoanBaseVO> listPage(ReqBMSLoanBaseVO reqLoanBaseVO);

}
