package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.dao.master.IBMSContractTemplateDao;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;

@Repository
public class BMSContractTemplateDaoImpl extends BaseDaoImpl<BMSContractTemplate> implements IBMSContractTemplateDao {

	@Override
	public BMSContractTemplate findByVO(ReqBMSContractTemplateVO reqContractTemplateVO) {
		return super.getSqlSession().selectOne(super.getStatement("findByVO"), reqContractTemplateVO);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<BMSContractTemplate> listByChannelCd(Map paramMap) {
		
		return super.getSqlSession().selectList(super.getStatement("listByChannelCd"),paramMap);
	}


}
