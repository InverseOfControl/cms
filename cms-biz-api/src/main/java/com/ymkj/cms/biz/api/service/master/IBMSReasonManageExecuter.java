package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

/**
 * 原因管理接口
 * @author YM10172
 *
 */
public interface IBMSReasonManageExecuter {

	/**
	 * 查询原因信息（分页）
	 * @param reqReasonVO
	 * @return
	 */
	public PageResponse<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO);

	/**
	 * 动态查询原因数据
	 * @param reqReasonVO
	 * @return
	 */
	public ResListVO<ResBMSReasonVO> findBMSReasonByValue(ReqBMSTMReasonVO reqReasonVO);
	
	/***
	 * 新增原因
	 */
	public Response<ResBMSReasonVO> addReasonManage(ReqBMSReasonVO reqBMSReasonVO);

	/**
	 * 编辑
	 */
	public Response<ResBMSReasonVO> editReasonManage(ReqBMSReasonVO reqBMSReasonVO);

	/**
	 * 查询所有数据
	 */
	public ResListVO<ResBMSReasonVO> findReasonExport(ReqBMSTMReasonVO reqReasonVO);

	/**
	 * 分局入参查询数据
	 */
	public ResListVO<ResBMSReasonVO> findReasonByParame(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 根据原因CODE删除原因信息
	 */
	public Response<Object> delReasonByCode(ReqBMSReasonVO reqBMSReasonVO);
}
