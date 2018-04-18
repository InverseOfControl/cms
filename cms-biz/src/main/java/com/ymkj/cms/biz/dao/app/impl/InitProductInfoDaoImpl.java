package com.ymkj.cms.biz.dao.app.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.app.IInitProductInfoDao;
import com.ymkj.cms.biz.entity.app.input.AppApplyInfoEntity;

@Repository
public class InitProductInfoDaoImpl extends BaseDaoImpl<AppApplyInfoEntity> implements IInitProductInfoDao {

	@Override
	public List<Map<String, Object>> getRefuseReasonList() {
		return getSessionTemplate().selectList("getRefuseReasonList");
	}

}
