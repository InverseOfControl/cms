package com.ymkj.cms.biz.dao.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferTransaction;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作dao 接口
 */
public interface ILinePaymentOfferTransactionDao extends BaseDao<LinePaymentOfferTransaction>{
	
	 /**
     * 根据批次号相关条件查询报盘流水表信息
     * @param params
     * @return
     */
    public List<LinePaymentOfferTransaction> findOfferTransactionListByMap(Map<String, Object> params);
    
    
    public LinePaymentOfferTransaction findHaTwoOfferTransactionByMap(Map<String, Object> paramMap);

}
