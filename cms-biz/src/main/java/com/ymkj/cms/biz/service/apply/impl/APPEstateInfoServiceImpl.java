package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPEstateInfoDao;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.service.apply.APPEstateInfoService;

@Service
public class APPEstateInfoServiceImpl extends BaseServiceImpl<APPEstateInfoEntity> implements APPEstateInfoService{
	
	@Autowired
	private APPEstateInfoDao appEstateInfoDao;
	
	@Override
	protected BaseDao<APPEstateInfoEntity> getDao() {
		return appEstateInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPEstateInfoEntity appEstateInfoEntity) {
		
		
		long id = 0;
		if(appEstateInfoEntity.getId() == null){
			long insert = appEstateInfoDao.insert(appEstateInfoEntity);
			if(insert > 0){
				id = appEstateInfoEntity.getId();
			}
		}else{
			 long update = appEstateInfoDao.update(appEstateInfoEntity);
			 if(update > 0){
				 id = appEstateInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPEstateInfoEntity> appEstateInfoEntitys) {
		
		long i = appEstateInfoDao.batchInsert(appEstateInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPEstateInfoEntity appEstateInfoEntity) {
		long i = appEstateInfoDao.updateAll(appEstateInfoEntity);
		return i;
	}

}
