package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSTmParameterExecuter {
	public PageResponse<ResBMSTmParameterVO> listPage(ReqBMSTmParameterVO reqDemoVO);

	public Response<ResBMSTmParameterVO> addTmParameter(ReqBMSTmParameterVO reqDemoVO);

	public Response<ResBMSTmParameterVO> updateTmParameter(ReqBMSTmParameterVO reqDemoVO);

	public Response<ResBMSTmParameterVO> findOne(ReqBMSTmParameterVO reqDemoVO);
	
	public ResListVO<ResBMSTmParameterVO> findTmParameterByCode(ReqBMSTmParameterVO reqDemoVO);
	
	/**
	 * 参数查找
	 * @param reqDemoVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月27日上午9:58:11
	 */
	public Response<ResBMSTmParameterVO> findOneByParam(ReqBMSTmParameterVO reqDemoVO);
}
