package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSSysLoanLogVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTmAppContactInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLoanLogListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSSysLoanLogVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSTmAppContactInfoVO;

public interface IBMSSysLoanLogExecuter {

	public Response<ResBMSSysLoanLogListVO>  listByLoan(ReqBMSSysLoanLogVO reqSysLoanLogVO) ;
	
	
	 
}
