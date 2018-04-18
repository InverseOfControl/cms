package com.ymkj.cms.biz.dao.app.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.dao.app.IAppDataInfoDao;
import com.ymkj.cms.biz.entity.app.AppDataInfoEntity;

@Repository
public class AppDataInfoDaoImpl extends BaseDaoImpl<AppDataInfoEntity> implements IAppDataInfoDao {
	@Override
	public List<Map<String, Object>> getStatusCountByUserCode(Map paramMap) {
		
		return  super.getSqlSession().selectList("getStatusCountByUserCode", paramMap);
	}

	@Override
	public boolean isAppClaim(String loanNo) {
		boolean isAppClaim =false;
		String appClaim=(String)super.getSqlSession().selectOne("isAppClaim", loanNo);
		if(EnumConstants.YES.equals(appClaim)){
			isAppClaim = true;
		}
		return isAppClaim; 
	}

}
