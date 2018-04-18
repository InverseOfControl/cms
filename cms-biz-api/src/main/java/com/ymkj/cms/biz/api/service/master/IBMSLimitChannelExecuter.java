package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

public interface IBMSLimitChannelExecuter {
	/**
	 * 查询出所有期限渠道信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResProductBaseListVO> listLimitChannelBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 查询出网店所有期限渠道信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResProductBaseListVO> listLimitChannelRateBy(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 获取分页数据
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public PageResponse<ResBMSOrgLimitChannelVO> listPage(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	
	/**
	 * 保存渠道产品信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> saveProductLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 更新渠道产品信息
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> updateChannelLimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 逻辑删除该条数据
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public Response<ResBMSOrgLimitChannelVO> logicalDelete(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	
	/**
	 * 得到
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public ResBMSOrgLimitChannelVO getFULimit(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	/**
	 * 得到
	 * @param reqBMSOrgLimitChannelVO
	 * @return
	 */
	public ResBMSOrgLimitChannelVO getFULimitByOrgId(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO);
	
	public Response<ResBMSLimitChannelVO> updateByAuLimitId(ReqBMSLimitChannelVO reqBMSOrgLimitChannelVO);
	/***
	 * 获取产品期限渠道表所有数据
	 * @param reqBMSLimitChannelVo
	 * @return
	 */
	public PageResponse<ResBMSLimitChannelVO> listPage(ReqBMSLimitChannelVO reqBMSLimitChannelVo);
	
}
