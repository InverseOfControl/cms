package com.ymkj.cms.biz.service.audit.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.audit.ISignReassignmentDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.service.audit.ISignReassignmentService;

@Service
public class SignReassignmentImpl extends BaseServiceImpl<LoanBaseEntity> implements ISignReassignmentService{
	
	@Autowired
	private ISignReassignmentDao signReassignmentDao;

	@Override
	protected BaseDao<LoanBaseEntity> getDao() {
		return signReassignmentDao;
	}

	@Override
	public List<LoanBaseEntity> getSignReassignList() {
		return signReassignmentDao.getSignReassignList();
	}

	@Override
	public List<Map<String,Object>> getSignCodeListByOrgId(String orgId) {
		return signReassignmentDao.getSignCodeListByOrgId(orgId);
	}

	@Override
	public int signReassign(LoanBaseEntity loanBaseEntity) {
		
		return signReassignmentDao.signReassign(loanBaseEntity);
	}

	@Override
	public List<Map<String, Object>> getWhitePerson() {
		
		return signReassignmentDao.getWhitePerson();
	}

	@Override
	public List<Map<String, Object>> getSignCodeListBy(Map<String, Object> paramMap) {
		if("".equals(paramMap.get("signCodeList"))){
			paramMap.put("signCodeList",null);
		}
		return signReassignmentDao.getSignCodeListBy(paramMap);
	}
}
