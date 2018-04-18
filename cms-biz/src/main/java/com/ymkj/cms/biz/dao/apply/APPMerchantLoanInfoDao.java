package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPMerchantLoanInfoDao extends BaseDao<APPMerchantLoanInfoEntity>{
	public long updateAll(APPMerchantLoanInfoEntity appMerchantLoanInfoEntity);
}
