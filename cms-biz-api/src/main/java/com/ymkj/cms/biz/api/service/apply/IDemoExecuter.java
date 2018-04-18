package com.ymkj.cms.biz.api.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;

/**
 * demo 接口定义
 * @author haowp
 *
 */
public interface IDemoExecuter {
	/**
	 * 根据 id 查询 demoVO
	 * @param reqDemoVO
	 * @return
	 */
	public Response<ResDemoVO> findById(ReqDemoVO reqDemoVO);
	/**
	 * 根据名字查询 demoVO
	 * @param ReqDemoVO
	 * @return
	 */
	public Response<ResDemoVO> findByName(ReqDemoVO reqDemoVO);
	
	/**
	 * 分页查询 demoVO
	 * @param ReqDemoVO
	 * @return
	 */
	public PageResponse<ResDemoVO> listPage(ReqDemoVO reqDemoVO) ;
	
	/**
	 * 保存或更新 demoVO
	 * @param ReqDemoVO
	 * @return
	 */
	public Response<Object> saveOrUpdate(ReqDemoVO reqDemoVO);
	
	/**
	 * 批量保存 demo
	 * @param reqDemoVOs
	 * @return
	 */
	public Response<Object> saveDemos(List<ReqDemoVO> reqDemoVOs);
	
}
