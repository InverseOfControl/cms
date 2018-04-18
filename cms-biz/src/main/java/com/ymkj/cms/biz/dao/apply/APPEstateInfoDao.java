package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPEstateInfoDao extends BaseDao<APPEstateInfoEntity>{
	
	public long updateAll(APPEstateInfoEntity appEstateInfoEntity);
	
}
