package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPCardLoanInfoService extends BaseService<APPCardLoanInfoEntity>{
	
	public Long saveOrUpdate(APPCardLoanInfoEntity cardLoanInfoEntity);
	
	public boolean saveList(List<APPCardLoanInfoEntity> cardLoanInfoEntitys);
	
	public Long updateAll(APPCardLoanInfoEntity cardLoanInfoEntity);
}
