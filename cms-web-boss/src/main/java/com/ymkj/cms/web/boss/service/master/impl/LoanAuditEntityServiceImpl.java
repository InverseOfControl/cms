package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanAuditVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanAuditVO;
import com.ymkj.cms.web.boss.facade.apply.LoanAuditFacade;
import com.ymkj.cms.web.boss.service.master.ILoanAuditEntityService;

@Service
public class LoanAuditEntityServiceImpl implements ILoanAuditEntityService{


	@Autowired
	private LoanAuditFacade loanAuditFacade;
	@Override
	public PageResult<ResBMSLoanAuditVO> listPage(ReqBMSLoanAuditVO reqLoanAuditVO) {
		PageResult<ResBMSLoanAuditVO> pageResult = loanAuditFacade.listPage(reqLoanAuditVO);
		return pageResult;
	}

}
