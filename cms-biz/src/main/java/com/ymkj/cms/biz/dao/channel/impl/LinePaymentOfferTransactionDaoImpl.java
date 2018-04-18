package com.ymkj.cms.biz.dao.channel.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.channel.ILinePaymentOfferTransactionDao;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferTransaction;

@Repository
public class LinePaymentOfferTransactionDaoImpl extends BaseDaoImpl<LinePaymentOfferTransaction> implements ILinePaymentOfferTransactionDao{

	@Override
	public List<LinePaymentOfferTransaction> findOfferTransactionListByMap(Map<String, Object> params) {
		 return getSessionTemplate().selectList("findOfferTransactionListByMap", params);
	}
	
	 public LinePaymentOfferTransaction findHaTwoOfferTransactionByMap(Map<String, Object> paramMap) {
	        return getSqlSession().selectOne("findHaTwoOfferTransactionByMap", paramMap);
	    }
	    
}
