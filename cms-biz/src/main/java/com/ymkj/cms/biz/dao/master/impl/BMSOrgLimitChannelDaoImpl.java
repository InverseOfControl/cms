package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.cms.biz.dao.master.IBMSOrgLimitChannelDao;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;

@Repository
public class BMSOrgLimitChannelDaoImpl extends BaseDaoImpl<BMSOrgLimitChannel> implements IBMSOrgLimitChannelDao{

	@Override
	public void deleteByCondition(BMSOrgLimitChannel limitChannel) {
		super.getSqlSession().delete(super.getStatement("deleteByCondition"), limitChannel);
		
	}

	@Override
	public void updateByCondition(BMSOrgLimitChannel limitChannel) {
		super.getSqlSession().update(super.getStatement("updateByCondition"), limitChannel);
		
	}
	@Override
	public void updateByParams(BMSOrgLimitChannel limitChannel) {
		super.getSqlSession().update(super.getStatement("updateByParams"), limitChannel);
		
	}

	@Override
	public long logicalDelete(BMSOrgLimitChannel orgLimitChannel) {
		if (orgLimitChannel == null)
			throw new CoreException(CoreErrorCode.DB_UPDATE_RESULT_0.getErrorCode(), "更新对象为空");
		int result = getSessionTemplate().update(getStatement("logicalDelete"), orgLimitChannel);
		return result;
	}

	@Override
	public boolean isDisabled(BMSOrgLimitChannel orgLimitChannel) {
		
		long result = getSessionTemplate().selectOne(getStatement("isDisabled"), orgLimitChannel);
		if(result > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<BMSOrgLimitChannel> findByParams(Map<String, Object> params) {
		return getSessionTemplate().selectList("findByParams",params);
	}
	
	@Override
	public List<BMSOrgLimitChannel> findOrgIntersectByParams(Map<String, Object> params) {
		return getSessionTemplate().selectList("findOrgIntersectByParams",params);
	}

	@Override
	public boolean branchProductRelevanceCheck(Map<String, Object> params) {
		long result = getSessionTemplate().selectOne(getStatement("branchProductRelevanceCheck"), params);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean updateOrgLimitChannelAllByCondition(Map<String, Object> params) {
		int result = getSessionTemplate().update(getStatement("updateOrgLimitChannelAllByCondition"), params);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isDisabledInSign(Map<String, Object> params) {
		long result = getSessionTemplate().selectOne(getStatement("isDisabledInSign"), params);
		if(result > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<BMSOrgLimitChannel> listOrgProductLimitByOrgProApp(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList("listOrgProductLimitByOrgProApp",paramMap);

	}
	@Override
	public long updateByOrgLimitId(BMSOrgLimitChannel orgLimitChannel) {
		if (orgLimitChannel == null)
			throw new CoreException(CoreErrorCode.DB_UPDATE_RESULT_0.getErrorCode(), "更新对象为空");
		int result = getSessionTemplate().update(getStatement("updateByOrgLimitId"), orgLimitChannel);
		return result;
	}

	@Override
	public List<BMSOrgLimitChannel> findHistory(Map<String, Object> historyParamMap) {
		return getSessionTemplate().selectList(getStatement("findHistory"),historyParamMap);

	}

	@Override
	public BMSOrgLimitChannel findOrgLimitChannelLimitUnion(Map<String, Object> paramMap) {
		return getSessionTemplate().selectOne(getStatement("findOrgLimitChannelLimitUnion"),paramMap);
	}

	@Override
	public List<BMSOrgLimitChannel> listOrgLimitChannelRateBy(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(getStatement("listOrgLimitChannelRateBy"),paramMap);
	}
	

}
