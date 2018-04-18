package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactHeadVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactHeadVO;
import com.ymkj.cms.web.boss.facade.master.TmAppContactHeadFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppContactHeadService;
@Service
public class TmAppContactHeadServiceImpl implements ITmAppContactHeadService{
	@Autowired
	private TmAppContactHeadFacade TmAppContactHeadFacade;

	@Override
	public PageResult<ResBMSTmAppContactHeadVO> listPage(ReqBMSTmAppContactHeadVO reqTmAppContactHeadVO) {
		PageResult<ResBMSTmAppContactHeadVO> pageResult = TmAppContactHeadFacade.listPage(reqTmAppContactHeadVO);
		return pageResult;
	}

}
