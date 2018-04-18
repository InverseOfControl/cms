package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSLoanBaseRelaDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanBaseRela;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSLoanBaseRelaService;
@Service
public class BMSLoanBaseRelaServiceImpl extends BaseServiceImpl<BMSLoanBaseRela> implements IBMSLoanBaseRelaService{

	@Autowired
	private IBMSLoanBaseRelaDao bmsLoanBaseRelaDao;
	@Override
	protected BaseDao<BMSLoanBaseRela> getDao() {
		return bmsLoanBaseRelaDao;
	}
}
