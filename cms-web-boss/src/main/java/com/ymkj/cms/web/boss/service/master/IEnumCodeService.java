package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;

public interface IEnumCodeService {
	

	/**
	 * 根据请求VO 查询分页信息
	 * 
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSEnumCodeVO> listPage(ReqBMSEnumCodeVO reqDemoVO);

	public boolean addEnumCode(ReqBMSEnumCodeVO reqDemoVO);

	public boolean updateEnumCode(ReqBMSEnumCodeVO reqDemoVO);

	/**
	 * 根据ID 查询 vo
	 * 
	 * @param id
	 * @return
	 */
	public ResBMSEnumCodeVO findById(Long id);
	
	/**
	 * 1. 请求参数构造 2. 接口调用 3. 响应结果处理
	 * 
	 * @param reqBMSEnumCodeVO
	 * @return
	 */
	public ResListVO<ResBMSEnumCodeVO> findEnumCodeByCondition(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	
	public ResListVO<ResBMSEnumCodeVO> listEnumCodeBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO);
	
	public ResListVO<ResBMSEnumCodeVO> listBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO);

}
