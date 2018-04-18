package com.ymkj.cms.biz.dao.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.dao.master.IBMSEnumCodeDao;
import com.ymkj.cms.biz.entity.master.BMSEnumCode;

@Repository
public class BMSEnumCodeDaoImpl extends BaseDaoImpl<BMSEnumCode> implements IBMSEnumCodeDao{

	@Override
	public List<BMSEnumCode> findEnumCodeByCondition(
			Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("findEnumCodeByCondition"), paramMap);
	}

	@Override
	public BMSEnumCode getByVO(Map<String, Object> paramMap) {
		return super.getSqlSession().selectOne(super.getStatement("getByVO"), paramMap);
	}
	
	@Override
	public List<BMSEnumCode> getProducAssetsInfo(Map<String, Object> paramMap){
		return super.getSqlSession().selectList(super.getStatement("getProducAssetsInfo"), paramMap);
	}

	@Override
	public List<BMSEnumCode> getProducAssetsInfoByCode(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("getProducAssetsInfoCode"), paramMap);
	}

	@Override
	public List<BMSEnumCode> findCodeByUnit(Map<String, String> map) {
		return super.getSqlSession().selectList(super.getStatement("findCodeByUnit"), map);
	}

	@Override
	public List<BMSEnumCode> findCodeByProfession(Map<String, Object> map) {
		return super.getSqlSession().selectList(super.getStatement("findCodeByProfession"), map);
	}

	@Override
	public List<BMSEnumCode> findByservenProfession() {
		Map<String,Object> map=new HashMap<String,Object>();
		return super.getSqlSession().selectList(super.getStatement("findByservenProfession"), map);
	}
}
