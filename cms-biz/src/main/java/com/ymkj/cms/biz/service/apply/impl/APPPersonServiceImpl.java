package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPPersonDao;
import com.ymkj.cms.biz.entity.apply.APPPersonEntity;
import com.ymkj.cms.biz.service.apply.APPPersonService;

@Service
public class APPPersonServiceImpl extends BaseServiceImpl<APPPersonEntity> implements APPPersonService{
	
	@Autowired
	private APPPersonDao appPersonDao;
	
	@Override
	protected BaseDao<APPPersonEntity> getDao() {
		return appPersonDao;
	}

	@Override
	public Long saveOrUpdate(APPPersonEntity appPersonEntity) {
		
		
		long id = 0;
		if(appPersonEntity.getId() == null){
			long insert = appPersonDao.insert(appPersonEntity);
			if(insert > 0){
				id = appPersonEntity.getId();
			}
		}else{
			 long update = appPersonDao.update(appPersonEntity);
			 if(update > 0){
				 id = appPersonEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPPersonEntity> appPersonEntitys) {
		
		long i = appPersonDao.batchInsert(appPersonEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

}
