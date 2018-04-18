package com.ymkj.cms.biz.api.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;

public interface IBMSCreditRatingLimitExecuter {
	public PageResponse<ResBMSCreditRatingLimitVO> listPage(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<List<Map<String,Object>>> findByProductAll(ReqBMSCreditRatingLimitVO v);
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
}
