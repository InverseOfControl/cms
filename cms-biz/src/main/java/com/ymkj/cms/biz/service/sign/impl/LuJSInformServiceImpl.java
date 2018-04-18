package com.ymkj.cms.biz.service.sign.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.sign.ILuJSInformDao;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;

@Service
public class LuJSInformServiceImpl  extends BaseServiceImpl<BMSLuJSInform> implements ILuJSInformService{
	@Autowired
	private ILuJSInformDao iLuJSInformDao;
	
	
	@Override
	protected BaseDao<BMSLuJSInform> getDao() {

		return iLuJSInformDao;
	}

}
