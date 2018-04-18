package com.ymkj.cms.biz.service.master;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;

public interface IBMSContractTemplateService extends BaseService<BMSContractTemplate> {
	/**
	 * 保存数据
	 * @param template
	 * @return
	 */
	public Long saveTemplate(BMSContractTemplate template);
	
	/**
	 * 更新数据
	 * @param template
	 * @return
	 */
	public Long updateTemplate(BMSContractTemplate template);
	
	
	/**
	 * 按vo传入的参数查询信息
	 * @param reqContractTemplateVO
	 * @return
	 */
	public BMSContractTemplate findByVO(ReqBMSContractTemplateVO reqContractTemplateVO);
	
	/**
	 * 按传入的channelCd查询合同模板信息
	 * @param reqContractTemplateVO
	 * @return
	 */
	public List<BMSContractTemplate> listByChannelCd(String channelCd) ;


}
