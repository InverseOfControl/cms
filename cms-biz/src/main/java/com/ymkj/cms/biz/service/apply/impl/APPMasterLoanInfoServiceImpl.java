package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPMasterLoanInfoDao;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
import com.ymkj.cms.biz.service.apply.APPMasterLoanInfoService;

@Service
public class APPMasterLoanInfoServiceImpl extends BaseServiceImpl<APPMasterLoanInfoEntity> implements APPMasterLoanInfoService{
	
	@Autowired
	private APPMasterLoanInfoDao appMasterLoanInfoDao;
	
	@Override
	protected BaseDao<APPMasterLoanInfoEntity> getDao() {
		return appMasterLoanInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPMasterLoanInfoEntity appMasterLoanInfoEntity) {
		
		
		long id = 0;
		if(appMasterLoanInfoEntity.getId() == null){
			long insert = appMasterLoanInfoDao.insert(appMasterLoanInfoEntity);
			if(insert > 0){
				id = appMasterLoanInfoEntity.getId();
			}
		}else{
			 long update = appMasterLoanInfoDao.update(appMasterLoanInfoEntity);
			 if(update > 0){
				 id = appMasterLoanInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPMasterLoanInfoEntity> appMasterLoanInfoEntitys) {
		
		long i = appMasterLoanInfoDao.batchInsert(appMasterLoanInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPMasterLoanInfoEntity appMasterLoanInfoEntity) {
		long i = appMasterLoanInfoDao.updateAll(appMasterLoanInfoEntity);
		return i;
	}

}
