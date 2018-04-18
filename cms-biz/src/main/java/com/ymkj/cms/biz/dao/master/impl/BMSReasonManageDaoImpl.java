package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSReasonManageDao;
import com.ymkj.cms.biz.entity.master.BMSReason;
@Repository
public class BMSReasonManageDaoImpl extends BaseDaoImpl<BMSReason> implements IBMSReasonManageDao{

	@Override
	public List<BMSReason>  findBMSReasonByValue(Map<String, Object> map) {

		return super.getSqlSession().selectList(super.getStatement("findBMSReasonByValue"), map);
	}

	@Override
	public Integer findMaxId() {

		return super.getSqlSession().selectOne(super.getStatement("findMaxId"));
	}

	@Override
	public BMSReason findReasonByPId(Long parentId) {

		return super.getSqlSession().selectOne(super.getStatement("findReasonByPId"),parentId);
	}

	@Override
	public Integer updateById(BMSReason reason) {

		return super.getSqlSession().update(super.getStatement("updateById"), reason);
	}

	@Override
	public List<BMSReason> findReasonExport(Map<String,Object> map) {

		return super.getSqlSession().selectList(super.getStatement("findReasonExport"),map);
	}

	@Override
	public List<BMSReason> findReasonByParame(Map<String, Object> map) {

		return super.getSqlSession().selectList(super.getStatement("findReasonByParame"), map);
	}

}
