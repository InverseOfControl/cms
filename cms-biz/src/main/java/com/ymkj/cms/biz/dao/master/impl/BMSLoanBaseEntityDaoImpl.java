package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.dao.master.IBMSLoanBaseEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanBase;

@Repository
public class BMSLoanBaseEntityDaoImpl extends BaseDaoImpl<BMSLoanBase> implements IBMSLoanBaseEntityDao{

	@Override
	public BMSLoanBase findBYLoanNo(String loanNo) {
		
		return getSessionTemplate().selectOne(super.getStatement("findBYLoanNo"),loanNo);
	}

	@Override
	public Integer findLoanBaseCount(Map<String, Object> paramMap) {
		return getSessionTemplate().selectOne(super.getStatement("findLoanBaseCount"),paramMap);
	}

	@Override
	public List<ResBMSLoanBaseVO> findGrantLoanUpdateByCoreJob(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(super.getStatement("findGrantLoanUpdateByCoreJob"),paramMap);
		
	}

	@Override
	public List<ResBMSLoanBaseVO> findLoanBaseJob(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(super.getStatement("findLoanBaseJob"),paramMap);
	}

	@Override
	public List<ResBMSLoanBaseVO> findBindCreditReportJob(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(super.getStatement("findBindCreditReportJob"),paramMap);
	}

	@Override
	public Integer findLoanBaseJobCount(Map<String, Object> paramMap) {
		return getSessionTemplate().selectOne(super.getStatement("findLoanBaseJobCount"),paramMap);
	}

	@Override
	public Integer findGrantLoanUpdateByCoreJobCount(Map<String, Object> paramMap) {
		return getSessionTemplate().selectOne(super.getStatement("findGrantLoanUpdateByCoreJobCount"),paramMap);
	}

	@Override
	public Integer findBindCreditReportJobCount(Map<String, Object> paramMap) {
		return getSessionTemplate().selectOne(super.getStatement("findBindCreditReportJobCount"),paramMap);
	}

}
