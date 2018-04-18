package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppMasterLoanInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppMasterLoanInfoService;
@Service
public class TmAppMasterLoanInfoServiceImpl implements ITmAppMasterLoanInfoService{
	@Autowired
	private TmAppMasterLoanInfoFacade TmAppMasterLoanInfoFacade;

	@Override
	public PageResult<ResBMSTmAppMasterLoanInfoVO> listPage(ReqBMSTmAppMasterLoanInfoVO reqTmAppMasterLoanInfoVO) {
		PageResult<ResBMSTmAppMasterLoanInfoVO> pageResult = TmAppMasterLoanInfoFacade.listPage(reqTmAppMasterLoanInfoVO);
		return pageResult;
	}

}
