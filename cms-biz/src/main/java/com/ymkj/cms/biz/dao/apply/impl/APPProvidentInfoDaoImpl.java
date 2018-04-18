package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPProvidentInfoDao;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;

@Repository
public class APPProvidentInfoDaoImpl extends BaseDaoImpl<APPProvidentInfoEntity> implements APPProvidentInfoDao{

	@Override
	public long updateAll(APPProvidentInfoEntity appProvidentInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appProvidentInfoEntity);
	}

}
