package com.ymkj.cms.web.boss.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;

public interface ICreditRatingLimitService {

	
	
	/**
	 * 根据请求VO 查询分页信息
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSCreditRatingLimitVO> listPage(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	
	public Response<List<Map<String,Object>>> findByProductAll(ReqBMSCreditRatingLimitVO v);
	
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
}
