package com.ymkj.cms.biz.dao.apply.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.LoanBaseRelaDao;
import com.ymkj.cms.biz.dao.apply.LoanProductDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;

@Repository
public class LoanBaseRelaDaoImpl extends BaseDaoImpl<LoanBaseRelaEntity> implements LoanBaseRelaDao{

	@Override
	public LoanBaseRelaEntity getByBaseId(Long baseId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("baseId", baseId);
		return getSessionTemplate().selectOne("getByBaseId", paramMap);
	}

}
