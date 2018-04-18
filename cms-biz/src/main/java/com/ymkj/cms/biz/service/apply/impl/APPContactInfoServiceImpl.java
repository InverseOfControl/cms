package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.apply.ReqAuditDifferencesVO;
import com.ymkj.cms.biz.dao.apply.APPContactInfoDao;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.service.apply.APPContactInfoService;

@Service
public class APPContactInfoServiceImpl extends BaseServiceImpl<APPContactInfoEntity> implements APPContactInfoService{
	
	@Autowired
	private APPContactInfoDao appContactInfoDao;
	@Override
	protected BaseDao<APPContactInfoEntity> getDao() {
		return appContactInfoDao;
	}

	@Override
	public Long saveOrUpdate(APPContactInfoEntity appContactInfoEntity) {
		
		
		long id = 0;
		if(appContactInfoEntity.getId() == null){
			long insert = appContactInfoDao.insert(appContactInfoEntity);
			if(insert > 0){
				id = appContactInfoEntity.getId();
			}
		}else{
			 long update = appContactInfoDao.update(appContactInfoEntity);
			 if(update > 0){
				 id = appContactInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<APPContactInfoEntity> appContactInfoEntitys) {
		long i = appContactInfoDao.batchInsert(appContactInfoEntitys);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public long deleteByloanBaseId(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return appContactInfoDao.deleteByloanBaseId(paraMap);
	}

	@Override
	public long updateContactInfoByAudit(APPContactInfoEntity appContactInfoEntity) {
		// TODO Auto-generated method stub
		return appContactInfoDao.updateContactInfoByAudit(appContactInfoEntity);
	}
	
	@Override
	public long deleteContactInfoByAudit(APPContactInfoEntity appContactInfoEntity) {
		// TODO Auto-generated method stub
		return appContactInfoDao.deleteContactInfoByAudit(appContactInfoEntity);
	}

	@Override
	public Long saveOrUpdateNew(APPContactInfoEntity appContactInfoEntity) {
		
		
		long id = 0;
		if(appContactInfoEntity.getId() == null){
			long insert = appContactInfoDao.insert(appContactInfoEntity);
			if(insert > 0){
				id = appContactInfoEntity.getId();
			}
		}else{
			 long update = appContactInfoDao.updateAll(appContactInfoEntity);
			 if(update > 0){
				 id = appContactInfoEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public Long deleteApplyContactInfo(ReqAuditDifferencesVO reqAuditDifferencesVO) {
		return appContactInfoDao.deleteApplyContactInfo(reqAuditDifferencesVO);
	}

	@Override
	public long deleteByLoanAndNumAll(Map<String, Object> mapDelteleContact) {
		return appContactInfoDao.deleteByLoanAndNumAll(mapDelteleContact);
	}

}
