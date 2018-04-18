package com.ymkj.cms.biz.service.apply.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.IReturnedLoanBoxDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.apply.ReturnedLoanBoxSearchEntity;
import com.ymkj.cms.biz.service.apply.IReturnedLoanBoxService;

@Service
public class ReturnedLoanBoxServiceImpl extends BaseServiceImpl<ReturnedLoanBoxSearchEntity> implements IReturnedLoanBoxService{
	
	@Autowired
	private IReturnedLoanBoxDao iReturnedLoanBoxDao;
	
	@Override
	protected BaseDao<ReturnedLoanBoxSearchEntity> getDao() {
		return iReturnedLoanBoxDao;
	}

	@Override
	public PageBean<ReturnedLoanBoxSearchEntity> search(PageParam pageParam, Map<String, Object> paramMap) {
		return iReturnedLoanBoxDao.listPage(pageParam, paramMap);
	}

	@Override
	public int queryMessageCount(Map<String,Object> paramMap) {
		return iReturnedLoanBoxDao.getCount(paramMap);
	}

}
