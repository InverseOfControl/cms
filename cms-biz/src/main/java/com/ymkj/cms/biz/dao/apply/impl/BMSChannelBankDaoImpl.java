package com.ymkj.cms.biz.dao.apply.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.IBMSChannelBankDao;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
@Repository
public class BMSChannelBankDaoImpl extends BaseDaoImpl<BMSChannelBank> implements IBMSChannelBankDao {

	@Override
	public Integer checkParentIsStart(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("checkParentIsStart"), map);
	}

	@Override
	public Integer checkParentIsChanel(Map<String, Object> map) {
			return super.getSqlSession().selectOne(super.getStatement("checkParentIsChanel"), map);
	 }
}
