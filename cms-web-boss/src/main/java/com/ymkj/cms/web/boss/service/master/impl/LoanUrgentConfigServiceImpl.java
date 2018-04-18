package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.web.boss.facade.master.LoanUrgentConfigFacade;
import com.ymkj.cms.web.boss.service.master.ILoanUrgentConfigService;

@Service
public class LoanUrgentConfigServiceImpl implements ILoanUrgentConfigService{

	@Autowired
	private LoanUrgentConfigFacade loanUrgentConfigFacade;

	@Override
	public PageResult<ResBMSLoanUrgentConfigVO> listPage(ReqBMSUrgentLimitListVO reqOrgVO) {
		PageResult<ResBMSLoanUrgentConfigVO> pageResult = loanUrgentConfigFacade.listPage(reqOrgVO);
		return pageResult;
	}

	@Override
	public Integer updateOrg(ReqBMSUrgentLimitListVO reqOrgVO) {
		Integer isSuccess=loanUrgentConfigFacade.updateOrg(reqOrgVO);
		return isSuccess;
	}
}
