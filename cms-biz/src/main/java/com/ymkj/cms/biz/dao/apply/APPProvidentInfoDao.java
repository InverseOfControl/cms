package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPProvidentInfoDao extends BaseDao<APPProvidentInfoEntity>{
	
	public long updateAll(APPProvidentInfoEntity appProvidentInfoEntity);
	
}
