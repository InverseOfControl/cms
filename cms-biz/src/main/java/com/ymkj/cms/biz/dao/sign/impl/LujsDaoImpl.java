package com.ymkj.cms.biz.dao.sign.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.dao.sign.ILujsDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

@Repository
public class LujsDaoImpl  extends BaseDaoImpl<BMSLoanBaseEntity> implements ILujsDao {

	@Override
	public Map<String, Object> getApplicationList(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("getApplicationList"), loanNo);
	}

	@Override
	public int saveApsApplyNo(Map<String, Object> paramsMap) {
		return super.getSqlSession().update(super.getStatement("saveApsApplyNo"), paramsMap);
	}

	@Override
	public int saveLufaxNoticeExternal(ReqLufax800001Vo reqVo) {
		return super.getSqlSession().update(super.getStatement("saveLufaxNoticeExternal"), reqVo);
	}

	@Override
	public String getLoanBaseIdByLoanNo(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("getLoanBaseIdByLoanNo"), loanNo);
	}

	@Override
	public Map<String, Object> getAuditReturnResult(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("getAuditReturnResult"), loanNo);
	}
	
	@Override
	public void delLujsManualAnditInfo(String loanNo) {
		super.getSqlSession().delete(super.getStatement("delLujsManualAnditInfo"), loanNo);
	}

	@Override
	public Map<String,Object> getPersonInfo(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("getPersonInfo"), loanNo);
	}
	
	@Override
	public Map<String,Object> getAppPersonInfo(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("getAppPersonInfo"), loanNo);
	}
	
	@Override
	public int updateIdLastDate(Map<String,Object> map) {
		return super.getSqlSession().update(super.getStatement("updateIdLastDate"), map);
	}
}
