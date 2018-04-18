package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.service.apply.APPPersonInfoService;

@Service
public class APPPersonInfoServiceImpl extends BaseServiceImpl<APPPersonInfoEntity> implements APPPersonInfoService{
	
	@Autowired
	private APPPersonInfoDao appPersonInfoDao;
	
	@Override
	protected BaseDao<APPPersonInfoEntity> getDao() {
		return appPersonInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPPersonInfoEntity appPersonInfoEntity) {
		
		
		long id = 0;
		if(appPersonInfoEntity.getId() == null){
			long insert = appPersonInfoDao.insert(appPersonInfoEntity);
			if(insert > 0){
				id = appPersonInfoEntity.getId();
			}
		}else{
			long update=0L;
			if(("NFC").equals(appPersonInfoEntity.getFlag()) || ("REP").equals(appPersonInfoEntity.getFlag()) ){
				  update = appPersonInfoDao.updateById(appPersonInfoEntity);
			}else{
				 update=appPersonInfoDao.update(appPersonInfoEntity);
			}
			
			 if(update > 0){
				 id = appPersonInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPPersonInfoEntity> appPersonInfoEntitys) {
		
		long i = appPersonInfoDao.batchInsert(appPersonInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPPersonInfoEntity appPersonInfoEntity) {
		long i = appPersonInfoDao.updateAll(appPersonInfoEntity);
		return i;
	}

	@Override
	public Long updateUserPhone(APPPersonInfoEntity appPersonInfoEntity) {
		long i = appPersonInfoDao.updateUserPhone(appPersonInfoEntity);
		return i;
	}

}
