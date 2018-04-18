package com.ymkj.cms.biz.service.app;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.app.AppDataInfoEntity;


public interface IAppDataInfoService  extends BaseService<AppDataInfoEntity> {
	
	
	public List<Map<String, Object>> getStatusCountByUserCode(Map  paramMap);
	
	
	public boolean isAppClaim(String loanNo);

}
