package com.ymkj.cms.biz.dao.master.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSReasonManagementDao;
import com.ymkj.cms.biz.entity.master.BMSReason;

@Repository
public class BMSReasonManagementDaoImpl extends BaseDaoImpl<BMSReason> implements IBMSReasonManagementDao{

	@Override
	public BMSReason findBMSReasonByCode(BMSReason bmsReason) {
	
		return super.getSqlSession().selectOne(super.getStatement("findBMSReasonByCode"), bmsReason);
	}

}
