package com.ymkj.cms.biz.service.sign.sample;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.common.sign.BaseSign;

public class Sample1 extends BaseSign<ResBMSContractTemplateVO> {

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResBMSContractTemplateVO> res) {
		// TODO 自动生成的方法存根
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResBMSContractTemplateVO> res) {
		// TODO 自动生成的方法存根
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResBMSContractTemplateVO> res) {
		// TODO 自动生成的方法存根
		return super.after(reqLoanContractSignVo, res);
	}

	
}
