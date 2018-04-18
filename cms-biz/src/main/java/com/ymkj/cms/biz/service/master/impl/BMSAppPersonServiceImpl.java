package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonDao;
import com.ymkj.cms.biz.dao.master.IBMSBankDao;
import com.ymkj.cms.biz.entity.master.BMSAppPerson;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.service.master.IBMSAppPersonService;
import com.ymkj.cms.biz.service.master.IBMSBankService;

@Service
public class BMSAppPersonServiceImpl extends BaseServiceImpl<BMSAppPerson> implements IBMSAppPersonService{

	@Autowired
	private IBMSAppPersonDao bmsAppPersonDao;
	@Override
	protected BaseDao<BMSAppPerson> getDao() {
		return bmsAppPersonDao;
	}

}
