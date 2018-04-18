package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRejectReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRejectReasonListVO;

public interface IBMSRejectReasonExecuter {

	public Response<ResBMSRejectReasonListVO>  listBy(ReqBMSRejectReasonVO reqRejectReasonVO) ;
	
	 
}
