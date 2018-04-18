package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPMerchantLoanInfoService extends BaseService<APPMerchantLoanInfoEntity>{
	
	public Long saveOrUpdate(APPMerchantLoanInfoEntity appMerchantLoanInfoEntity);
	
	public boolean saveList(List<APPMerchantLoanInfoEntity> appMerchantLoanInfoEntity);
	
	public Long updateAll(APPMerchantLoanInfoEntity appMerchantLoanInfoEntity);
}
	