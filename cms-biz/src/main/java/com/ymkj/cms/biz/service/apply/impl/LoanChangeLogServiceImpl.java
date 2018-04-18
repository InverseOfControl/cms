package com.ymkj.cms.biz.service.apply.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.APPWhitePersonnelDao;
import com.ymkj.cms.biz.dao.apply.LoanChangeLogDao;
import com.ymkj.cms.biz.entity.apply.BmsLoanChangeLogEntity;
import com.ymkj.cms.biz.service.apply.LoanChangeLogService;

@Service
public class LoanChangeLogServiceImpl extends BaseServiceImpl<BmsLoanChangeLogEntity> implements LoanChangeLogService{

	
	@Autowired
	private LoanChangeLogDao loanChangeLogDao;
	
	@Override
	protected BaseDao<BmsLoanChangeLogEntity> getDao() {
		return loanChangeLogDao;
	}

	@Override
	public Long saveOrUpdate(BmsLoanChangeLogEntity bmsLoanChangeLogEntity) {
		
		long id = 0;
		if(bmsLoanChangeLogEntity.getId() == null){
			long insert = loanChangeLogDao.insert(bmsLoanChangeLogEntity);
			if(insert > 0){
				id = bmsLoanChangeLogEntity.getId();
			}
		}else{
			 long update = loanChangeLogDao.update(bmsLoanChangeLogEntity);
			 if(update > 0){
				 id = bmsLoanChangeLogEntity.getId();
			 }
		}
		return id;
	}

}
