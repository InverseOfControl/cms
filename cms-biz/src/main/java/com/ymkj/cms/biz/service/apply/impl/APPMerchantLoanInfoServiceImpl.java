package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPMerchantLoanInfoDao;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.service.apply.APPMerchantLoanInfoService;

@Service
public class APPMerchantLoanInfoServiceImpl extends BaseServiceImpl<APPMerchantLoanInfoEntity> implements APPMerchantLoanInfoService{
	
	@Autowired
	private APPMerchantLoanInfoDao appMerchantLoanInfoDao;
	
	@Override
	protected BaseDao<APPMerchantLoanInfoEntity> getDao() {
		return appMerchantLoanInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPMerchantLoanInfoEntity appMerchantLoanInfoEntity) {
		
		
		long id = 0;
		if(appMerchantLoanInfoEntity.getId() == null){
			long insert = appMerchantLoanInfoDao.insert(appMerchantLoanInfoEntity);
			if(insert > 0){
				id = appMerchantLoanInfoEntity.getId();
			}
		}else{
			 long update = appMerchantLoanInfoDao.update(appMerchantLoanInfoEntity);
			 if(update > 0){
				 id = appMerchantLoanInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPMerchantLoanInfoEntity> appMerchantLoanInfoEntitys) {
		
		long i = appMerchantLoanInfoDao.batchInsert(appMerchantLoanInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPMerchantLoanInfoEntity appMerchantLoanInfoEntity) {
		long i = appMerchantLoanInfoDao.updateAll(appMerchantLoanInfoEntity);
		return i;
	}

}
