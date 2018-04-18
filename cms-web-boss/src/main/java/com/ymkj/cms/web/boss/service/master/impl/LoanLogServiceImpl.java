package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLoanLogVO;
import com.ymkj.cms.web.boss.facade.apply.BankFacade;
import com.ymkj.cms.web.boss.facade.master.LoanLogFacade;
import com.ymkj.cms.web.boss.service.master.IBankService;
import com.ymkj.cms.web.boss.service.master.ILoanLogService;

@Service
public class LoanLogServiceImpl implements ILoanLogService{
	
	@Autowired
	private LoanLogFacade loanLogFacade;

	@Override
	public PageResult<ResBMSLoanLogVO> listPage(ReqBMSLoanLogVO reqLoanLogVO) {
		PageResult<ResBMSLoanLogVO> pageResult = loanLogFacade.listPage(reqLoanLogVO);
		return pageResult;
	}


}
