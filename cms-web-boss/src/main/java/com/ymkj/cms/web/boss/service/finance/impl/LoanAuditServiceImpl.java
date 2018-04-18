package com.ymkj.cms.web.boss.service.finance.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanExportInfoVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.facade.finance.LoanFacade;
import com.ymkj.cms.web.boss.service.finance.ILoanAuditService;

@Service
public class LoanAuditServiceImpl implements ILoanAuditService{
	@Autowired
	private LoanFacade loanFacade;
	
	@Override
	public PageResult<ResLoanVo> listPage(ReqLoanVo reqLoanVo) {
		PageResult<ResLoanVo> pageResult = loanFacade.listPage(reqLoanVo);
		return pageResult;
	}

	@Override
	public PageResult<ResLoanVo> doneListPage(ReqLoanVo reqLoanVo) {
		PageResult<ResLoanVo> pageResult = loanFacade.doneListPage(reqLoanVo);
		return pageResult;
	}

	@Override
	public Response<ResLoanVo> passAuditLoan(ReqLoanVo reqLoanVo) {
		return loanFacade.passAuditLoan(reqLoanVo);
	}

	@Override
	public Response<ResLoanVo> bacthPassAudit(ReqLoanVo reqLoanVo) {

		return loanFacade.bacthPassAudit(reqLoanVo);
	}
	
	@Override
	public Response<ResLoanVo> backAudit(ReqLoanVo reqLoanVo) {
		return loanFacade.backAudit(reqLoanVo);
	}

	@Override
	public Response<String> valiProductIsDisabled(ReqLoanVo reqLoanVo) {
	
		return loanFacade.valiProductIsDisabled(reqLoanVo);
	}

	@Override
	public Response<ResLoanVo> bacthBackLoanAudit(ReqLoanVo reqLoanVo) {

		return loanFacade.bacthBackLoanAudit(reqLoanVo);
	}
	
	public ResListVO<ResLoanExportInfoVo> findLoanExportInfo(ReqLoanVo reqLoanVo){
		// 请求参数构造
		reqLoanVo.setSysCode(EnumConstants.BMS_SYSCODE);
		// 接口调用
	    ResListVO<ResLoanExportInfoVo> response = loanFacade.findLoanExportInfo(reqLoanVo);
		return response;
	}

	
	
}
