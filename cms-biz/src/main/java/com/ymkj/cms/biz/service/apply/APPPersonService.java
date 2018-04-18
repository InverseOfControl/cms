package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPPersonService extends BaseService<APPPersonEntity>{
	
	public Long saveOrUpdate(APPPersonEntity appPersonEntity);
	
	public boolean saveList(List<APPPersonEntity> appPersonEntity);
}
	