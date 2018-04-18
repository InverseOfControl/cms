package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanAuditVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanAuditVO;

public interface IBMSLoanAuditExecuter {
	
	/**
	 * 查询借审批表的信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 */
	public PageResponse<ResBMSLoanAuditVO> listPage(ReqBMSLoanAuditVO reqBMSLoanAuditVO);

}
