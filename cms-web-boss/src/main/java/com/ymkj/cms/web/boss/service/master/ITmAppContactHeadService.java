package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactHeadVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactHeadVO;

public interface ITmAppContactHeadService {

	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSTmAppContactHeadVO> listPage(ReqBMSTmAppContactHeadVO reqTmAppContactHeadVO);
}
