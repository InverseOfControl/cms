package com.ymkj.cms.biz.service.finance.waimao.three.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.finance.base.BackAudit2Impl;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;
import com.ymkj.cms.biz.service.sign.lujs.ifc.LUJSInterfaceService;

@Service
public class WaiMaoTBackLoanAuditImpl extends BackAudit2Impl{
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
