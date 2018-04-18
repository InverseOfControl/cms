package com.ymkj.cms.biz.dao.master.impl;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSProductDao;
import com.ymkj.cms.biz.entity.master.BMSProduct;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BMSProductDaoImpl extends BaseDaoImpl<BMSProduct> implements IBMSProductDao{

	@Override
	public List<BMSProduct> listProByCondition(Map<String, Object> paramMap) {
		
		return super.getSqlSession().selectList(super.getStatement("listProByCondition"), paramMap);
	}

	@Override
	public List<BMSProduct> findProByChannelId(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("findProByChannelId"), paramMap);
	}

	@Override
	public List<BMSProduct> findProByChannelIdNotChannel(
			Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("findProByChannelIdNotChannel"), paramMap);
	}

	@Override
	public BMSProduct getByVo(Map<String, Object> paramMap) {
		return super.getSqlSession().selectOne(super.getStatement("getByVo"), paramMap);
	}

	@Override
	public Integer findProductById(Long productId) {
		
		return super.getSqlSession().selectOne(super.getStatement("findProductById"), productId);
	}

	@Override
	public BMSProduct findProducByCode(Map<String, Object> paramMap) {
		return super.getSqlSession().selectOne(super.getStatement("findProducByCode"), paramMap);
	}
	@Override
	public List<BMSProduct> findProRateByChannelId(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("findProRateByChannelId"), paramMap);
	}


}
