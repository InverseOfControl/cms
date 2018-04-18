package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;

public interface IBMSContractChangeExecuter {
	
	/**
	 * 分页列表查询
	 * @param reqBMSContractChangeVo
	 * @return
	 */
	public PageResponse<ResBMSContractChangeVo> listPage(ReqBMSContractChangeVo reqBMSContractChangeVo);
	
	/**
	 * 签约改派
	 * @param reqVo
	 * @return
	 */
	public Response<ResBMSContractChangeVo> changeContract(ReqBMSContractChangeVo reqVo);

}
