package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppProvidentInfoVO;

public interface IBMSTmAppProvidentInfoExecuter {

	public PageResponse<ResBMSTmAppProvidentInfoVO> listPage(ReqBMSTmAppProvidentInfoVO reqBMSTmAppProvidentInfoVO);
}
