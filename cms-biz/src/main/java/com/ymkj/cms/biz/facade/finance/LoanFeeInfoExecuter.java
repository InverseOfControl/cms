package com.ymkj.cms.biz.facade.finance;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.ymkj.cms.biz.api.service.finance.ILoanFeeInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanFeeInfoVO;
import com.ymkj.cms.biz.service.finance.ILoanFeeInfoService;
import com.ymkj.cms.biz.service.finance.ILoanService;

@Service
public class LoanFeeInfoExecuter implements ILoanFeeInfoExecuter{

	
	@Autowired
	private ILoanFeeInfoService iLoanFeeInfoService;
	
	@Override
	public ReqBMSLoanFeeInfoVO insert(ReqBMSLoanFeeInfoVO vo) {
		
		return iLoanFeeInfoService.insert(vo);
	}

}
