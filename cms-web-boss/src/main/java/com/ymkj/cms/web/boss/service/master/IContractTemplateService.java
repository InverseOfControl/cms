package com.ymkj.cms.web.boss.service.master;

import java.util.List;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;

public interface IContractTemplateService {
	/**
	 * 获取分页模板
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public PageResult<ResBMSContractTemplateVO> listPage(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	/**
	 * 保存模板信息
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public Long saveTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	
	/**
	 * 更新模板信息
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public Long updateTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	/**
	 * 获取模板返回对象
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public List<ResBMSContractTemplateVO>  getAllTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO);
	
	/**
	 * 获取模板对象
	 * @param reqBMSContractTemplateVO
	 * @return
	 */
	public ResBMSContractTemplateVO findByVO(ReqBMSContractTemplateVO reqContractTemplateVO);
}
