package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogEntityVO;

public interface IBMSSysLogEntityExecuter {

	public PageResponse<ResBMSSysLogEntityVO> listPage(ReqBMSSysLogEntityVO reqBMSSysLogEntityVO);
}
