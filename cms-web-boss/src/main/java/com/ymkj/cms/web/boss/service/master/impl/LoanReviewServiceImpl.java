package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanReviewVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanReviewVO;
import com.ymkj.cms.web.boss.facade.master.LoanReviewFacade;
import com.ymkj.cms.web.boss.service.master.ILoanReviewService;
@Service
public class LoanReviewServiceImpl implements ILoanReviewService{
	@Autowired
	private LoanReviewFacade LoanReviewFacade;

	@Override
	public PageResult<ResBMSLoanReviewVO> listPage(ReqBMSLoanReviewVO reqLoanReviewVO) {
		PageResult<ResBMSLoanReviewVO> pageResult = LoanReviewFacade.listPage(reqLoanReviewVO);
		return pageResult;
	}
}
