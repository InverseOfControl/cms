package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPSalaryInfoService extends BaseService<APPSalaryLoanInfoEntity>{
	
	public Long saveOrUpdate(APPSalaryLoanInfoEntity appSalaryInfoEntity);
	
	public boolean saveList(List<APPSalaryLoanInfoEntity> appSalaryInfoEntity);
	
	public Long updateAll(APPSalaryLoanInfoEntity appSalaryInfoEntity);
}
	