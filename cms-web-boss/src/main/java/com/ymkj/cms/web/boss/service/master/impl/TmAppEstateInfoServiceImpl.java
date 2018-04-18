package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppEstateInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppEstateInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppEstateInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppEstateInfoService;
@Service
public class TmAppEstateInfoServiceImpl implements ITmAppEstateInfoService{
	@Autowired
	private TmAppEstateInfoFacade TmAppEstateInfoFacade;

	@Override
	public PageResult<ResBMSTmAppEstateInfoVO> listPage(ReqBMSTmAppEstateInfoVO reqTmAppEstateInfoVO) {
		PageResult<ResBMSTmAppEstateInfoVO> pageResult = TmAppEstateInfoFacade.listPage(reqTmAppEstateInfoVO);
		return pageResult;
	}
}
