package com.ymkj.cms.biz.service.master.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.impl.BMSParameterDaoImpl;
import com.ymkj.cms.biz.entity.master.BMSParameter;
import com.ymkj.cms.biz.service.master.IBMSParameterService;

@Service
public class BMSParameterServiceImpl   extends BaseServiceImpl<BMSParameter> implements IBMSParameterService{

	@Autowired
	private BMSParameterDaoImpl parameterDaoImpl;
	@Override
	protected BaseDao<BMSParameter> getDao() {
		// TODO Auto-generated method stub
		return parameterDaoImpl;
	}
	@Override
	public BMSParameter parameterByCondition(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
 
 

	
	
 

	 

	 

	 

 
 

	 

}
