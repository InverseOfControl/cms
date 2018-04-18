package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppProvidentInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppProvidentInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppProvidentInfoService;
@Service
public class TmAppProvidentInfoServiceImpl implements ITmAppProvidentInfoService{
	@Autowired
	private TmAppProvidentInfoFacade TmAppProvidentInfoFacade;

	@Override
	public PageResult<ResBMSTmAppProvidentInfoVO> listPage(ReqBMSTmAppProvidentInfoVO reqTmAppProvidentInfoVO) {
		PageResult<ResBMSTmAppProvidentInfoVO> pageResult = TmAppProvidentInfoFacade.listPage(reqTmAppProvidentInfoVO);
		return pageResult;
	}

}
