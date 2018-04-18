package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmParameterVO;

public interface ITmParameterService {

	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSTmParameterVO> listPage(ReqBMSTmParameterVO reqDemoVO);

	public boolean addTmParameter(ReqBMSTmParameterVO reqDemoVO);

	public boolean updateTmParameter(ReqBMSTmParameterVO reqDemoVO);

	/**
	 * 根据ID 查询 vo
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSTmParameterVO findById(Long id);
}
