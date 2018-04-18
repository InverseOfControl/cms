package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppEstateInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppEstateInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppEstateInfoService;
@Service
public class BMSTmAppEstateInfoServiceImpl extends BaseServiceImpl<BMSTmAppEstateInfo> implements IBMSTmAppEstateInfoService{

	@Autowired
	private IBMSTmAppEstateInfoDao bmsTmAppEstateInfoDao;
	@Override
	protected BaseDao<BMSTmAppEstateInfo> getDao() {
		return bmsTmAppEstateInfoDao;
	}
}
