package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSBaseAreaDao;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;
import com.ymkj.cms.biz.service.master.IBMSBaseAreaService;

@Service
public class BMSBaseAreaServiceImpl extends BaseServiceImpl<BMSBaseArea> implements IBMSBaseAreaService{
	
	@Autowired
	private IBMSBaseAreaDao bmsBaseAreaDao;
	
	@Override
	protected BaseDao<BMSBaseArea> getDao() {
		return bmsBaseAreaDao;
	}

	@Override
	public void deletelAll(BMSBaseArea bmsBaseArea) {
		this.bmsBaseAreaDao.deletelAll(bmsBaseArea);
	}

	@Override
	public long insert(BMSBaseArea bmsBaseArea) {
		return bmsBaseAreaDao.insert(bmsBaseArea);
	}

	@Override
	public long deleteById(long id) {
		return bmsBaseAreaDao.deleteById(id);
	}


}
