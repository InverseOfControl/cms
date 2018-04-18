package com.ymkj.cms.biz.dao.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.dao.master.IBMSCreditRatingLimitDao;
import com.ymkj.cms.biz.entity.master.BMSCreditRatingLimit;

@Repository
public class BMSCreditRatingLimitDaoImpl extends BaseDaoImpl<BMSCreditRatingLimit> implements IBMSCreditRatingLimitDao{

	@Override
	public Response<List<Map<String, Object>>> findByProductAll() {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,Object>> list=super.getSqlSession().selectList(super.getStatement("findByProductAll"), map);
		Response<List<Map<String, Object>>> response=new Response<List<Map<String, Object>>>();
		response.setData(list);
		return response;
	}

	@Override
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("addCreditRating", reqBMSCreditRatingLimitVO.getAddCreditRating());
		map.put("addCustomerType", reqBMSCreditRatingLimitVO.getAddCustomerType());
		map.put("addProductCode", reqBMSCreditRatingLimitVO.getAddProductCode());
		map.put("addProductText", reqBMSCreditRatingLimitVO.getAddProductText());
		map.put("addRemark", reqBMSCreditRatingLimitVO.getAddRemark());
		map.put("creatorId", reqBMSCreditRatingLimitVO.getCreatorId());
		map.put("creator", reqBMSCreditRatingLimitVO.getCreator());
		map.put("addCreaditType", reqBMSCreditRatingLimitVO.getAddCreaditType());
		Integer returnSuccess=super.getSqlSession().insert(super.getStatement("addCreditRatingLimit"), map);
		Response<Integer> response=new Response<Integer>();
		response.setData(returnSuccess);
		return response;
	}

	@Override
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", reqBMSCreditRatingLimitVO.getId());
		map.put("modifierId", reqBMSCreditRatingLimitVO.getCreatorId());
		map.put("modifier", reqBMSCreditRatingLimitVO.getCreator());
		Integer returnSuccess=super.getSqlSession().delete(super.getStatement("deleteCreditRatingLimit"), map);
		Response<Integer> response=new Response<Integer>();
		response.setData(returnSuccess);
		return response;
	}

	@Override
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(
			ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", reqBMSCreditRatingLimitVO.getId());
		BMSCreditRatingLimit limit=super.getSqlSession().selectOne(super.getStatement("loadCreditRatingLimit"), map);
		ResBMSCreditRatingLimitVO v=new ResBMSCreditRatingLimitVO();
		BeanUtils.copyProperties(limit, v);
		Response<ResBMSCreditRatingLimitVO> r=new Response<ResBMSCreditRatingLimitVO>();
		r.setData(v);
		return r;
	}

	@Override
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", reqBMSCreditRatingLimitVO.getId());
		map.put("modifierId", reqBMSCreditRatingLimitVO.getCreatorId());
		map.put("modifier", reqBMSCreditRatingLimitVO.getCreator());
		map.put("updateCreditRating", reqBMSCreditRatingLimitVO.getUpdateCreditRating());
		map.put("updateCustomerType", reqBMSCreditRatingLimitVO.getUpdateCustomerType());
		map.put("updateProductCode", reqBMSCreditRatingLimitVO.getUpdateProductCode());
		map.put("updateProductText", reqBMSCreditRatingLimitVO.getUpdateProductText());
		map.put("updateRemark", reqBMSCreditRatingLimitVO.getUpdateRemark());
		map.put("updateCreaditType", reqBMSCreditRatingLimitVO.getUpdateCreaditType());
		Integer returnSuccess=super.getSqlSession().update(super.getStatement("updateCreditRatingLimit"), map);
		Response<Integer> response=new Response<Integer>();
		response.setData(returnSuccess);
		return response;
	}
	
	@Override
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Integer returnRepeat=super.getSqlSession().selectOne(super.getStatement("findAddIsRepeat"), reqBMSCreditRatingLimitVO);
		Response<Integer> response=new Response<Integer>();
		response.setData(returnRepeat);
		return response;
	}

	@Override
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		Integer returnRepeat=super.getSqlSession().selectOne(super.getStatement("findUpdateIsRepeat"), reqBMSCreditRatingLimitVO);
		Response<Integer> response=new Response<Integer>();
		response.setData(returnRepeat);
		return response;
	}


}
