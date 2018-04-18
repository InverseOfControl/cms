package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmParameterVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmParameterVO;
import com.ymkj.cms.web.boss.facade.apply.TmParameterFacade;
import com.ymkj.cms.web.boss.service.master.ITmParameterService;

@Service
public class TmParameterServiceImpl implements ITmParameterService {

	@Autowired
	private TmParameterFacade tmParameterFacade;

	@Override
	public PageResult<ResBMSTmParameterVO> listPage(ReqBMSTmParameterVO reqDemoVO) {
		return tmParameterFacade.listPage(reqDemoVO);
	}

	@Override
	public boolean addTmParameter(ReqBMSTmParameterVO reqDemoVO) {
		int resMsg = tmParameterFacade.addTmParameter(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean updateTmParameter(ReqBMSTmParameterVO reqDemoVO) {
		return tmParameterFacade.updateTmParameter(reqDemoVO);
	}

	@Override
	public ResBMSTmParameterVO findById(Long id) {
		return tmParameterFacade.findById(id);
	}

}
