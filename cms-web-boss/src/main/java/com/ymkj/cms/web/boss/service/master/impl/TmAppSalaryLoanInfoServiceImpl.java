package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppSalaryLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppSalaryLoanInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppSalaryLoanInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppSalaryLoanInfoService;
@Service
public class TmAppSalaryLoanInfoServiceImpl implements ITmAppSalaryLoanInfoService{
	@Autowired
	private TmAppSalaryLoanInfoFacade TmAppSalaryLoanInfoFacade;

	@Override
	public PageResult<ResBMSTmAppSalaryLoanInfoVO> listPage(ReqBMSTmAppSalaryLoanInfoVO reqTmAppSalaryLoanInfoVO) {
		PageResult<ResBMSTmAppSalaryLoanInfoVO> pageResult = TmAppSalaryLoanInfoFacade.listPage(reqTmAppSalaryLoanInfoVO);
		return pageResult;
	}

}
