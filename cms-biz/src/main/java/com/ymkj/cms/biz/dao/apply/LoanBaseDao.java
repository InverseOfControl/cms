package com.ymkj.cms.biz.dao.apply;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.entity.apply.EntrySearchEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSContractSignSearchEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface LoanBaseDao extends BaseDao<LoanBaseEntity>{
	public PageBean<EntrySearchEntity> listPageEntrySearch(PageParam pageParam, Map<String, Object> paramMap, String sqlId) ;
		 
	public PageBean<BMSContractSignSearchEntity> listPageContractSignSearch(PageParam pageParam, Map<String, Object> paramMap,String sqlId) ;
	
	
	public List<LoanBaseEntity> getByMap(Map<String, Object> paramMap);
	
	public PageBean<LoanBaseEntity> getReassignment(PageParam pageParam, Map<String, Object> paramMap, String sqlId);
	
	public InformationEntity queryInformationEntity(Map<String, Object> paramMap);
	
	public boolean updateOperatingStaff(Map<String, Object> paramMap);
	
	public long updateVersion(Map<String, Object> paramMap);
	
	public long updateLoanBaseVersionById(Map<String, Object> paramMap);
	
	public Long queryWaitToDealCount(Map<String, Object> paramMap);
	public List<LoanBaseEntity> findLoanNoByNotBelongTo(PersonHistoryLoanVO personHistoryLoanVO);
	
	public Map<String,Object> findRadioByApplyType(String applyType);
	
	public String createLoanNo(Date date);
	
	public long updateIfNewLoanNo(Map<String, Object> paramMap);

	public String getOrgPrevReviewCode(Map paramMap);
	
	public List<Map<String,Object>> findDataByIdNoRefuse(String idNo);
	public List<Map<String,Object>> findDataByIdNoCancel(String idNo);
	
	public Map<String,Object> queryContractInfo(String loanNo);
	
}
