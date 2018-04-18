package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppProvidentInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppProvidentInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppProvidentInfoService;
@Service
public class BMSTmAppProvidentInfoServiceImpl extends BaseServiceImpl<BMSTmAppProvidentInfo> implements IBMSTmAppProvidentInfoService{
	@Autowired
	private IBMSTmAppProvidentInfoDao bmsTmAppProvidentInfoDao;
	@Override
	protected BaseDao<BMSTmAppProvidentInfo> getDao() {
		return bmsTmAppProvidentInfoDao;
	}
}
