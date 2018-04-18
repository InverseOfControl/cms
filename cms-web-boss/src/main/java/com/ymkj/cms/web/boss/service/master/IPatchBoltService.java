package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSPatchBoltVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSPatchBoltVO;

public interface IPatchBoltService {
	public PageResponse<ResQueryLoanVo> listPage(ReqBMSPatchBoltVO reqBMSPatchBoltVO);
}
