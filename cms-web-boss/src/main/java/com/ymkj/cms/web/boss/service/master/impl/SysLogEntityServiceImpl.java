package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLogEntityVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLogEntityVO;
import com.ymkj.cms.web.boss.facade.master.SysLogEntityFacade;
import com.ymkj.cms.web.boss.service.master.ISysLogEntityService;

@Service
public class SysLogEntityServiceImpl implements ISysLogEntityService{
	@Autowired
	private SysLogEntityFacade sysLogEntityFacade;

	@Override
	public PageResult<ResBMSSysLogEntityVO> listPage(ReqBMSSysLogEntityVO reqSysLogEntityVO) {
		PageResult<ResBMSSysLogEntityVO> pageResult = sysLogEntityFacade.listPage(reqSysLogEntityVO);
		return pageResult;
	}

}
