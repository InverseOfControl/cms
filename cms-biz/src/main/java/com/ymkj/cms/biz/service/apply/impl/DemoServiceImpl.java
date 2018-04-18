package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.IDemoDao;
import com.ymkj.cms.biz.entity.apply.DemoEntity;
import com.ymkj.cms.biz.service.apply.IDemoService;

@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoEntity> implements IDemoService{
	
	@Autowired
	private IDemoDao demoDao;
	
	@Override
	protected BaseDao<DemoEntity> getDao() {
		return demoDao;
	}

	@Override
	public Long saveOrUpdate(DemoEntity demoEntity) {
		
		
		long id = 0;
		if(demoEntity.getId() == null){
			long insert = demoDao.insert(demoEntity);
			if(insert > 0){
				id = demoEntity.getId();
			}
		}else{
			 long update = demoDao.update(demoEntity);
			 if(update > 0){
				 id = demoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveDemos(List<DemoEntity> demoEntitys) {
		
		long i = demoDao.batchInsert(demoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

}
