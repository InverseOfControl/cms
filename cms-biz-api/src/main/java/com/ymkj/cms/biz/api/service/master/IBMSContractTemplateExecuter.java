package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

public interface IBMSContractTemplateExecuter {
	/**
	 * 分页显示模板
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public PageResponse<ResBMSContractTemplateVO> listPage(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	/**
	 * 保存模板
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public Response<ResBMSContractTemplateVO> saveTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	
	/**
	 * 更新模板
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public Response<ResBMSContractTemplateVO> updateTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	/**
	 * 获取所有的模板数据根据条件
	 * @param reqChannelVO
	 * @return
	 */
	public ResListVO<ResBMSContractTemplateVO> getAllTemplate(ReqBMSContractTemplateVO reqChannelVO);
	
	/**
	 * 按vo传入的参数查询数据
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public Response<ResBMSContractTemplateVO> findByVO(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	
	
	
}
