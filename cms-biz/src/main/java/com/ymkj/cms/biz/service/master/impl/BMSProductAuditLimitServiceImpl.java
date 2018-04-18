package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.dao.master.IBMSProductAuditLimitDao;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;
import com.ymkj.cms.biz.service.master.IBMSProductAuditLimitService;

@Service
public class BMSProductAuditLimitServiceImpl extends BaseServiceImpl<BMSProductAuditLimit> implements IBMSProductAuditLimitService{
	
	@Autowired
	private IBMSProductAuditLimitDao productLimitDao;


	@Override
	public Long saveProductLimit(BMSProductAuditLimit productLimit) {
		return productLimitDao.insert(productLimit);
	}

	@Override
	public void updateProductLimit(BMSProductAuditLimit productLimit) {
		productLimitDao.update(productLimit);
		
	}

	@Override
	public void deleteProductLimit(BMSProductAuditLimit productLimit) {
		
		productLimitDao.deleteById(productLimit.getAuditLimitId());
	}

	@Override
	protected BaseDao<BMSProductAuditLimit> getDao() {
		return productLimitDao;
	}

	@Override
	public void updateProductLimit(ReqBMSProductAuditLimitVO reqLimit) {
		BMSProductAuditLimit productLimit = new BMSProductAuditLimit();
		if(reqLimit !=null && reqLimit.getAuditLimitId() !=null){
			productLimit.setAuditLimitId(reqLimit.getAuditLimitId());
			productLimit.setAuditLimit(reqLimit.getAuditLimit());
			productLimit.setFloorLimit(reqLimit.getFloorLimit());
			productLimit.setIsDisabled(reqLimit.getIsDisabled());
			productLimit.setIsDeleted(reqLimit.getIsDeleted());
			productLimit.setProductCode(reqLimit.getProductCode());
			productLimit.setProductId(reqLimit.getProductId());
			productLimit.setUpperLimit(reqLimit.getUpperLimit());
			productLimit.setVersion(reqLimit.getVersion());
			productLimit.setConfigureConflict("N");
			productLimitDao.update(productLimit);
		}
		
	}

	@Override
	public BMSProductAuditLimit findByAuditLimitId(long auditLimitId) {
		return productLimitDao.findByAuditLimitId(auditLimitId);
	}

	@Override
	public BMSProductAuditLimit findByVO(ReqBMSProductAuditLimitVO productAuditLimitVO) {
		return productLimitDao.findByVO(productAuditLimitVO);
	}

	@Override
	public List<BMSProductAuditLimit> findLimitByChaIdUserCode(
			Map<String, Object> paramMap) {
		return productLimitDao.findLimitByChaIdUserCode(paramMap);
	}

	@Override
	public List<BMSProductAuditLimit> findLimitByChaIdOrgId(Map<String, Object> paramMap) {
		
		return productLimitDao.findLimitByChaIdOrgId(paramMap);
	}

	@Override
	public void updateByProductId(ReqBMSProductAuditLimitVO reqLimit) {
//		BMSProductAuditLimit productLimit = new BMSProductAuditLimit();
//		if(reqLimit !=null && reqLimit.getProductId() !=null){
//			productLimit.setAuditLimitId(reqLimit.getAuditLimitId());
//			productLimit.setAuditLimit(reqLimit.getAuditLimit());
//			productLimit.setFloorLimit(reqLimit.getFloorLimit());
//			productLimit.setIsDisabled(reqLimit.getIsDisabled());
//			productLimit.setIsDeleted(reqLimit.getIsDeleted());
//			productLimit.setProductCode(reqLimit.getProductCode());
//			productLimit.setProductId(reqLimit.getProductId());
//			productLimit.setUpperLimit(reqLimit.getUpperLimit());
//			productLimit.setVersion(reqLimit.getVersion());
//			productLimitDao.update(productLimit);
//		}
		productLimitDao.updateByProductId(reqLimit);
	}
	@Override
	public long deleteProductTerm(ReqBMSProductAuditLimitVO reqLimit) {
		BMSProductAuditLimit productLimit = new BMSProductAuditLimit();
		if(reqLimit.getAuditLimitId()!=null){
			productLimit.setAuditLimitId(reqLimit.getAuditLimitId());
			productLimit.setIsDeleted((long)1);
		}
		long reuslt=productLimitDao.update(productLimit);
		return reuslt;
	}

	@Override
	public List<BMSOrgLimitChannel> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req) {
		List<BMSOrgLimitChannel> list=productLimitDao.findOutletByAuditLimitId(req);
		return list;
	}

	@Override
	public Boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req) {
		return productLimitDao.updateOrgLimitByAuditId(req);
	}

	@Override
	public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo) {
		return productLimitDao.updateOrgLimitById(channelVo);
	}

	

}
