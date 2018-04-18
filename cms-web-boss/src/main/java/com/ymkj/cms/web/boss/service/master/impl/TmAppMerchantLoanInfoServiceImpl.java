package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMerchantLoanInfoVO;
import com.ymkj.cms.web.boss.facade.master.TmAppMerchantLoanInfoFacade;
import com.ymkj.cms.web.boss.service.master.ITmAppMerchantLoanInfoService;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMerchantLoanInfoVO;
@Service
public class TmAppMerchantLoanInfoServiceImpl implements ITmAppMerchantLoanInfoService{
	@Autowired
	private TmAppMerchantLoanInfoFacade TmAppMerchantLoanInfoFacade;

	@Override
	public PageResult<ResBMSTmAppMerchantLoanInfoVO> listPage(ReqBMSTmAppMerchantLoanInfoVO reqTmAppMerchantLoanInfoVO) {
		PageResult<ResBMSTmAppMerchantLoanInfoVO> pageResult = TmAppMerchantLoanInfoFacade.listPage(reqTmAppMerchantLoanInfoVO);
		return pageResult;
	}

}
