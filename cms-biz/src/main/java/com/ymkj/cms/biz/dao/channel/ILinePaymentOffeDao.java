package com.ymkj.cms.biz.dao.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作dao 接口
 */
public interface ILinePaymentOffeDao extends BaseDao<LinePaymentOffer>{
	
	LinePaymentOffer findById(long id);
	
	List<LinePaymentOffer> findNoOfferOrFail(Map<String,Object>paramMap);
	
	BMSLoanBaseEntity findLoanById(Map<String,Object> paraMap);

	

}
