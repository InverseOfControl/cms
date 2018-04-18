package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;

public interface IBMSReasonManagementExecuter {
	/**
	 * 查询原因信息（分页）
	 * @param reqReasonVO
	 * @return
	 */
	public PageResponse<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO);
	
	/***
	 * 新增原因
	 */
	public Response<ResBMSReasonVO> addReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 根据ID查询对应的数据
	 */
	public Response<ResBMSReasonVO> queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 编辑
	 */
	public Response<ResBMSReasonVO> editReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
	/**
	 * 删除
	 */
	public Response<ResBMSReasonVO> deleteReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
   /**
    * 根据CODE查询原因
    */
	public Response<ResBMSReasonVO>findBMSReasonByCode(ReqBMSReasonVO reqBMSReasonVO);
}
