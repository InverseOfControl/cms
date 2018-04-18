package com.ymkj.cms.biz.service.master.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSReasonManageDao;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.service.master.IBMSReasonManageService;

@Service
public class BMSReasonManageServiceImpl extends BaseServiceImpl<BMSReason> implements IBMSReasonManageService{
	
	@Autowired 
    private IBMSReasonManageDao bmsReasonManageDao;
	
	
	@Override
	protected BaseDao<BMSReason> getDao() {
		return bmsReasonManageDao;
	}
	
	@Override
	public List<BMSReason>  findBMSReasonByValue(Map<String, Object> map) {
		return bmsReasonManageDao.findBMSReasonByValue(map);
	}
	
	@Override
	public long insert(BMSReason bmsReason) {
		return bmsReasonManageDao.insert(bmsReason);
	}
	
	@Override
	public long update(BMSReason bmsReason) {
		return bmsReasonManageDao.update(bmsReason);
	}
	
	@Override
	public Integer findMaxId() {
		return bmsReasonManageDao.findMaxId();
	}
	
	@Override
	public BMSReason findReasonByPId(Long parentId) {
		return bmsReasonManageDao.findReasonByPId(parentId);
	}
	
	@Override
	public Integer updateById(BMSReason reason) {
		return bmsReasonManageDao.updateById(reason);
	}
	
	@Override
	public List<BMSReason> findReasonExport(Map<String,Object> map) {
		return bmsReasonManageDao.findReasonExport(map);
	}
	
	@Override
	public List<BMSReason> findReasonByParame(Map<String, Object> map) {
	   return bmsReasonManageDao.findReasonByParame(map);
	}

}
