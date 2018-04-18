package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPMasterLoanInfoService extends BaseService<APPMasterLoanInfoEntity>{
	
	public Long saveOrUpdate(APPMasterLoanInfoEntity appMasterLoanInfoEntity);
	
	public boolean saveList(List<APPMasterLoanInfoEntity> appMasterLoanInfoEntity);
	
	
	public Long updateAll(APPMasterLoanInfoEntity appMasterLoanInfoEntity);
}
	