package com.ymkj.cms.biz.dao.apply.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.ILoanReviewDao;
import com.ymkj.cms.biz.entity.apply.LoanReviewEntity;

@Repository
public class LoanReviewDaoImpl extends BaseDaoImpl<LoanReviewEntity> implements ILoanReviewDao{

	@Override
	public List<LoanReviewEntity> getReviewList(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList("getReviewList", paramMap);
	}
	
	@Override
	public int updateStatus(LoanReviewEntity loanReviewEntity) {
		
		return getSessionTemplate().update("updateStatus", loanReviewEntity);
	}

	@Override
	public int insertReviewReason(LoanReviewEntity loanReviewEntity) {
		return getSessionTemplate().update("insertReviewReason", loanReviewEntity);
	}

	@Override
	public int queryMessageCount(Map<String,Object> paramMap) {
		return getSessionTemplate().selectOne("queryMessageCount",paramMap);
	}

	@Override
	public int updateIsReadStatus(String loanNo) {
		
		return getSessionTemplate().update("updateIsReadStatus",loanNo);
	}

	@Override
	public int updateOrSaveReviewStatus(Map<String, Object> map) {
		return getSessionTemplate().update("updateOrSaveReviewStatus",map);
	}

	@Override
	public int updateBlackListIdByLoanNo(Map<String, Object> map) {
		return getSessionTemplate().update("updateBlackListIdByLoanNo",map);
	}

	@Override
	public LoanReviewEntity selectByLoanNo(String loanNo) {
		return getSessionTemplate().selectOne("selectByLoanNo",loanNo);
	}

	@Override
	public int updateReasonByNo(Map<String, Object> map) {
		return getSessionTemplate().update("updateReasonByNo", map);
	}
	
}
