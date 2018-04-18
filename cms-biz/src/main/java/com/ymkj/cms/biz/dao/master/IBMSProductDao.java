package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSProduct;

import java.util.List;
import java.util.Map;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSProductDao extends BaseDao<BMSProduct>{
	/**
	 * 通过营业部ID获取产品数据
	 * @param paramMap
	 * @return
	 */
	List<BMSProduct> listProByCondition(Map<String, Object> paramMap);
	/**
	 * 根据渠道ID获取产品数据 有渠道ID
	 * @param paramMap
	 * @return
	 */
	List<BMSProduct> findProByChannelId(Map<String, Object> paramMap);
	/**
	 * 根据渠道ID获取产品数据 无渠道ID
	 * @param paramMap
	 * @return
	 */
	List<BMSProduct> findProByChannelIdNotChannel(Map<String, Object> paramMap);
	
	/**
	 * 根据vo类传进来的信息查询返回信息
	 * @param paramMap
	 * @return
	 */
	BMSProduct getByVo(Map<String, Object> paramMap) ;
	/**
	 * 根据产品ID查询数据
	 * @param productId
	 * @return
	 */
	Integer findProductById(Long productId);

	/**
	 * 根据产品code查询
	 * @param paramMap
	 * @return
	 */
	BMSProduct findProducByCode(Map<String, Object> paramMap) ;
	
	/**
	 * 根据渠道ID获取产品数据 有渠道ID
	 * @param paramMap
	 * @return
	 */
	List<BMSProduct> findProRateByChannelId(Map<String, Object> paramMap);
	
	
	
}
