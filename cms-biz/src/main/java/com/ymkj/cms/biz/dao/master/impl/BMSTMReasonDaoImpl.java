package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSTMReasonDao;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;

@Repository
public class BMSTMReasonDaoImpl extends BaseDaoImpl<BMSTMReasonEntity> implements IBMSTMReasonDao{

	@Override
	public List<BMSTMReasonEntity> twoLevelparents(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(getStatement("twoLevelparents"), paramMap);
	}

	@Override
	public BMSTMReasonEntity getByParam(Map<String, Object> reasonParamMap) {
		
		return getSessionTemplate().selectOne(getStatement("getByParam"), reasonParamMap);
	}

	@Override
	public List<BMSTMReasonEntity> findFirstReasonByOperType(Map<String, Object> map) {
		
		return getSessionTemplate().selectList(getStatement("findFirstReasonByOperType"), map);
	}

	@Override
	public List<BMSTMReasonEntity> findSecondReasonByOperType(Map<String, Object> map) {
		
		return getSessionTemplate().selectList(getStatement("findSecondReasonByOperType"), map);
	}

	/*@Override
	public List<BMSTMReasonEntity> findReasonByOperType(Map<String, Object> map) {
		
		return getSessionTemplate().selectList(getStatement("findReasonByOperType"), map);
	}*/

}
