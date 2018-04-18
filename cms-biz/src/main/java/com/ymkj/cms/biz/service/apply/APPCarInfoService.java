package com.ymkj.cms.biz.service.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.APPPersonVauleAddresEntity;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPCarInfoService extends BaseService<APPCarInfoEntity>{
	
	public Long saveOrUpdate(APPCarInfoEntity appCarInfoEntity);
	
	public boolean saveList(List<APPCarInfoEntity> appCarInfoEntity);
	
	
	public Long updateAll(APPCarInfoEntity appCarInfoEntity);
	
	public APPPersonVauleAddresEntity queryPersonVauleAddresEntity(Map<String, Object> paramMap);
}
