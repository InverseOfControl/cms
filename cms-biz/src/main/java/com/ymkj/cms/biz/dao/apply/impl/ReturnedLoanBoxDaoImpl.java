package com.ymkj.cms.biz.dao.apply.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.IReturnedLoanBoxDao;
import com.ymkj.cms.biz.entity.apply.ReturnedLoanBoxSearchEntity;

@Repository
public class ReturnedLoanBoxDaoImpl extends BaseDaoImpl<ReturnedLoanBoxSearchEntity> implements IReturnedLoanBoxDao{

	@Override
	public int getCount(Map<String,Object> paramMap) {
		return getSessionTemplate().selectOne("getCount",paramMap);
	}

}
