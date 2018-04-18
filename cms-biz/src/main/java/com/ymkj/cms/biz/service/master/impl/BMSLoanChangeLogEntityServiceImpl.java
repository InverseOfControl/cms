package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanChangeLogEntityVO;
import com.ymkj.cms.biz.dao.master.IBMSLoanBaseRelaDao;
import com.ymkj.cms.biz.dao.master.IBMSLoanChangeLogEntityDao;
import com.ymkj.cms.biz.entity.apply.BmsLoanChangeLogEntity;
import com.ymkj.cms.biz.entity.master.BMSLoanBaseRela;
import com.ymkj.cms.biz.entity.master.BMSLoanChangeLog;
import com.ymkj.cms.biz.service.master.IBMSLoanChangeLogEntityService;
@Service
public class BMSLoanChangeLogEntityServiceImpl extends BaseServiceImpl<BmsLoanChangeLogEntity> implements IBMSLoanChangeLogEntityService{

	@Autowired
	private IBMSLoanChangeLogEntityDao iBMSLoanChangeLogEntityDao;
	@Override
	protected BaseDao<BmsLoanChangeLogEntity> getDao() {
		return iBMSLoanChangeLogEntityDao;
	}

}
