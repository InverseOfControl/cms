package com.ymkj.cms.web.boss.service.apply;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;

/**
 * demo 业务层接口
 * @author haowp
 */
public interface IDemoService {
	/**
	 * 根据ID 查询 vo
	 * @param id
	 * @return
	 */
	public ResDemoVO findById(Long id);
	/**
	 * 根据 name 查询vo
	 * @param name
	 * @return
	 */
	public ResDemoVO findByName(String name);
	
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResDemoVO> listPage(ReqDemoVO reqDemoVO);
	
	/**
	 * 保存或更新 demo
	 * @param ReqDemoVO
	 * @return
	 */
	public Long saveOrUpdate(ReqDemoVO reqDemoVO);
	
}
