package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppMasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppMasterLoanInfoVO;

public interface IBMSTmAppMasterLoanInfoExecuter {
	
	public PageResponse<ResBMSTmAppMasterLoanInfoVO> listPage(ReqBMSTmAppMasterLoanInfoVO reqBMSTmAppMasterLoanInfoVO);

}
