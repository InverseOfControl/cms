package com.ymkj.cms.biz.service.apply.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPContactHeadDao;
import com.ymkj.cms.biz.entity.apply.AppContactHeadEntity;
import com.ymkj.cms.biz.service.apply.APPContactHeadService;


@Service
public class APPContactHeadServiceImpl  extends BaseServiceImpl<AppContactHeadEntity> implements APPContactHeadService{

	@Autowired
	private APPContactHeadDao appContactHeadDao;
	
	@Override
	protected BaseDao<AppContactHeadEntity> getDao() {
		return appContactHeadDao;
	}

	@Override
	public Long saveOrUpdate(AppContactHeadEntity appPersonEntity) {
		long id = 0;
		
		if(appPersonEntity.getId() == null){
			long insert = appContactHeadDao.insert(appPersonEntity);
			if(insert > 0){
				id = appPersonEntity.getId();
			}
		}else{
			 long update = appContactHeadDao.update(appPersonEntity);
			 if(update > 0){
				 id = appPersonEntity.getId();
			 }
		}
		
		return id;
	}

}
