package com.ymkj.cms.biz.service.app.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.app.IAppDataInfoDao;
import com.ymkj.cms.biz.entity.app.AppDataInfoEntity;
import com.ymkj.cms.biz.service.app.IAppDataInfoService;
@Service
public class IAppDataInfoServiceImpl extends BaseServiceImpl<AppDataInfoEntity> implements IAppDataInfoService{
	@Autowired
	private IAppDataInfoDao appDataInfoDao;
	
	@Override
	protected BaseDao<AppDataInfoEntity> getDao() {
		
		return appDataInfoDao;
	}

	@Override
	public List<Map<String, Object>> getStatusCountByUserCode(Map pramMap) {
		return appDataInfoDao.getStatusCountByUserCode(pramMap);
	}

	@Override
	public boolean isAppClaim(String loanNo) {

		return appDataInfoDao.isAppClaim(loanNo);
	}

}
