package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPEstateInfoService extends BaseService<APPEstateInfoEntity>{
	
	public Long saveOrUpdate(APPEstateInfoEntity appEstateInfoEntity);
	
	public boolean saveList(List<APPEstateInfoEntity> appEstateInfoEntity);
	
	public Long updateAll(APPEstateInfoEntity appEstateInfoEntity);
}
	