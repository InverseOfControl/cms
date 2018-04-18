package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppContactInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppContactInfoService;
@Service
public class TmAppContactInfoServiceImpl implements ITmAppContactInfoService{
	@Autowired
	private TmAppContactInfoFacade TmAppContactInfoFacade;

	@Override
	public PageResult<ResBMSTmAppContactInfoVO> listPage(ReqBMSTmAppContactInfoVO reqTmAppContactInfoVO) {
		PageResult<ResBMSTmAppContactInfoVO> pageResult = TmAppContactInfoFacade.listPage(reqTmAppContactInfoVO);
		return pageResult;
	}
}
