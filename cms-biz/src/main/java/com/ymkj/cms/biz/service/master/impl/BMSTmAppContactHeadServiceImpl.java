package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppContactHeadDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactHead;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactHeadService;
@Service
public class BMSTmAppContactHeadServiceImpl extends BaseServiceImpl<BMSTmAppContactHead> implements IBMSTmAppContactHeadService {

	@Autowired
	private IBMSTmAppContactHeadDao bmsTmAppContactHeadDao;
	@Override
	protected BaseDao<BMSTmAppContactHead> getDao() {
		return bmsTmAppContactHeadDao;
	}


}
