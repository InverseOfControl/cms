package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPSalaryInfoDao;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
import com.ymkj.cms.biz.service.apply.APPSalaryInfoService;

@Service
public class APPSalaryInfoServiceImpl extends BaseServiceImpl<APPSalaryLoanInfoEntity> implements APPSalaryInfoService{
	
	@Autowired
	private APPSalaryInfoDao appSalaryInfoDao;

	@Override
	protected BaseDao<APPSalaryLoanInfoEntity> getDao() {
		return appSalaryInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPSalaryLoanInfoEntity appSalaryInfoEntity) {
		
		
		long id = 0;
		if(appSalaryInfoEntity.getId() == null){
			long insert = appSalaryInfoDao.insert(appSalaryInfoEntity);
			if(insert > 0){
				id = appSalaryInfoEntity.getId();
			}
		}else{
			 long update = appSalaryInfoDao.update(appSalaryInfoEntity);
			 if(update > 0){
				 id = appSalaryInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPSalaryLoanInfoEntity> appSalaryInfoEntitys) {
		
		long i = appSalaryInfoDao.batchInsert(appSalaryInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPSalaryLoanInfoEntity appSalaryInfoEntity) {
		long i = appSalaryInfoDao.updateAll(appSalaryInfoEntity);
		return i;
	}

}
