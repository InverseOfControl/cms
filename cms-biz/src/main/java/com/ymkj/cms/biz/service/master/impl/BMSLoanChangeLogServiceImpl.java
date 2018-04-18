package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanChangeLogDao;
import com.ymkj.cms.biz.entity.master.BMSLoanChangeLog;
import com.ymkj.cms.biz.service.master.IBMSLoanChangeLogService;
@Service
public class BMSLoanChangeLogServiceImpl extends BaseServiceImpl<BMSLoanChangeLog> implements IBMSLoanChangeLogService{

	@Autowired
	private IBMSLoanChangeLogDao bmsLoanChangeLogDao;
	@Override
	protected BaseDao<BMSLoanChangeLog> getDao() {
		return bmsLoanChangeLogDao;
	}

}
