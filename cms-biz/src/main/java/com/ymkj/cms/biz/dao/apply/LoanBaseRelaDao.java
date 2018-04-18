package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface LoanBaseRelaDao extends BaseDao<LoanBaseRelaEntity>{
	
	public LoanBaseRelaEntity getByBaseId(Long baseId);
	
}
