package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonVO;
import com.ymkj.cms.web.boss.facade.master.AppPersonFacade;
import com.ymkj.cms.web.boss.service.master.IAppPersonService;
@Service
public class AppPersonServiceImpl implements IAppPersonService{
	@Autowired
	private AppPersonFacade appPersonFacade;

	@Override
	public PageResult<ResBMSAppPersonVO> listPage(ReqBMSAppPersonVO reqAppPersonVO) {
		PageResult<ResBMSAppPersonVO> pageResult = appPersonFacade.listPage(reqAppPersonVO);
		return pageResult;
	}
}
