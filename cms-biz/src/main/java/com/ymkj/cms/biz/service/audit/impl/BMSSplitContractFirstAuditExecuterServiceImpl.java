package com.ymkj.cms.biz.service.audit.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
import com.ymkj.cms.biz.service.audit.BMSSplitContractFirstAuditExecuterService;
@Service
public class BMSSplitContractFirstAuditExecuterServiceImpl extends BaseServiceImpl<BMSFirstAuditEntity> implements BMSSplitContractFirstAuditExecuterService{

	@Override
	public List<BMSAutomaticDispatchEntity> automaticDispatchList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseDao<BMSFirstAuditEntity> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
