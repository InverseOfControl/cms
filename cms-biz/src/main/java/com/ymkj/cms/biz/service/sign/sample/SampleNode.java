package com.ymkj.cms.biz.service.sign.sample;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.common.sign.BaseSign;

public class SampleNode extends BaseSign<ResBMSContractTemplateVO> {
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResBMSContractTemplateVO> res) {
		System.err.println("Sample3");
		return super.execute(reqLoanContractSignVo, res);
	}
}
