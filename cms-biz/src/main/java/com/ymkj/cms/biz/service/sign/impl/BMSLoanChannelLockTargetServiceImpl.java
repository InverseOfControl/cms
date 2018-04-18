package com.ymkj.cms.biz.service.sign.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.sign.IBMSLoanChannelLockTargetDao;
import com.ymkj.cms.biz.dao.sign.ILuJSInformDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanChannelLockTargetEntity;
import com.ymkj.cms.biz.entity.sign.BMSLuJSInform;
import com.ymkj.cms.biz.service.sign.IBMSLoanChannelLockTargetService;
import com.ymkj.cms.biz.service.sign.ILuJSInformService;

@Service
public class BMSLoanChannelLockTargetServiceImpl  extends BaseServiceImpl<BMSLoanChannelLockTargetEntity> implements IBMSLoanChannelLockTargetService{
	@Autowired
	private IBMSLoanChannelLockTargetDao loanChannelLockTargetDao;
	
	
	@Override
	protected BaseDao<BMSLoanChannelLockTargetEntity> getDao() {

		return loanChannelLockTargetDao;
	}

}
