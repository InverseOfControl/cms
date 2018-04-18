package com.ymkj.cms.biz.service.sign.sample;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.common.sign.BaseSign;

public class Sample2 extends BaseSign<ResBMSContractTemplateVO> {

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResBMSContractTemplateVO> res) {
		res = new ResListVO<ResBMSContractTemplateVO>();
		return super.before(reqLoanContractSignVo, res);
	}

}
