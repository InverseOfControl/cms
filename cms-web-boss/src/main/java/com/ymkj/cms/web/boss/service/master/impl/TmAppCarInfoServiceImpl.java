package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppCarInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppCarInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppCarInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppCarInfoService;
@Service
public class TmAppCarInfoServiceImpl implements ITmAppCarInfoService{
	@Autowired
	private TmAppCarInfoFacade TmAppCarInfoFacade;

	@Override
	public PageResult<ResBMSTmAppCarInfoVO> listPage(ReqBMSTmAppCarInfoVO reqTmAppCarInfoVO) {
		PageResult<ResBMSTmAppCarInfoVO> pageResult = TmAppCarInfoFacade.listPage(reqTmAppCarInfoVO);
		return pageResult;
	}
}
