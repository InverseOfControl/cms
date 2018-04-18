package com.ymkj.cms.biz.api.service.master;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSDebtRadioVO;

public interface IBMSDebtRadioExecuter {

	public PageResponse<ResBMSDebtRadioVO> listPage(ReqBMSdebtRadioVO reqBMSDebtRadio);
	
	public Response<ResBMSDebtRadioVO> update(ReqBMSdebtRadioVO reqBMSDebtRadio);
	
	public Response<ResBMSDebtRadioVO> findDebtRadioById(ReqBMSdebtRadioVO reqBMSDebtRadio);
	
	public Response<Integer> testConnection(Request req);


}
