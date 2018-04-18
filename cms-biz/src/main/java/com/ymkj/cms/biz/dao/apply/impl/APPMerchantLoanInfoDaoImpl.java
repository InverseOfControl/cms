package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPMerchantLoanInfoDao;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;

@Repository
public class APPMerchantLoanInfoDaoImpl extends BaseDaoImpl<APPMerchantLoanInfoEntity> implements APPMerchantLoanInfoDao{

	@Override
	public long updateAll(APPMerchantLoanInfoEntity appMerchantLoanInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appMerchantLoanInfoEntity);
	}

}
