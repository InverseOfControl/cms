package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;

public interface IBMSLoanUrgentConfigExecuter {
	/**
	 * 查询加急限制的信息
	 * @param reqBMSLoanBaseVO
	 * @return
	 */
	public PageResponse<ResBMSLoanUrgentConfigVO> listPage(ReqBMSUrgentLimitListVO reqOrgVO);
	
	/**
	 * 编辑加急件
	 */
	public Response<ResBMSLoanUrgentConfigVO> updateOrg(ReqBMSUrgentLimitListVO reqOrgVO);
}
