package com.ymkj.cms.biz.service.audit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.audit.BMSAPPHistoryDao;
import com.ymkj.cms.biz.dao.master.IBMSBaseAreaDao;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;
import com.ymkj.cms.biz.service.audit.BMSAPPHistoryService;
import com.ymkj.cms.biz.service.master.IBMSBaseAreaService;

@Service
public class BMSAPPHistoryServiceImpl extends BaseServiceImpl<BMSAPPHistoryEntity> implements BMSAPPHistoryService{
	
	@Autowired
	private BMSAPPHistoryDao historyDao;
	
	@Override
	protected BaseDao<BMSAPPHistoryEntity> getDao() {
		return historyDao;
	}

}
