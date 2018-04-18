package com.ymkj.cms.biz.service.finance;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanFeeInfoVO;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanFeeInfo;

public interface ILoanFeeInfoService extends BaseService<BMSLoanFeeInfo>{

	public ReqBMSLoanFeeInfoVO insert(ReqBMSLoanFeeInfoVO vo);
}
