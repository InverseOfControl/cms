package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPMasterLoanInfoDao;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;

@Repository
public class APPMasterLoanInfoDaoImpl extends BaseDaoImpl<APPMasterLoanInfoEntity> implements APPMasterLoanInfoDao{

	@Override
	public long updateAll(APPMasterLoanInfoEntity appMasterLoanInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appMasterLoanInfoEntity);
	}

}
