package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseRelaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseRelaVO;
import com.ymkj.cms.web.boss.facade.master.LoanBaseRelaFacade;
import com.ymkj.cms.web.boss.service.master.ILoanBaseRelaService;
@Service
public class LoanBaseRelaServiceImpl implements ILoanBaseRelaService{
	@Autowired
	private LoanBaseRelaFacade LoanBaseRelaFacade;

	@Override
	public PageResult<ResBMSLoanBaseRelaVO> listPage(ReqBMSLoanBaseRelaVO reqLoanBaseRelaVO) {
		PageResult<ResBMSLoanBaseRelaVO> pageResult = LoanBaseRelaFacade.listPage(reqLoanBaseRelaVO);
		return pageResult;
	}
}
