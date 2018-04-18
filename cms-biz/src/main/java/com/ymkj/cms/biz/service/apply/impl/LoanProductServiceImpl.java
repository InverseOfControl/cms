package com.ymkj.cms.biz.service.apply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.LoanProductDao;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.service.apply.LoanProductService;

@Service
public class LoanProductServiceImpl extends BaseServiceImpl<LoanProductEntity> implements LoanProductService{
	
	@Autowired
	private LoanProductDao loanProductDao;

	@Override
	protected BaseDao<LoanProductEntity> getDao() {
		return loanProductDao;
	}

	@Override
	public Long saveOrUpdate(LoanProductEntity loanProductEntity) {
		
		
		long id = 0;
		if(loanProductEntity.getId() == null){
			long insert = loanProductDao.insert(loanProductEntity);
			if(insert > 0){
				id = loanProductEntity.getId();
			}
		}else{
			 long update = loanProductDao.update(loanProductEntity);
			 if(update > 0){
				 id = loanProductEntity.getId();
			 }
		}
		
		return id;
	}

	@Override
	public boolean saveList(List<LoanProductEntity> loanProductEntitys) {
		
		long i = loanProductDao.batchInsert(loanProductEntitys);
		if(i>0){
			return true;
		}
		return false; 
	}

	@Override
	public List<String> getChannelCodeByLoans(List<String> loans) {
		// TODO Auto-generated method stub
		return loanProductDao.getChannelCodeByLoans(loans);
	}
	
	@Override
	public String getContractType(String loanNo) {
		return loanProductDao.getContractType(loanNo);
	}
	
	
	@Override
	public BMSProduct findProductData(String productCd) {
		return loanProductDao.findProductData(productCd);
	}

}
