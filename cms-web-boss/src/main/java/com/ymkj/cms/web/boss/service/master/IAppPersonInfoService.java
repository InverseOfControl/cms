package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;

public interface IAppPersonInfoService {
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSAppPersonInfoVO> listPage(ReqBMSAppPersonInfoVO reqAppPersonInfoVO);
}
