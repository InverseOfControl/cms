package com.ymkj.cms.biz.dao.audit.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.BMSFirstAuditDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

/**
 * 初审接口DAO层实现类
 * @author YM10143
 *
 */
@Repository
public class BMSFirstAuditDaoImpl extends BaseDaoImpl<BMSFirstAuditEntity> implements BMSFirstAuditDao{

	@Override
	public List<BMSAutomaticDispatchEntity> automaticDispatchList(
			Map<String,Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getStatement("queryAutomaticDis"), map);
	}
	
	@Override
	public List<BMSAutomaticDispatchEntity> csAutomaticDispatchList(
			Map<String,Object> map) {
		return getSqlSession().selectList(getStatement("csQueryAutomaticDis"), map);
	}
	

	@Override
	public Integer updAuditNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("updAuditNo"), map);
	}

	@Override
	public Integer updLoanBase(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("updLoanBase"), map);
	}

	@Override
	public Integer updLoanAdExt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("updLoanAdExt"), map);
	}
	
	@Override
	public Integer updLoanAdExtReject(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("updLoanAdExtReject"), map);
	}
	
	@Override
	public Integer updLoanAdExtIsBlackNull(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("updLoanAdExtIsBlackNull"), map);
	}

	@Override
	public Integer plUpdLoanBase(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("plUpdLoanBase"), map);
	}

	@Override
	public Integer plUpdAuditNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("plUpdAuditNo"), map);
	}

	@Override
	public Integer updBmsLoanProduct(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(getStatement("updLoanProductInfo"), map);
	}

	@Override
	public Integer checkIsHositoryLoanNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatement("checkIsHositoryLoanNo"), map);
	}

	@Override
	public List<BMSFirstAuditEntity> queryBmsLogByLoan(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getStatement("queryBmsLogByLoan"), map);
	}

	@Override
	public int checkRtfNodeStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatement("checkRtfNodeStatus"), map);
	}

	@Override
	public String byLoanNoQueryXieShengCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatement("byLoanNoQueryXieShengCode"), map);
	}

	@Override
	public int byPersonCodeQueryJuse(String serviceCode) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatement("byPersonCodeQueryJuse"), serviceCode);
	}

	@Override
	public List<String> byAppRovalPersonCodeQueryLoanNo(String serviceCode) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getStatement("byAppRovalPersonCodeQueryLoanNo"), serviceCode);
	}

	@Override
	public Integer findTrialByStatus(String code) {
		return getSqlSession().selectOne(getStatement("findTrialByStatus"), code);
	}

	@Override
	public List<BMSFirstAuditEntity> queryFirstOperationTime(
			Map<String, Object> map) {
		
		return getSqlSession().selectList(getStatement("queryFirstOperationTime"),map);
	}

	@Override
	public List<BMSFirstAuditEntity> querycSFirstOperationTime( Map<String, Object> map) {
		
		return getSqlSession().selectList(getStatement("querycSFirstOperationTime"),map);
	}

	@Override
	public Integer updLoanBaseTwo(Map<String, Object> map) {
		return getSqlSession().update(getStatement("updLoanBaseTwo"), map);
	}
	
	@Override
	public int updateOnlineAWithinMonthsAddress(Map<String, Object> map) {
		return getSqlSession().update(getStatement("updOnlineAWithinMonthsAddress"), map);
	}

	@Override
	public int updatePolicyCheckIsVerify(Map<String, Object> map) {
		return getSqlSession().update(getStatement("updatePolicyCheckIsVerify"), map);
	}

	@Override
	public int updateCarCheckIsVerify(Map<String, Object> map) {
		return getSqlSession().update(getStatement("updateCarCheckIsVerify"), map);
	}

	@Override
	public Map<String, Object> getPersonPhone(String loanNo) {
		return getSqlSession().selectOne(getStatement("getPersonPhone"), loanNo);
	}

	@Override
	public int updateHZReportId(Map<String, Object> paramsMap) {
		return getSqlSession().update(getStatement("updateHZReportId"), paramsMap);
	}
	
	@Override
	public Map<String,Object> hzReportIdPreCondition(String loanNo) {
		return getSqlSession().selectOne(getStatement("hzReportIdPreCondition"), loanNo);
	}

	@Override
	public int updateHZReportChangeInfo(Map<String, Object> paramsMap) {
		return getSqlSession().update(getStatement("updateHZReportChangeInfo"), paramsMap);
	}
}
