package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanAuditVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanAuditVO;

public interface ILoanAuditEntityService {
	
	/**
	 * 查询借款审批表
	 * @param reqLoanBaseVO
	 * @return
	 */
	public PageResult<ResBMSLoanAuditVO> listPage(ReqBMSLoanAuditVO reqLoanAuditVO);

}
