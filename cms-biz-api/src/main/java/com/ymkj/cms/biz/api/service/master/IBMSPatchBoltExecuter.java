package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSPatchBoltVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;

/**
 * 补件管理暴露的服务
 * @author YM10161
 *
 */
public interface IBMSPatchBoltExecuter {
	public 	PageResponse<ResQueryLoanVo> listPage(ReqBMSPatchBoltVO reqBMSPatchBoltVO);
}
