package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.impl.BMSRejectReasonDaoImpl;
import com.ymkj.cms.biz.entity.master.BMSRejectReason;
import com.ymkj.cms.biz.service.master.IBMSRejectReasonService;

@Service
public class BMSRejectReasonServiceImpl   extends BaseServiceImpl<BMSRejectReason> implements IBMSRejectReasonService{

	@Autowired
	private BMSRejectReasonDaoImpl rejectReasonDao;
	@Override
	protected BaseDao<BMSRejectReason> getDao() {
		// TODO Auto-generated method stub
		return rejectReasonDao;
	}
	@Override
	public List<BMSRejectReason> listByCondition(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BMSRejectReason RejectReasonByCondition(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
 

	
	
 

	 

	 

	 

 
 

	 

}
