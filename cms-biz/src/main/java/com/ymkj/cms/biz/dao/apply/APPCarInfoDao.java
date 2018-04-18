package com.ymkj.cms.biz.dao.apply;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.APPPersonVauleAddresEntity;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPCarInfoDao extends BaseDao<APPCarInfoEntity>{
	
	public long updateAll(APPCarInfoEntity appCarInfoEntity);
	
	public APPPersonVauleAddresEntity queryPersonVauleAddresEntity(Map<String, Object> paramMap);
	
}
