package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppSalaryLoanInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppSalaryLoanInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppSalaryLoanInfoService;
@Service
public class BMSTmAppSalaryLoanInfoServiceImpl extends BaseServiceImpl<BMSTmAppSalaryLoanInfo> implements IBMSTmAppSalaryLoanInfoService{
	@Autowired
	private IBMSTmAppSalaryLoanInfoDao bmsTmAppSalaryLoanInfoDao;
	@Override
	protected BaseDao<BMSTmAppSalaryLoanInfo> getDao() {
		return bmsTmAppSalaryLoanInfoDao;
	}
}
