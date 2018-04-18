package com.ymkj.cms.biz.service.sign.impl;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanBaseEntityServiceImpl extends BaseServiceImpl<BMSLoanBaseEntity> implements ILoanBaseEntityService {
	@Autowired
	private ILoanBaseEntityDao loanBaseDao;

	@Override
	public BMSLoanBaseEntity getByLoanNo(String loanNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		return loanBaseDao.findSignInfo(paramMap);
	}

	@Override
	public BMSLoanBaseEntity findByloanBaseId(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanBaseId", id);
		BMSLoanBaseEntity exBaseEntity=	loanBaseDao.findByloanBaseId(paramMap);
		return exBaseEntity;
	}
	
	@Override
	public List<Map<String, Object>> getManagerCodeListByOrgId(String orgId) {
		List<Map<String, Object>> list=	loanBaseDao.getManagerCodeListByOrgId(orgId);
		return list;
	}

	@Override
	public BMSLoanBaseEntity getLoanInfoByLoanNo(String loanNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		return loanBaseDao.getLoanInfoByLoanNo(paramMap);
	}

	@Override
	protected BaseDao<BMSLoanBaseEntity> getDao() {
		return loanBaseDao;
	}

}
