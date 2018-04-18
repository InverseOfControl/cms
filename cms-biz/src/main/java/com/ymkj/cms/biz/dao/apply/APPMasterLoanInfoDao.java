package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPMasterLoanInfoDao extends BaseDao<APPMasterLoanInfoEntity>{
	
	public long updateAll(APPMasterLoanInfoEntity appMasterLoanInfoEntity);
	
}
