package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPSalaryInfoDao;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;

@Repository
public class APPSalaryInfoDaoImpl extends BaseDaoImpl<APPSalaryLoanInfoEntity> implements APPSalaryInfoDao{

	@Override
	public long updateAll(APPSalaryLoanInfoEntity appSalaryInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appSalaryInfoEntity);
	}

}
