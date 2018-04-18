package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChannelVO;

public interface IBMSContractChannelExecuter {
	/***
	 * 分页显示模板
	 * @param reqContractChannelVo
	 * @return
	 */
	public PageResponse<ResBMSContractChannelVO> listPage(ReqBMSContractChannelVO reqContractChannelVo);

}
