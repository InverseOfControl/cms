package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSTMReasonDao;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;
import com.ymkj.cms.biz.service.master.IBMSTMReasonService;

@Service
public class BMSTMReasonServiceImpl extends BaseServiceImpl<BMSTMReasonEntity> implements IBMSTMReasonService{

	@Autowired
	private IBMSTMReasonDao iBMSTMReasonDao;
	
	@Override
	protected BaseDao<BMSTMReasonEntity> getDao() {
		return iBMSTMReasonDao;
	}

	@Override
	public List<BMSTMReasonEntity> twoLevelparents(Map<String, Object> paramMap) {
		return iBMSTMReasonDao.twoLevelparents(paramMap);
	}

	@Override
	public BMSTMReasonEntity getByParam(Map<String, Object> reasonParamMap) {
		return iBMSTMReasonDao.getByParam(reasonParamMap);
	}

	@Override
	public List<BMSTMReasonEntity> findFirstReasonByOperType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iBMSTMReasonDao.findFirstReasonByOperType(map);
	}

	@Override
	public List<BMSTMReasonEntity> findSecondReasonByOperType(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iBMSTMReasonDao.findSecondReasonByOperType(map);
	}

	/*@Override
	public List<BMSTMReasonEntity> findReasonByOperType(Map<String, Object> reasonParamMap) {
		
		return iBMSTMReasonDao.findReasonByOperType(reasonParamMap);
	}*/

}
