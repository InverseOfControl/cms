package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;

public interface IBMSReasonReLinksExecuter {
	
	/**
	 * 查询原因信息（分页）
	 * @param reqReasonVO
	 * @return
	 */
	public PageResponse<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO);
	
	/**
	 * 根据CODE查询
	 */
	public Response<ResBMSReasonVO> findBMSReLinksById(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 编辑
	 */
	public Response<ResBMSReasonVO> editReasonReLinks(ReqBMSReasonVO reqBMSReasonVO);

}
