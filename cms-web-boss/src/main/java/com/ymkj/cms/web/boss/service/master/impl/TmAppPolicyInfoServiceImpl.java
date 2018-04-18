package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppPolicyInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppPolicyInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppPolicyInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppPolicyInfoService;
@Service
public class TmAppPolicyInfoServiceImpl implements ITmAppPolicyInfoService{
	@Autowired
	private TmAppPolicyInfoFacade TmAppPolicyInfoFacade;

	@Override
	public PageResult<ResBMSTmAppPolicyInfoVO> listPage(ReqBMSTmAppPolicyInfoVO reqTmAppPolicyInfoVO) {
		PageResult<ResBMSTmAppPolicyInfoVO> pageResult = TmAppPolicyInfoFacade.listPage(reqTmAppPolicyInfoVO);
		return pageResult;
	}

}
