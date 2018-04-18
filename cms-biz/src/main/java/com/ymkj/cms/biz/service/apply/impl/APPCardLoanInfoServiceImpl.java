package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPCardLoanInfoDao;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
import com.ymkj.cms.biz.service.apply.APPCardLoanInfoService;

@Service
public class APPCardLoanInfoServiceImpl extends BaseServiceImpl<APPCardLoanInfoEntity> implements APPCardLoanInfoService{
	
	@Autowired
	private APPCardLoanInfoDao appCardLoanInfoDao;
	
	@Override
	protected BaseDao<APPCardLoanInfoEntity> getDao() {
		return appCardLoanInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPCardLoanInfoEntity appCardLoanInfoEntity) {
		
		
		long id = 0;
		if(appCardLoanInfoEntity.getId() == null){
			long insert = appCardLoanInfoDao.insert(appCardLoanInfoEntity);
			if(insert > 0){
				id = appCardLoanInfoEntity.getId();
			}
		}else{
			 long update = appCardLoanInfoDao.update(appCardLoanInfoEntity);
			 if(update > 0){
				 id = appCardLoanInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPCardLoanInfoEntity> appCardLoanInfoEntitys) {
		
		long i = appCardLoanInfoDao.batchInsert(appCardLoanInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPCardLoanInfoEntity cardLoanInfoEntity) {
		long i = appCardLoanInfoDao.updateAll(cardLoanInfoEntity);
		return i;
	}

}
