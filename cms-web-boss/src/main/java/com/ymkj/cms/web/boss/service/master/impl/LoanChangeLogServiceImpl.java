package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanChangeLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogVO;
import com.ymkj.cms.web.boss.facade.master.LoanChangeLogFacade;
import com.ymkj.cms.web.boss.service.master.ILoanChangeLogService;
@Service
public class LoanChangeLogServiceImpl implements ILoanChangeLogService{
	@Autowired
	private LoanChangeLogFacade LoanChangeLogFacade;

	@Override
	public PageResult<ResBMSLoanChangeLogVO> listPage(ReqBMSLoanChangeLogVO reqLoanChangeLogVO) {
		PageResult<ResBMSLoanChangeLogVO> pageResult = LoanChangeLogFacade.listPage(reqLoanChangeLogVO);
		return pageResult;
	}

}
