package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanChangeLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogVO;

public interface ILoanChangeLogService {
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSLoanChangeLogVO> listPage(ReqBMSLoanChangeLogVO reqLoanChangeLogVO);
}
