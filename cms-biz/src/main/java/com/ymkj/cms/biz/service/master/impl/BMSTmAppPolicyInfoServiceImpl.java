package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppPolicyInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppPolicyInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppPolicyInfoService;
@Service
public class BMSTmAppPolicyInfoServiceImpl extends BaseServiceImpl<BMSTmAppPolicyInfo> implements IBMSTmAppPolicyInfoService{

	@Autowired
	private IBMSTmAppPolicyInfoDao bmsTmAppPolicyInfoDao;
	@Override
	protected BaseDao<BMSTmAppPolicyInfo> getDao() {
		return bmsTmAppPolicyInfoDao;
	}
}
