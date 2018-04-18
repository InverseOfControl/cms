package com.ymkj.cms.biz.service.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.apply.ReqAuditDifferencesVO;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface APPContactInfoService extends BaseService<APPContactInfoEntity>{
	
	public Long saveOrUpdate(APPContactInfoEntity appContactInfoEntity);
	
	public boolean saveList(List<APPContactInfoEntity> appContactInfoEntity);
	
	public long deleteByloanBaseId(Map<String, Object> paraMap);
	
	public long updateContactInfoByAudit(APPContactInfoEntity appContactInfoEntity);
	
	public long deleteContactInfoByAudit(APPContactInfoEntity appContactInfoEntity);
	
	public Long saveOrUpdateNew(APPContactInfoEntity appContactInfoEntity);
	
	public Long deleteApplyContactInfo(ReqAuditDifferencesVO reqAuditDifferencesVO);
	//删除对应的联系人，根据前台传来的顺序不用删除i其他的要删除
	public long deleteByLoanAndNumAll(Map<String,Object> mapDelteleContact);
}
