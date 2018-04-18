package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPPersonInfoService extends BaseService<APPPersonInfoEntity>{
	
	public Long saveOrUpdate(APPPersonInfoEntity appPersonInfoEntity);
	
	public boolean saveList(List<APPPersonInfoEntity> appPersonInfoEntity);
	
	public Long updateAll(APPPersonInfoEntity appPersonInfoEntity);
	
	public Long updateUserPhone(APPPersonInfoEntity appPersonInfoEntity);
}
	