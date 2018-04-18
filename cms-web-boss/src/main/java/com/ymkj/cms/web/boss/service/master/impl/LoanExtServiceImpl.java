package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanExtVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanExtVO;
import com.ymkj.cms.web.boss.facade.apply.LoanExtFacade;
import com.ymkj.cms.web.boss.service.master.ILoanExtService;

@Service
public class LoanExtServiceImpl implements ILoanExtService{
	
	@Autowired
	private LoanExtFacade loanExtFacade;
	@Override
	public PageResult<ResBMSLoanExtVO> listPage(ReqBMSLoanExtVO reqLoanExtVO) {
		PageResult<ResBMSLoanExtVO> pageResult = loanExtFacade.listPage(reqLoanExtVO);
		return pageResult;
	}


}
