package com.ymkj.cms.biz.dao.apply.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.apply.ReqAuditDifferencesVO;
import com.ymkj.cms.biz.dao.apply.APPContactInfoDao;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;

@Repository
public class APPContactInfoDaoImpl extends BaseDaoImpl<APPContactInfoEntity> implements APPContactInfoDao{

	public long deleteByloanBaseId(Map<String, Object> paraMap) {
		
		return  getSessionTemplate().delete("deleteByloanBaseId", paraMap);
	}
	
	
	public long updateContactInfoByAudit(APPContactInfoEntity appContactInfoEntity){
		return  getSessionTemplate().update("updateContactInfoByAudit", appContactInfoEntity);
	}
	
	public long deleteContactInfoByAudit(APPContactInfoEntity appContactInfoEntity){
		return  getSessionTemplate().update("deleteContactInfoByAudit", appContactInfoEntity);
	}


	public long updateAll(APPContactInfoEntity appContactInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appContactInfoEntity);
	}


	@Override
	public Long deleteApplyContactInfo(ReqAuditDifferencesVO reqAuditDifferencesVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNo",reqAuditDifferencesVO.getLoanNo());
		map.put("sequenceNum", reqAuditDifferencesVO.getSequenceNum());
		return  (long) getSessionTemplate().delete("deleteApplyContactInfo", map);
	}


	@Override
	public long deleteByLoanAndNumAll(Map<String, Object> mapDelteleContact) {
		return super.getSqlSession().delete(super.getStatement("deleteByLoanAndNumAll"), mapDelteleContact);
	}
}
