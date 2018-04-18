package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSContractChannelDao;
import com.ymkj.cms.biz.entity.master.BMSContractChannel;
import com.ymkj.cms.biz.service.master.IBMSContractChannelService;


@Service
public class BMSContractChannelServiceImpl extends BaseServiceImpl<BMSContractChannel> implements IBMSContractChannelService{
	
	@Autowired
	private IBMSContractChannelDao bmsContractChannelDao;

	@Override
	protected BaseDao<BMSContractChannel> getDao() {
		return bmsContractChannelDao;
	}

	
}
