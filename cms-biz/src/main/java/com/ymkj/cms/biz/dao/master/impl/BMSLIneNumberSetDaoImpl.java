package com.ymkj.cms.biz.dao.master.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSLineNumberSetDao;
import com.ymkj.cms.biz.entity.master.BMSLoanBank;
import com.ymkj.cms.biz.entity.master.BMSLoanBankExt;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDic;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDicEntity;

@Repository
public class BMSLIneNumberSetDaoImpl extends BaseDaoImpl<BMSOffLineOfferBankDic> implements IBMSLineNumberSetDao{

	@Override
	public Integer findByRegionAndCode(Map<String,Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("findByRegionAndCode"), map);
	}

	@Override
	public BMSLoanBank findLoanBank(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("findLoanBank"), map);
	}

	@Override
	public BMSLoanBankExt findLoanBankExtByBankId(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("findLoanBankExtByBankId"), map);
	}

	@Override
	public BMSOffLineOfferBankDicEntity findByRegionAndCodeData(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("findByRegionAndCodeData"), map);
	}

	@Override
	public Integer insertLoanBankExt(BMSLoanBankExt bmsLoanBankExt) {
		return super.getSqlSession().insert(super.getStatement("insertLoanBankExt"), bmsLoanBankExt);
	}

	@Override
	public Integer updateLoanBankExt(BMSLoanBankExt bmsLoanBankExt) {
		return super.getSqlSession().update(super.getStatement("updateLoanBankExt"), bmsLoanBankExt);
	}

	@Override
	public Integer insertLineOfferBankDic(BMSOffLineOfferBankDicEntity entity) {
		return super.getSqlSession().insert(super.getStatement("insertLineOfferBankDic"), entity);
	}

	@Override
	public Integer updateLineOfferBankDic(BMSOffLineOfferBankDicEntity entity) {
		return super.getSqlSession().update(super.getStatement("updateLineOfferBankDic"), entity);
	}

}
