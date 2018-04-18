package com.ymkj.cms.web.boss.facade.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.service.finance.ILoanFeeInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanFeeInfoVO;

@Component
public class LoanFeeInfoFacade extends BaseFacade {

	
	
	@Autowired
	private ILoanFeeInfoExecuter iLoanFeeInfoExecuter;
	
	public ReqBMSLoanFeeInfoVO insert(ReqBMSLoanFeeInfoVO vo) {
		return iLoanFeeInfoExecuter.insert(vo);
	}
}
