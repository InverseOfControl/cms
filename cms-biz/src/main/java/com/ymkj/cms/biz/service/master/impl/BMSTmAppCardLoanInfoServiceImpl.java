package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSTmAppCardLoanInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppContactHeadDao;
import com.ymkj.cms.biz.entity.master.BMSTmAppCardLoanInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactHead;
import com.ymkj.cms.biz.service.master.IBMSTmAppCardLoanInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactHeadService;
@Service
public class BMSTmAppCardLoanInfoServiceImpl extends BaseServiceImpl<BMSTmAppCardLoanInfo> implements IBMSTmAppCardLoanInfoService{

	@Autowired
	private IBMSTmAppCardLoanInfoDao bmsTmAppCardLoanInfoDao;
	
	@Override
	protected BaseDao<BMSTmAppCardLoanInfo> getDao() {
		return bmsTmAppCardLoanInfoDao;
	}

}
