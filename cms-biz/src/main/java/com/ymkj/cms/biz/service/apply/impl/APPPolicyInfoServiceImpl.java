package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPPolicyInfoDao;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
import com.ymkj.cms.biz.service.apply.APPPolicyInfoService;

@Service
public class APPPolicyInfoServiceImpl extends BaseServiceImpl<APPPolicyInfoEntity> implements APPPolicyInfoService{
	
	@Autowired
	private APPPolicyInfoDao appPolicyInfoDao;
	
	@Override
	protected BaseDao<APPPolicyInfoEntity> getDao() {
		return appPolicyInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPPolicyInfoEntity appPolicyInfoEntity) {
		
		
		long id = 0;
		if(appPolicyInfoEntity.getId() == null){
			long insert = appPolicyInfoDao.insert(appPolicyInfoEntity);
			if(insert > 0){
				id = appPolicyInfoEntity.getId();
			}
		}else{
			 long update = appPolicyInfoDao.update(appPolicyInfoEntity);
			 if(update > 0){
				 id = appPolicyInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPPolicyInfoEntity> appPolicyInfoEntitys) {
		
		long i = appPolicyInfoDao.batchInsert(appPolicyInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Long updateAll(APPPolicyInfoEntity appPolicyInfoEntity) {
		long i = appPolicyInfoDao.updateAll(appPolicyInfoEntity);
		return i;
	}

}
