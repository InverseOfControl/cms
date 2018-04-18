package com.ymkj.cms.biz.dao.master.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSDebtRadioDao;
import com.ymkj.cms.biz.entity.master.BMSDebtRadio;

@Repository
public class BMSDebtRadioDaoImpl extends BaseDaoImpl<BMSDebtRadio> implements IBMSDebtRadioDao {


	public BMSDebtRadio findDebtRadioById(BMSDebtRadio debtRadio) {
		
		return super.getSqlSession().selectOne(super.getStatement("findDebtRadioById"), debtRadio);
	}

	@Override
	public Integer testConnection(Request req) {
		return super.getSqlSession().selectOne(super.getStatement("testConnection"));
	}

}
