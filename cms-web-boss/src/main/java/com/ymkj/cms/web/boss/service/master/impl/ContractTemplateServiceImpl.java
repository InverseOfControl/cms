package com.ymkj.cms.web.boss.service.master.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.facade.apply.ContractTemplateFacade;
import com.ymkj.cms.web.boss.service.master.IContractTemplateService;
@Service
public class ContractTemplateServiceImpl implements IContractTemplateService{
	
	@Autowired
	private ContractTemplateFacade contractTemplateFacade;
	@Override
	public PageResult<ResBMSContractTemplateVO> listPage(
			ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		return contractTemplateFacade.listPage(reqBMSContractTemplateVO);
	}
	@Override
	public Long saveTemplate(
			ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		Long resLong = null;
		String result = contractTemplateFacade.saveTemplate(reqBMSContractTemplateVO);
		if(!StringUtils.isEmpty(result)){
			resLong = Long.parseLong(result);
		}
		return resLong;
	}
	@Override
	public List<ResBMSContractTemplateVO> getAllTemplate(
			ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		ResListVO<ResBMSContractTemplateVO> resList = contractTemplateFacade.getAllTemplate(reqBMSContractTemplateVO);
		List<ResBMSContractTemplateVO> templateList = new ArrayList<>();
		if(resList != null && resList.getCollections() !=null && resList.getCollections().size()>0){
			templateList = resList.getCollections();
		}
		return templateList;
	}
	@Override
	public Long updateTemplate(ReqBMSContractTemplateVO reqBMSContractTemplateVO) {
		Long resLong = null;
		String result = contractTemplateFacade.updateTemplate(reqBMSContractTemplateVO);
		if(!StringUtils.isEmpty(result)){
			resLong = Long.parseLong(result);
		}
		return resLong;
	}
	@Override
	public ResBMSContractTemplateVO findByVO(ReqBMSContractTemplateVO reqContractTemplateVO) {
		ResBMSContractTemplateVO res=	contractTemplateFacade.findByVO(reqContractTemplateVO);
		return res;
	}

}
