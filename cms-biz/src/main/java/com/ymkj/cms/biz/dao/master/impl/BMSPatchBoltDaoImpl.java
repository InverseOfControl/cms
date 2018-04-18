package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSPatchBoltDao;
import com.ymkj.cms.biz.entity.master.BMSPatchBolt;
@Repository
public class BMSPatchBoltDaoImpl extends BaseDaoImpl<BMSPatchBolt> implements IBMSPatchBoltDao{

	@Override
	public List<BMSPatchBolt> listPage(Map<String, Object> map) {
		return getSqlSession().selectList(getStatement("listPage"), map);
	}

}
