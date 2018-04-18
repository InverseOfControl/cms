package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanLogVO;

public interface IBMSLoanLogExecuter {

	public PageResponse<ResBMSLoanLogVO> listPage(ReqBMSLoanLogVO reqBMSLoanLogVO);
	
	/**
	 * 获取最新日志
	 * @param reqBMSLoanLogVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月16日下午7:33:54
	 */
	public Response<ResBMSLoanLogVO> findLastLog(ReqBMSLoanLogVO reqBMSLoanLogVO);
}
