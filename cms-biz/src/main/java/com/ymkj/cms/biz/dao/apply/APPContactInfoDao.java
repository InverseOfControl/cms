package com.ymkj.cms.biz.dao.apply;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.apply.ReqAuditDifferencesVO;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface APPContactInfoDao extends BaseDao<APPContactInfoEntity>{
	
	public long deleteByloanBaseId(Map<String, Object> paraMap);
	
	public long updateContactInfoByAudit(APPContactInfoEntity appContactInfoEntity);
	
	public long deleteContactInfoByAudit(APPContactInfoEntity appContactInfoEntity);
	
	public long updateAll(APPContactInfoEntity appContactInfoEntity);
	
	
	public Long deleteApplyContactInfo(ReqAuditDifferencesVO reqAuditDifferencesVO);
	
	public long deleteByLoanAndNumAll(Map<String, Object> mapDelteleContact);
}
