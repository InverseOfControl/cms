package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSReasonManagementDao;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.service.master.IBMSReasonManagementService;

@Service
public class BMSReasonManagementServiceImpl extends BaseServiceImpl<BMSReason> implements IBMSReasonManagementService{

	@Autowired
	private IBMSReasonManagementDao iBMSReasonManagementDao;
	
	@Override
	protected BaseDao<BMSReason> getDao() {
		return iBMSReasonManagementDao;
	}

	@Override
	public long insert(BMSReason bmsReason) {
		return iBMSReasonManagementDao.insert(bmsReason);
	}

	@Override
	public long update(BMSReason bmsReason) {
		return iBMSReasonManagementDao.update(bmsReason);
	}

	@Override
	public BMSReason findBMSReasonByCode(BMSReason bmsReason) {
		
		return iBMSReasonManagementDao.findBMSReasonByCode(bmsReason);
	}

	

}
