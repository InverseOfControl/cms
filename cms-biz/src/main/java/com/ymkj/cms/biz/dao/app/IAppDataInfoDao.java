package com.ymkj.cms.biz.dao.app;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.app.AppDataInfoEntity;

public interface IAppDataInfoDao extends BaseDao<AppDataInfoEntity> {

	public List<Map<String, Object>> getStatusCountByUserCode(Map paramMap);
	
	
	public boolean isAppClaim(String loanNo);
	
}
