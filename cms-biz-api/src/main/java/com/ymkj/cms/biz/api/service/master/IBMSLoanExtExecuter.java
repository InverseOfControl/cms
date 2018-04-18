package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanExtVO;

public interface IBMSLoanExtExecuter {
	
	/**
	 * 查询借款扩展表的信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 */
	public PageResponse<ResBMSLoanExtVO> listPage(ReqBMSLoanExtVO reqBMSLoanAuditVO);

}
