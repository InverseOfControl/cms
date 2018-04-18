package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanNoLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLoanNoLogVo;

public interface ILoanNoExecuter {
	PageResponse<ResLoanNoLogVo> queryLoanNoLogInfo(ReqBMSLoanNoLogVO reqBMSLoanNoLogVO);
}
