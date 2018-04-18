package com.ymkj.cms.biz.service.finance.waimao.three.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.service.finance.base.PassAuditLoan2Impl;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

@Service
public class WaiMaoTPassAuditLoanImpl extends PassAuditLoan2Impl{
	
	@Autowired
	private LUJSInterfaceService lujsInterfaceService;
	
	
	@Override
	public boolean before(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		return super.before(reqLoanVo, res);
	}

	@Override
	public boolean execute(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		return super.execute(reqLoanVo, res);
	}

	@Override
	public boolean after(ReqLoanVo reqLoanVo, Response<ResLoanVo> res) {
		return super.after(reqLoanVo, res);
	}
	

}
