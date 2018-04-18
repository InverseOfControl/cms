package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSDebtRadioDao;
import com.ymkj.cms.biz.entity.master.BMSDebtRadio;
import com.ymkj.cms.biz.service.master.IBMSDebtRadioService;

@Service
public class BMSDebtRadionServiceImpl extends BaseServiceImpl<BMSDebtRadio> implements IBMSDebtRadioService{
	
	@Autowired
	private IBMSDebtRadioDao ibmsDebtRadioDao;

	@Override
	protected BaseDao<BMSDebtRadio> getDao() {
	
		return ibmsDebtRadioDao;
	}

	@Override
	public boolean updateById(BMSDebtRadio debtDadio) {
		long result=ibmsDebtRadioDao.update(debtDadio);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public BMSDebtRadio findDebtRadioById(BMSDebtRadio debtRadio) {
		
		return ibmsDebtRadioDao.findDebtRadioById(debtRadio);
	}
	
	@Override
	public Integer testConnection(Request req) {

		return ibmsDebtRadioDao.testConnection(req);
	}

}
