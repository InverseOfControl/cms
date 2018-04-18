package com.ymkj.cms.biz.dao.audit.last.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.last.IBMSFinalAuditDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
/**
 * 信审终审实现DAO
 * @author YM10172
 *
 */
@Repository
public class BMSFinalAuditDaoImpl extends BaseDaoImpl<BMSFirstAuditEntity> implements IBMSFinalAuditDao {

	@Override
	public int checkFinalRtfNodeStatus(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("checkFinalRtfNodeStatus"), map);
	}

	@Override
	public List<BMSAutomaticDispatchEntity> zSautomaticDispatchList(
			Map<String, Object> map) {	
		return getSqlSession().selectList("queryzSAutomaticDis", map);
	}

	@Override
	public Integer updAuditNo(Map<String, Object> map) {
		
		return getSqlSession().update(getStatement("updAuditNo"), map);
	}

	@Override
	public Integer updLoanBase(Map<String, Object> map) {
		
		return getSqlSession().update(getStatement("updLoanBase"), map);
	}

	@Override
	public Integer updBmsLoanProduct(Map<String, Object> map) {
	
		return getSqlSession().update(getStatement("updLoanProductInfo"), map);
	}

	@Override
	public Long findByLoanNo(String loanNo) {
		
		return getSqlSession().selectOne(getStatement("findByLoanNo"), loanNo);
	}

	@Override
	public Integer findLastByStatus(String code) {
		return getSqlSession().selectOne(getStatement("findLastByStatus"), code);
	}

	@Override
	public List<BMSFirstAuditEntity> queryzSBmsLogByLoan(Map<String, Object> map) {
		
		return getSqlSession().selectList(getStatement("queryzSBmsLogByLoan"),map);
	}

	@Override
	public Integer updLoanBaseTwo(Map<String, Object> map) {
		return getSqlSession().update(getStatement("updLoanBaseTwo"), map);
	}

	@Override
	public BMSFirstAuditEntity findByCheckCode(Map<String, Object> map) {
		
		return getSqlSession().selectOne(getStatement("findByCheckCode"), map);
	}

	@Override
	public BMSFirstAuditEntity findLogByReturn(Map<String, Object> map) {
	
		return getSqlSession().selectOne(getStatement("findLogByReturn"), map);
	}

	@Override
	public BMSFirstAuditEntity findXsSnapVersionInfo(Map<String, Object> map) {
		
		return getSqlSession().selectOne(getStatement("findXsSnapVersionInfo"), map);
	}

}
