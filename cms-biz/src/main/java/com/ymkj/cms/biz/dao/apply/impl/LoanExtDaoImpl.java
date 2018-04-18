package com.ymkj.cms.biz.dao.apply.impl;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.LoanExtDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.apply.checkNewDataEntity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LoanExtDaoImpl extends BaseDaoImpl<LoanExtEntity> implements LoanExtDao{

	@Override
	public List<checkNewDataEntity> queryCheckNewData(Map<String, Object> map) {
		return getSessionTemplate().selectList("queryCheckNewData", map);
	}

}
