package com.ymkj.cms.biz.dao.apply.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPCarInfoDao;
import com.ymkj.cms.biz.entity.APPPersonVauleAddresEntity;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;

@Repository
public class APPCarInfoDaoImpl extends BaseDaoImpl<APPCarInfoEntity> implements APPCarInfoDao{

	@Override
	public long updateAll(APPCarInfoEntity appCarInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appCarInfoEntity);
	}

	@Override
	public APPPersonVauleAddresEntity queryPersonVauleAddresEntity(Map<String, Object> paramMap) {
		return getSessionTemplate().selectOne("queryPersonVaule", paramMap);
	}
	
	

}
