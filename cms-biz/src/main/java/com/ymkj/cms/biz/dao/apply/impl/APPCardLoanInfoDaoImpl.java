package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPCardLoanInfoDao;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;

@Repository
public class APPCardLoanInfoDaoImpl extends BaseDaoImpl<APPCardLoanInfoEntity> implements APPCardLoanInfoDao{

	@Override
	public long updateAll(APPCardLoanInfoEntity cardLoanInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), cardLoanInfoEntity);
	}

}