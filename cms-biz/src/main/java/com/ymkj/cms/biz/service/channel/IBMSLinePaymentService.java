package com.ymkj.cms.biz.service.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUnderLinePaymentVo;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferBatch;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作Service interface
 */
public interface IBMSLinePaymentService {
	
	/**
     * 查询批次表
     * @return
     */
    public List<LinePaymentOfferBatch> findHaTwoOfferBatch(Map<String,Object> map);
    
    /**
     * 生成报盘信息
     */
    public void createOffer(Map<String, Object> map);
    
    /**
     * 生成报盘批次信息
     * @param map
     */
    public void createOfferBatch(Map<String, Object> map);
    
    /**
     * 生成报盘流水信息
     * @param fundsSource
     * @param offerList
     * @param offerBatchId
     * @param dayBatchNumber
     */
    public void createOfferTransaction(String fundsSource, List<LinePaymentOffer> offerList,long offerBatchId,String dayBatchNumber);
    
    /**
     * 报盘导出
     * @param requestVo
     * @return
     */
    public Response<Map<String,Object>>queryExpOffer(ReqUnderLinePaymentVo requestVo);
    
    /**
     * 导入回盘
     * @param dataList
     */
    public void updateHaTwoOffer(ReqUnderLinePaymentVo requestVo);
    

}
