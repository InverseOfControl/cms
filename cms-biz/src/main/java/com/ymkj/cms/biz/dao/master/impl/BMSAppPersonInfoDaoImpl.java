package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
@Repository
public class BMSAppPersonInfoDaoImpl extends BaseDaoImpl<BMSAppPersonInfo> implements IBMSAppPersonInfoDao{

	@Override
	public BMSAppPersonInfo byLoanNoQueryInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatement("byLoanNoQueryInfo"), map);
	}

	@Override
	public Integer updateByLoanNo(Map<String, Object> map) {
		
		return super.getSqlSession().update(super.getStatement("updateByLoanNo"), map);
	}
	
	@Override
	public List<Map<String, Object>> queryAdditionRecords(Map<String, String> paramMap) {
		List<Map<String,Object>> list = getSqlSession().selectList("additionRecords",paramMap);
		return list;
	}
	
	
	@Override
	public Integer updatePhoneCellStatus(Map<String, Object> map) {
		return super.getSqlSession().update("updatePhoneCellStatus", map);
	}

	@Override
	public List<Map<String,Object>> qyeryPhoneCellStatus(Map<String, Object> map) {
		return getSqlSession().selectList("queryPhoneStatus", map);
	}

}
