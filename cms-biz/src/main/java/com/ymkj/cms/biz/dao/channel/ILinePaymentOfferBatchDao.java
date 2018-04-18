package com.ymkj.cms.biz.dao.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferBatch;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作dao 接口
 */
public interface ILinePaymentOfferBatchDao extends BaseDao<LinePaymentOfferBatch>{
	
	 public List<LinePaymentOfferBatch> findHaTwoOfferBatchNotExport(Map<String,Object> map);
	 
	 public List<LoanBaseEntity> getAllLoanBaseInfo(Map<String, Object> map);
	 
	 public List<LinePaymentOffer> findListByVo(Map<String,String>map);
	 
	 public Map<String,String> findLoanRelInfo(Map<String,String>map); 
	

}
