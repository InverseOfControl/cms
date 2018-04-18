package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;

import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;
import com.ymkj.cms.web.boss.facade.master.AppPersonInfoFacade;
import com.ymkj.cms.web.boss.service.master.IAppPersonInfoService;
@Service
public class AppPersonInfoServiceImpl implements IAppPersonInfoService{
	@Autowired
	private AppPersonInfoFacade AppPersonInfoFacade;

	@Override
	public PageResult<ResBMSAppPersonInfoVO> listPage(ReqBMSAppPersonInfoVO reqAppPersonInfoVO) {
		PageResult<ResBMSAppPersonInfoVO> pageResult = AppPersonInfoFacade.listPage(reqAppPersonInfoVO);
		return pageResult;
	}

}
