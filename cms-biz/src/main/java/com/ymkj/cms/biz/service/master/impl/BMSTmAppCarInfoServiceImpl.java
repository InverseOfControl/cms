package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppCarInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppCarInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppCarInfoService;
@Service
public class BMSTmAppCarInfoServiceImpl extends BaseServiceImpl<BMSTmAppCarInfo> implements IBMSTmAppCarInfoService{

	@Autowired
	private IBMSTmAppCarInfoDao bmsTmAppCarInfoInfoDao;
	@Override
	protected BaseDao<BMSTmAppCarInfo> getDao() {
		return bmsTmAppCarInfoInfoDao;
	}
}
