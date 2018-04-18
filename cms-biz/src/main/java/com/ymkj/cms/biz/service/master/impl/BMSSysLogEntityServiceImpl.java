package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSSysLogEntityDao;
import com.ymkj.cms.biz.entity.master.BMSSysLogEntity;
import com.ymkj.cms.biz.service.master.IBMSSysLogEntityService;
@Service
public class BMSSysLogEntityServiceImpl extends BaseServiceImpl<BMSSysLogEntity> implements IBMSSysLogEntityService{

	@Autowired
	private IBMSSysLogEntityDao bmsSysLogEntityDao;
	@Override
	protected BaseDao<BMSSysLogEntity> getDao() {
		return bmsSysLogEntityDao;
	}
}
