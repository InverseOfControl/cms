package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPPolicyInfoDao extends BaseDao<APPPolicyInfoEntity>{
	
	public long updateAll(APPPolicyInfoEntity appPolicyInfoEntity);
	
}
