package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPSalaryInfoDao extends BaseDao<APPSalaryLoanInfoEntity>{
	
	public long updateAll(APPSalaryLoanInfoEntity appSalaryInfoEntity);
	
}
