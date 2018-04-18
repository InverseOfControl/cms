package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogVO;

public interface IBMSSysLogExecuter {

	public PageResponse<ResBMSSysLogVO> listPage(ReqBMSSysLogVO reqSysLogVO) ;
	
}
