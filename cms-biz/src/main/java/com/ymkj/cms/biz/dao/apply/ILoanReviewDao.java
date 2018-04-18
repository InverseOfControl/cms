package com.ymkj.cms.biz.dao.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.LoanReviewEntity;

public interface ILoanReviewDao extends BaseDao<LoanReviewEntity>{
	
	public List<LoanReviewEntity> getReviewList(Map<String,Object> paramMap);
	
	public int updateStatus(LoanReviewEntity loanReviewEntity);
	
	public int insertReviewReason(LoanReviewEntity loanReviewEntity);
	
	public int queryMessageCount(Map<String,Object> paramMap);
	
	public int updateIsReadStatus(String loanNo);
	
	public int updateOrSaveReviewStatus(Map<String,Object> map);
	
	public int updateBlackListIdByLoanNo(Map<String,Object> map);
	
	public LoanReviewEntity selectByLoanNo(String loanNo);
	
	public int updateReasonByNo(Map<String,Object> map);
}
