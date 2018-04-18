package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.entity.master.BMSCreditRatingLimit;

public interface IBMSCreditRatingLimitDao extends BaseDao<BMSCreditRatingLimit>{

	public Response<List<Map<String,Object>>> findByProductAll();
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO);
}
