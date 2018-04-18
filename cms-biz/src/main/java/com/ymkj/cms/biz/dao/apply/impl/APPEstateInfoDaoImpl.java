package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPEstateInfoDao;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;

@Repository
public class APPEstateInfoDaoImpl extends BaseDaoImpl<APPEstateInfoEntity> implements APPEstateInfoDao{

	@Override
	public long updateAll(APPEstateInfoEntity appEstateInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appEstateInfoEntity);
	}

}
