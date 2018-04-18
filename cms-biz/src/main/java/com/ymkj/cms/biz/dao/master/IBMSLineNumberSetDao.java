package com.ymkj.cms.biz.dao.master;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSLoanBank;
import com.ymkj.cms.biz.entity.master.BMSLoanBankExt;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDic;
import com.ymkj.cms.biz.entity.master.BMSOffLineOfferBankDicEntity;

public interface IBMSLineNumberSetDao extends BaseDao<BMSOffLineOfferBankDic>{

	public BMSLoanBank findLoanBank(Map<String,Object> map);
	public Integer findByRegionAndCode(Map<String,Object> map);
	public BMSOffLineOfferBankDicEntity findByRegionAndCodeData(Map<String,Object> map);
	public BMSLoanBankExt findLoanBankExtByBankId(Map<String,Object> map);
	
	public Integer insertLoanBankExt(BMSLoanBankExt bmsLoanBankExt);
	public Integer updateLoanBankExt(BMSLoanBankExt bmsLoanBankExt);
	
	
	public Integer insertLineOfferBankDic(BMSOffLineOfferBankDicEntity entity);
	public Integer updateLineOfferBankDic(BMSOffLineOfferBankDicEntity entity);
}
