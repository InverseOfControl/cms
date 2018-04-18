package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSReasonReLinksDao;
import com.ymkj.cms.biz.entity.master.BMSReason;
@Repository
public class BMSReasonReLinksDaoImpl extends BaseDaoImpl<BMSReason> implements IBMSReasonReLinksDao {

	@Override
	public BMSReason findBMSReLinksById(Map<String, Object> map) {

		return super.getSqlSession().selectOne(super.getStatement("findBMSReLinksById"),map);
	}

	@Override
	public Integer updateBMSReasonByVal(BMSReason bmsReason) {

		return super.getSqlSession().update(super.getStatement("updateBMSReasonByVal"), bmsReason);
	}

	@Override
	public  List<BMSReason> findByRelationCode(Map<String,Object> map) {

		return super.getSqlSession().selectList(super.getStatement("findByRelationCode"), map);
	}

	@Override
	public Integer delReasonCode(Map<String, Object> map) {

		return super.getSqlSession().delete(super.getStatement("deleteReasonCode"), map);
	}

	@Override
	public Integer updateBMSReasonIfShow(BMSReason bmsReason) {

		return super.getSqlSession().update(super.getStatement("updateBMSReasonIfShow"), bmsReason);
	}

}
