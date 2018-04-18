package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppCardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppCardLoanInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppCardLoanInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppCardLoanInfoService;
@Service
public class TmAppCardLoanInfoServiceImpl implements ITmAppCardLoanInfoService{
	@Autowired
	private TmAppCardLoanInfoFacade TmAppCardLoanInfoFacade;

	@Override
	public PageResult<ResBMSTmAppCardLoanInfoVO> listPage(ReqBMSTmAppCardLoanInfoVO reqTmAppCardLoanInfoVO) {
		PageResult<ResBMSTmAppCardLoanInfoVO> pageResult = TmAppCardLoanInfoFacade.listPage(reqTmAppCardLoanInfoVO);
		return pageResult;
	}

}
