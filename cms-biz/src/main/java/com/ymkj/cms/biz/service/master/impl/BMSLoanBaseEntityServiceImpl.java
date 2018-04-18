package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanBaseVO;
import com.ymkj.cms.biz.dao.master.IBMSLoanBaseEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanBase;
import com.ymkj.cms.biz.service.master.IBMSLoanBaseEntityService;

@Service
public class BMSLoanBaseEntityServiceImpl extends BaseServiceImpl<BMSLoanBase> implements IBMSLoanBaseEntityService {

	@Autowired
	private IBMSLoanBaseEntityDao bmsLoanBaseEntityDao;

	@Override
	protected BaseDao<BMSLoanBase> getDao() {
		return bmsLoanBaseEntityDao;
	}

	@Override
	public BMSLoanBase findBYLoanNo(String loanNo) {
	
		return bmsLoanBaseEntityDao.findBYLoanNo(loanNo);
	}

	@Override
	public Integer findLoanBaseCount(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findLoanBaseCount(paramMap);
	}

	@Override
	public List<ResBMSLoanBaseVO> findGrantLoanUpdateByCoreJob(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findGrantLoanUpdateByCoreJob(paramMap);
	}

	@Override
	public List<ResBMSLoanBaseVO> findLoanBaseJob(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findLoanBaseJob(paramMap);
	}

	@Override
	public List<ResBMSLoanBaseVO> findBindCreditReportJob(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findBindCreditReportJob(paramMap);
	}

	@Override
	public Integer findLoanBaseJobCount(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findLoanBaseJobCount(paramMap);
	}

	@Override
	public Integer findGrantLoanUpdateByCoreJobCount(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findGrantLoanUpdateByCoreJobCount(paramMap);
	}

	@Override
	public Integer findBindCreditReportJobCount(Map<String, Object> paramMap) {
		return bmsLoanBaseEntityDao.findBindCreditReportJobCount(paramMap);
	}
	
	

}
