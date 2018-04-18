package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPProvidentInfoDao;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
import com.ymkj.cms.biz.service.apply.APPProvidentInfoService;

@Service
public class APPProvidentInfoServiceImpl extends BaseServiceImpl<APPProvidentInfoEntity> implements APPProvidentInfoService{
	
	@Autowired
	private APPProvidentInfoDao appProvidentInfoDao;
	
	@Override
	protected BaseDao<APPProvidentInfoEntity> getDao() {
		return appProvidentInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPProvidentInfoEntity appProvidentInfoEntity) {
		
		
		long id = 0;
		if(appProvidentInfoEntity.getId() == null){
			long insert = appProvidentInfoDao.insert(appProvidentInfoEntity);
			if(insert > 0){
				id = appProvidentInfoEntity.getId();
			}
		}else{
			 long update = appProvidentInfoDao.update(appProvidentInfoEntity);
			 if(update > 0){
				 id = appProvidentInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPProvidentInfoEntity> appProvidentInfoEntitys) {
		
		long i = appProvidentInfoDao.batchInsert(appProvidentInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPProvidentInfoEntity appProvidentInfoEntity) {
		long i = appProvidentInfoDao.updateAll(appProvidentInfoEntity);
		return i;
	}

}
