package com.ymkj.cms.biz.dao.audit.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.IAduitPersonDao;
import com.ymkj.cms.biz.entity.audit.BMSAduitPersonEntity;

@Repository
public class AduitPersonDaoImpl extends BaseDaoImpl<BMSAduitPersonEntity> implements IAduitPersonDao {

	@Override
	public BMSAduitPersonEntity findAduitPersonInfo(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("findAduitPersonInfo"), map);
	}

}
