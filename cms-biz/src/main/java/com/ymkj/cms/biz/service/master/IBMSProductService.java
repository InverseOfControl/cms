package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSProduct;

import java.util.List;
import java.util.Map;
/**
 * 产品 服务层
 * @author haowp
 *
 */
public interface IBMSProductService extends BaseService<BMSProduct>{
	/**
	 * 通过营业部ID获取产品
	 * @param paramMap
	 * @return
	 */
	public List<BMSProduct> listProByCondition(Map<String, Object> paramMap);
	/**
	 * 保存产品对象
	 * @param product
	 * @return
	 */
	public Long saveProduct(BMSProduct product);
	/**
	 * 修改产品对象
	 * @param product	
	 */
	public void updateProduct(BMSProduct product);
	/**
	 * 删除产品对象
	 * @param product
	 */
	public void deleteProduct(BMSProduct product);
	
	public PageBean<BMSProduct> listPage2(PageParam pageParam, Map<String, Object> paramMap);
	
	
	

	/**
	 * 根据渠道ID获取产品数据 有渠道ID
	 * @param paramMap 
	 * @return
	 */
	public List<BMSProduct> findProByChannelId(Map<String, Object> paramMap);
	/**
	 * 根据渠道ID获取产品数据 没有渠道ID
	 * @param paramMap
	 * @return
	 */
	public List<BMSProduct> findProByChannelIdNotChannel(Map<String, Object> paramMap);
	
	/**
	 * 根据vo的讯息查询实体信息
	 * @param paramMap
	 * @return
	 */
	BMSProduct getByVo(Map<String, Object> paramMap);
	/**
	 * 根据产品ID查询数据
	 * @param productId
	 * @return
	 */
	public Integer findProductById(Long productId);

	/**
	 * 根据产品code查询数据
	 * @param productCd
	 * @return
	 */
	public BMSProduct findProductByCode(String productCd);
	
	/**
	 * 根据渠道ID获取优惠产品数据 有渠道ID
	 * @param paramMap 
	 * @return
	 */
	public List<BMSProduct> findProRateByChannelId(Map<String, Object> paramMap);
}
