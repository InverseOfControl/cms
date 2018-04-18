package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppEstateInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppEstateInfoVO;

public interface ITmAppEstateInfoService {
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSTmAppEstateInfoVO> listPage(ReqBMSTmAppEstateInfoVO reqTmAppEstateInfoVO);
}
