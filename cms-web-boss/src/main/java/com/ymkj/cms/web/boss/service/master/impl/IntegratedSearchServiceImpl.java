package com.ymkj.cms.web.boss.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqQueryLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSQueryLoanLogVO;
import com.ymkj.cms.web.boss.facade.master.IntegratedSearchFacade;
import com.ymkj.cms.web.boss.service.master.IIntegratedSearchService;


@Service
public class IntegratedSearchServiceImpl implements IIntegratedSearchService{

	@Autowired
	private IntegratedSearchFacade integratedSearchFacade;
	
	@Override
	public List<ResBMSQueryLoanLogVO> queryLoanLog(ReqQueryLoanLogVO reqQueryLoanLogVO) {

		return integratedSearchFacade.queryLoanLog(reqQueryLoanLogVO);
	}

}
