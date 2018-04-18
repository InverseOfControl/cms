package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppEstateInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppEstateInfoVO;

public interface IBMSTmAppEstateInfoExecuter {

	public PageResponse<ResBMSTmAppEstateInfoVO> listPage(ReqBMSTmAppEstateInfoVO reqBMSTmAppEstateInfoVO);
}
