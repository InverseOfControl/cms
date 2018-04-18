package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPPolicyInfoService extends BaseService<APPPolicyInfoEntity>{
	
	public Long saveOrUpdate(APPPolicyInfoEntity appPolicyInfoEntity);
	
	public boolean saveList(List<APPPolicyInfoEntity> appPolicyInfoEntity);
	
	public Long updateAll(APPPolicyInfoEntity appPolicyInfoEntity);
}

	