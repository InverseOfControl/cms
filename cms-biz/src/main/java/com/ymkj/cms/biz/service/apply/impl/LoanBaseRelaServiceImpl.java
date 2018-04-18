package com.ymkj.cms.biz.service.apply.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.LoanBaseRelaDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;
import com.ymkj.cms.biz.service.apply.LoanBaseRelaService;

@Service
public class LoanBaseRelaServiceImpl extends BaseServiceImpl<LoanBaseRelaEntity> implements LoanBaseRelaService{
	
	@Autowired
	private LoanBaseRelaDao loanBaseRelaDao;

	@Override
	protected BaseDao<LoanBaseRelaEntity> getDao() {
		return loanBaseRelaDao;
	}

	@Override
	public Long saveOrUpdate(LoanBaseRelaEntity loanBaseRelaEntity) {
		// TODO Auto-generated method stub

		long id = 0;
		if(loanBaseRelaEntity.getId() == null){
			long insert = loanBaseRelaDao.insert(loanBaseRelaEntity);
			if(insert > 0){
				id = loanBaseRelaEntity.getId();
			}
		}else{
			 long update = loanBaseRelaDao.update(loanBaseRelaEntity);
			 if(update > 0){
				 id = loanBaseRelaEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public LoanBaseRelaEntity getByBaseId(Long baseId) {
		return loanBaseRelaDao.getByBaseId(baseId);
	}
 

	 
}
