package com.ymkj.cms.biz.service.master.impl;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSProductDao;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.service.master.IBMSProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BMSProductServiceImpl extends BaseServiceImpl<BMSProduct> implements IBMSProductService{
	
	@Autowired
	private IBMSProductDao productDao;

	@Override
	public Long saveProduct(BMSProduct product) {
		return productDao.insert(product);
	}

	@Override
	public void updateProduct(BMSProduct product) {
		productDao.update(product);
		
	}

	@Override
	public void deleteProduct(BMSProduct product) {
		productDao.deleteById(product.getProductId());
		
	}

	@Override
	protected BaseDao<BMSProduct> getDao() {
		return productDao;
	}

	@Override
	public List<BMSProduct> listProByCondition(Map<String, Object> paramMap) {
		if("".equals(paramMap.get("paramMap"))){
			paramMap.put("orgIdList", null);
		}
		return productDao.listProByCondition(paramMap);
	}

	@Override
	public PageBean<BMSProduct> listPage2(PageParam pageParam, Map<String, Object> paramMap){
		return productDao.listPage(pageParam, paramMap);
	}


	@Override
	public List<BMSProduct> findProByChannelId(Map<String, Object> paramMap) {
		return productDao.findProByChannelId(paramMap);
	}

	@Override
	public List<BMSProduct> findProByChannelIdNotChannel(
			Map<String, Object> paramMap) {
		return productDao.findProByChannelIdNotChannel(paramMap);
	}

	@Override
	public BMSProduct getByVo(Map<String, Object> paramMap) {
		return productDao.getByVo(paramMap);
	}

	@Override
	public Integer findProductById(Long productId) {
	
		return productDao.findProductById(productId);
	}

	@Override
	public BMSProduct findProductByCode(String productCd) {
		Map<String,Object> param=new HashMap<>();
		param.put("code",productCd);
		return productDao.findProducByCode(param);
	}
	@Override
	public List<BMSProduct> findProRateByChannelId(Map<String, Object> paramMap) {
		return productDao.findProRateByChannelId(paramMap);
	}

}
