package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPCarInfoDao;
import com.ymkj.cms.biz.entity.APPPersonVauleAddresEntity;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.service.apply.APPCarInfoService;

@Service
public class APPCarInfoServiceImpl extends BaseServiceImpl<APPCarInfoEntity> implements APPCarInfoService{
	
	@Autowired
	private APPCarInfoDao appCarInfoDao;
	
	@Override
	protected BaseDao<APPCarInfoEntity> getDao() {
		return appCarInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPCarInfoEntity appCarInfoEntity) {
		
		
		long id = 0;
		if(appCarInfoEntity.getId() == null){
			long insert = appCarInfoDao.insert(appCarInfoEntity);
			if(insert > 0){
				id = appCarInfoEntity.getId();
			}
		}else{
			 long update = appCarInfoDao.update(appCarInfoEntity);
			 if(update > 0){
				 id = appCarInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPCarInfoEntity> appCarInfoEntitys) {
		
		long i = appCarInfoDao.batchInsert(appCarInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPCarInfoEntity appCarInfoEntity) {
		long i = appCarInfoDao.updateAll(appCarInfoEntity);
		return i;
	}

	@Override
	public APPPersonVauleAddresEntity queryPersonVauleAddresEntity(Map<String, Object> paramMap) {
		
		return appCarInfoDao.queryPersonVauleAddresEntity(paramMap);
	}

}
