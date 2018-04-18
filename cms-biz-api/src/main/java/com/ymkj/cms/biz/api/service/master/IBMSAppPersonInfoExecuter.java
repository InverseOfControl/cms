package com.ymkj.cms.biz.api.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;


public interface IBMSAppPersonInfoExecuter {
	
	public PageResponse<ResBMSAppPersonInfoVO> listPage(ReqBMSAppPersonInfoVO reqBMSAppPersonInfoVO);
	
	public Response<ResBMSAppPersonInfoVO> updateByLoanNo(ReqBMSAppPersonInfoVO reqBMSAppPersonInfoVO);
	
	public Response<List<Map<String, Object>>> queryAdditionRecords(Map<String, String> paramMap);
	public Response<Integer> updatePhoneCellStatus(Map<String, Object> map);

}
