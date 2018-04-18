package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;

public interface IReasonReLinkService {
	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO);
	
	public ResBMSReasonVO queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO);
	/**
	 * 编辑
	 */
	public boolean editReasonReLinks(ReqBMSReasonVO reqBMSReasonVO);

}
