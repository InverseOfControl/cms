package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPPolicyInfoDao;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;

@Repository
public class APPPolicyInfoDaoImpl extends BaseDaoImpl<APPPolicyInfoEntity> implements APPPolicyInfoDao{

	@Override
	public long updateAll(APPPolicyInfoEntity appPolicyInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appPolicyInfoEntity);
	}

}
