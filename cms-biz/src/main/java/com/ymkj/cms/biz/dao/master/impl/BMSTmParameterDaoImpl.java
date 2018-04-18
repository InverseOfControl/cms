package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSTmParameterDao;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;

@Repository
public class BMSTmParameterDaoImpl extends BaseDaoImpl<BMSTmParameter> implements IBMSTmParameterDao {

	@Override
	public List<BMSTmParameter> findByCode(String code) {
		
		return getSessionTemplate().selectList(getStatement("findByCode"), code);
	}

	@Override
	public List<BMSTmParameter> queryByCode(String code) {
		
		return getSessionTemplate().selectList(getStatement("queryByCode"), code);
	}

}
