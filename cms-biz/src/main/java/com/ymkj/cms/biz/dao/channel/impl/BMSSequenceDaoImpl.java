package com.ymkj.cms.biz.dao.channel.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.channel.IBMSSequenceDao;
import com.ymkj.cms.biz.entity.channel.BMSSequence;

@Repository
public class BMSSequenceDaoImpl extends BaseDaoImpl<BMSSequence> implements IBMSSequenceDao{

	@Override
	public BMSSequence getSequenceObj(String id) {
		return this.getSessionTemplate().selectOne("querySequenceById", id);
	}

	@Override
	public int updateSequence(Map<String, Object> paraMap) {
		return this.getSessionTemplate().update("updateSequence", paraMap);
	}

}
