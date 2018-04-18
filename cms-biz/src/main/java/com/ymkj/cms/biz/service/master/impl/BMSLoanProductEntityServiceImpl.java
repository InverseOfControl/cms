package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanProductEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanProduct;
import com.ymkj.cms.biz.service.master.IBMSLoanProductEntityService;

@Service
public class BMSLoanProductEntityServiceImpl extends BaseServiceImpl<BMSLoanProduct> implements IBMSLoanProductEntityService{

	@Autowired
	private IBMSLoanProductEntityDao bmsLoanProductEntityDao;
	
	@Override
	protected BaseDao<BMSLoanProduct> getDao() {
		return bmsLoanProductEntityDao;
	}

}
