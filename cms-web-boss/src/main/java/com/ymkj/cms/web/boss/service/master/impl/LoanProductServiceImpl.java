package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanProductVO;
import com.ymkj.cms.web.boss.facade.apply.LoanProductFacade;
import com.ymkj.cms.web.boss.service.master.ILoanProductService;

@Service
public class LoanProductServiceImpl implements ILoanProductService{


	@Autowired
	private LoanProductFacade loanProductFacade;
	@Override
	public PageResult<ResBMSLoanProductVO> listPage(ReqBMSLoanProductVO reqLoanProductVO) {
		PageResult<ResBMSLoanProductVO> pageResult = loanProductFacade.listPage(reqLoanProductVO);
		return pageResult;
	}
}
