package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.dao.master.IBMSLimitChannelDao;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;

@Repository
public class BMSLimitChannelDaoImpl extends BaseDaoImpl<BMSLimitChannel> implements IBMSLimitChannelDao{

	@Override
	public void deleteByCondition(BMSLimitChannel limitChannel) {
		super.getSqlSession().delete(super.getStatement("deleteByCondition"), limitChannel);
		
	}
	@Override
	public void updateByCondition(BMSLimitChannel limitChannel) {
		super.getSqlSession().delete(super.getStatement("updateByCondition"), limitChannel);
	}
	@Override
	public long logicalDelete(BMSLimitChannel limitChannel) {
		if (limitChannel == null)
			throw new CoreException(CoreErrorCode.DB_UPDATE_RESULT_0.getErrorCode(), "更新对象为空");
		int result = getSessionTemplate().update(getStatement("logicalDelete"), limitChannel);
		return result;
	}
	
	@Override
	public BMSLimitChannel  getFULimit(BMSLimitChannel limitChannel){
		
		
		return super.getSqlSession().selectOne(super.getStatement("getFULimit"), limitChannel);
		
	}
	@Override
	public BMSLimitChannel getFULimitByOrgId(BMSLimitChannel limitChannel) {
		return super.getSqlSession().selectOne(super.getStatement("getFULimitByOrgId"), limitChannel);
	}
	@Override
	public long updateByAuLimitId(BMSLimitChannel limitChannel) {
		if (limitChannel == null)
			throw new CoreException(CoreErrorCode.DB_UPDATE_RESULT_0.getErrorCode(), "更新对象为空");
		int result = getSessionTemplate().update(getStatement("updateByAuLimitId"), limitChannel);
		return result;
	}
	@Override
	public List<BMSLimitChannel> findHistory(Map<String, Object> historyParamMap) {
		return getSessionTemplate().selectList(getStatement("findHistory"),historyParamMap);

	}
	@Override
	public List<BMSLimitChannel> listByRate(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(getStatement("listByRate"),paramMap);
	}
}
