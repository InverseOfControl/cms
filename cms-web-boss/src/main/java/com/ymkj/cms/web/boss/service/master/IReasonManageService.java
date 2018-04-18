package com.ymkj.cms.web.boss.service.master;


import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IReasonManageService {
	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO);
	/**
	 * 根据请求param查询数据
	 */
	public ResListVO<ResBMSReasonVO> findBMSReasonByValue( ReqBMSTMReasonVO reqReasonVO);
	/**
	 * 新增原因
	 */
	public boolean addReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
	/**
	 * 编辑
	 */
	public boolean editReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 查询全部原因
	 */
	public ResListVO<ResBMSReasonVO> findReasonExport(ReqBMSTMReasonVO reqBMSReasonVO);
	
	/**
	 * 根据入参查询原因值
	 */
	public ResListVO<ResBMSReasonVO> findReasonByParame(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 根据原因CODE删除原因信息
	 */
	public boolean delReasonByCode(ReqBMSReasonVO reqBMSReasonVO);
}
