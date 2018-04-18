package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface LoanBaseRelaService extends BaseService<LoanBaseRelaEntity>{
  
	public Long saveOrUpdate(LoanBaseRelaEntity loanBaseRelaEntity);
	
 
	public LoanBaseRelaEntity getByBaseId(Long baseId);
}
	