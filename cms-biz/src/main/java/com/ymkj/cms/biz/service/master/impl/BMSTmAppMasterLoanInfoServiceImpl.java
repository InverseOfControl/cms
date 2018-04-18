package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppMasterLoanInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppMasterLoanInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppMasterLoanInfoService;
@Service
public class BMSTmAppMasterLoanInfoServiceImpl extends BaseServiceImpl<BMSTmAppMasterLoanInfo> implements IBMSTmAppMasterLoanInfoService{
	@Autowired
	private IBMSTmAppMasterLoanInfoDao bmsTmAppMasterLoanInfoDao;
	@Override
	protected BaseDao<BMSTmAppMasterLoanInfo> getDao() {
		return bmsTmAppMasterLoanInfoDao;
	}
}
