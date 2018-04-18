package com.ymkj.cms.biz.service.master.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSPatchBoltDao;
import com.ymkj.cms.biz.entity.master.BMSPatchBolt;
import com.ymkj.cms.biz.service.master.IBMSPatchBoltService;
@Service
public class BMSPatchServiceImpl extends BaseServiceImpl<BMSPatchBolt> implements IBMSPatchBoltService{
	@Autowired
	private IBMSPatchBoltDao iBMSPatchBoltDao;
	@Override
	protected BaseDao<BMSPatchBolt> getDao() {
		// TODO Auto-generated method stub
		return iBMSPatchBoltDao;
	}
	/*@Override
	public List<BMSPatchBolt> listPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iBMSPatchBoltDao.listPage(map);
	}*/
}
