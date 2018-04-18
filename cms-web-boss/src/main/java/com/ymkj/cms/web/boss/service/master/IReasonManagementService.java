package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;

public interface IReasonManagementService {
	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO);
	
	/**
	 * 新增原因
	 */
	public boolean addReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 根据ID查询对应数据
	 */
	public ResBMSReasonVO queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 编辑
	 */
	public boolean editReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
	
	/**
	 * 删除
	 */
	public boolean deleteReasonManagement(ReqBMSReasonVO reqBMSReasonVO);
}
