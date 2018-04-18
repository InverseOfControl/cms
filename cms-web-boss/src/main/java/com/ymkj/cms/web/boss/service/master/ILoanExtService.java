package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanExtVO;

public interface ILoanExtService {
	
	/**
	 * 查询借款扩展表
	 * @param reqLoanBaseVO
	 * @return
	 */
	public PageResult<ResBMSLoanExtVO> listPage(ReqBMSLoanExtVO reqLoanExtVO);

}
