package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactHeadVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactHeadVO;

public interface IBMSTmAppContactHeadExecuter {

	public PageResponse<ResBMSTmAppContactHeadVO> listPage(ReqBMSTmAppContactHeadVO reqBMSTmAppContactHeadVO);
}
