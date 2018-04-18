package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSReasonReLinksDao;
import com.ymkj.cms.biz.entity.master.BMSReason;
import com.ymkj.cms.biz.service.master.IBMSReasonReLinksService;

@Service
public class BMSReasonReLinksServiceImpl extends BaseServiceImpl<BMSReason> implements IBMSReasonReLinksService {
	
	@Autowired
	private IBMSReasonReLinksDao iBMSReasonReLinksDao;

	@Override
	protected BaseDao<BMSReason> getDao() {
		return iBMSReasonReLinksDao;
	}

	@Override
	public BMSReason findBMSReLinksById(Map<String, Object> map) {
		
		return iBMSReasonReLinksDao.findBMSReLinksById(map);
	}

	/*@Override
	public List<BMSReason> findBMSReasonByVal(Map<String, Object> map) {
	
		return iBMSReasonReLinksDao.findBMSReasonByVal(map);
	}*/

	@Override
	public long update(BMSReason bmsReason) {
		
		return iBMSReasonReLinksDao.update(bmsReason);
	}

	@Override
	public Integer updateBMSReasonByVal(BMSReason bmsReason) {
		
		return iBMSReasonReLinksDao.updateBMSReasonByVal(bmsReason);
	}

	@Override
	public long insert(BMSReason bmsReason) {
	
		return iBMSReasonReLinksDao.insert(bmsReason);
	}

	@Override
	public   List<BMSReason> findByRelationCode(Map<String,Object> map) {
		
		return iBMSReasonReLinksDao.findByRelationCode(map);
	}

	@Override
	public Integer delReasonCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iBMSReasonReLinksDao.delReasonCode(map);
	}

	@Override
	public Integer updateBMSReasonIfShow(BMSReason bmsReason) {
		// TODO Auto-generated method stub
		return iBMSReasonReLinksDao.updateBMSReasonIfShow(bmsReason);
	}

}
