package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPCardLoanInfoDao extends BaseDao<APPCardLoanInfoEntity>{
	
	public long updateAll(APPCardLoanInfoEntity cardLoanInfoEntity); 
	
}
