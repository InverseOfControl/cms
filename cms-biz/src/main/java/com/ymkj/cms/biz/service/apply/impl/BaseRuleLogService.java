package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.BaseRuleLogDao;
import com.ymkj.cms.biz.entity.apply.BmsRuleLogEntity;
import com.ymkj.cms.biz.service.apply.IBaseRuleLogService;

@Service
public class BaseRuleLogService extends BaseServiceImpl<BmsRuleLogEntity> implements IBaseRuleLogService{

	
	@Autowired
	private BaseRuleLogDao baseRuleLogDao;
	
	@Override
	protected BaseDao<BmsRuleLogEntity> getDao() {
		return baseRuleLogDao;
	}
	
	

	@Override
	public long insetBaseRuleLogList(List<BmsRuleLogEntity> listBmsRuleLog) {
		return baseRuleLogDao.batchInsert(listBmsRuleLog);
	}

}
