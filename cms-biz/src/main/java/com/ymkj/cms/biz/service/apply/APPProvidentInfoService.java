package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPProvidentInfoService extends BaseService<APPProvidentInfoEntity>{
	
	public Long saveOrUpdate(APPProvidentInfoEntity appProvidentInfoEntity);
	
	public boolean saveList(List<APPProvidentInfoEntity> appProvidentInfoEntity);
	
	public Long updateAll(APPProvidentInfoEntity appProvidentInfoEntity);
}
	