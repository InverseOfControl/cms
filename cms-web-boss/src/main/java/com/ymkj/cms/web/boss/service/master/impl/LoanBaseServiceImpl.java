package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.web.boss.common.ResponsePage;
import com.ymkj.cms.web.boss.facade.apply.LoanBaseFacade;
import com.ymkj.cms.web.boss.service.master.ILoanBaseService;

@Service
public class LoanBaseServiceImpl implements ILoanBaseService {

	@Autowired
	private LoanBaseFacade loanBaseFacade;
	@Override
	public PageResult<ResBMSLoanBaseVO> listPage(ReqBMSLoanBaseVO reqLoanBaseVO) {
		PageResult<ResBMSLoanBaseVO> pageResult = loanBaseFacade.listPage(reqLoanBaseVO);
		return pageResult;
	}

}
