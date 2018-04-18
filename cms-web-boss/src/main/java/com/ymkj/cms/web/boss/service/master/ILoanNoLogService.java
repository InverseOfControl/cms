package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanNoLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLoanNoLogVo;

public interface ILoanNoLogService {
	PageResponse<ResLoanNoLogVo> queryLoanNoLogInfo(ReqBMSLoanNoLogVO reqBMSLoanNoLogVO);

}
