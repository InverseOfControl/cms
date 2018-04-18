package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppCardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppCardLoanInfoVO;

public interface ITmAppCardLoanInfoService {

	/**
	 * 根据请求VO 查询分页信息
	 * @param 
	 * @return
	 */
	public PageResult<ResBMSTmAppCardLoanInfoVO> listPage(ReqBMSTmAppCardLoanInfoVO reqTmAppCardLoanInfoVO);
}
