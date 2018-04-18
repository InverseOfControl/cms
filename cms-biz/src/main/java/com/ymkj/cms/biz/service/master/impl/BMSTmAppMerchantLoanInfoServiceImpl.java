package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppMerchantLoanInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppMerchantLoanInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppMerchantLoanInfoService;
@Service
public class BMSTmAppMerchantLoanInfoServiceImpl extends BaseServiceImpl<BMSTmAppMerchantLoanInfo> implements IBMSTmAppMerchantLoanInfoService{
	@Autowired
	private IBMSTmAppMerchantLoanInfoDao bmsTmAppMerchantLoanInfoDao;
	@Override
	protected BaseDao<BMSTmAppMerchantLoanInfo> getDao() {
		return bmsTmAppMerchantLoanInfoDao;
	}
}
