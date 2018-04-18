package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;

public interface ILoanUrgentConfigService {

	public PageResult<ResBMSLoanUrgentConfigVO> listPage(ReqBMSUrgentLimitListVO reqOrgVO);
	
	public Integer updateOrg(ReqBMSUrgentLimitListVO reqOrgVO);
}
