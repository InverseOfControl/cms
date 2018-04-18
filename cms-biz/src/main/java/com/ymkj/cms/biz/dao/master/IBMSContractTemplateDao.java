package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;

public interface IBMSContractTemplateDao extends BaseDao<BMSContractTemplate>{
	
	/**
	 * 根据vo类传进来的信息查询返回信息
	 * @param paramMap
	 * @return
	 */
	BMSContractTemplate findByVO(ReqBMSContractTemplateVO reqContractTemplateVO) ;

	@SuppressWarnings("rawtypes")
	List<BMSContractTemplate> listByChannelCd(Map paramMap);
}
