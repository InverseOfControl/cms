package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPWhitePersonnelDao;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;
import com.ymkj.cms.biz.service.apply.APPWhitePersonnelService;

@Service
public class APPWhitePersonnelEntityServiceImpl extends BaseServiceImpl<APPWhitePersonnelEntity> implements APPWhitePersonnelService{
	
	@Autowired
	private APPWhitePersonnelDao appWhitePersonnelDao;

	@Override
	protected BaseDao<APPWhitePersonnelEntity> getDao() {
		return appWhitePersonnelDao;
	}

	@Override
	public int deleteVo(APPWhitePersonnelEntity appWhitePersonnelEntity) {
		return appWhitePersonnelDao.deleteVo(appWhitePersonnelEntity);
	}

	@Override
	public boolean saveList(List<APPWhitePersonnelEntity> appWhitePersonnelEntityBeans) {
		long i = appWhitePersonnelDao.batchInsert(appWhitePersonnelEntityBeans);
		if(i > 0){
			return true;
		}
		return false;
	}

}
