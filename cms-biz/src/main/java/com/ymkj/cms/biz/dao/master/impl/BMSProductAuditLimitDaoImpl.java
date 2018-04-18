package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.dao.master.IBMSProductAuditLimitDao;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;

@Repository
public class BMSProductAuditLimitDaoImpl extends BaseDaoImpl<BMSProductAuditLimit> implements IBMSProductAuditLimitDao{

	@Override
	public BMSProductAuditLimit findByAuditLimitId(long auditLimitId) {
		return getSessionTemplate().selectOne(getStatement("findByAuditLimitId"), auditLimitId);
	}

	@Override
	public BMSProductAuditLimit findByVO(ReqBMSProductAuditLimitVO productAuditLimitVO) {
		return getSessionTemplate().selectOne(getStatement("findByVO"), productAuditLimitVO);
	}

	@Override
	public List<BMSProductAuditLimit> findLimitByChaIdUserCode(
			Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(getStatement("findLimitByChaIdUserCode"), paramMap);
	}
	@Override
	public List<BMSProductAuditLimit> findLimitByChaIdOrgId(
			Map<String, Object> paramMap) {
		return getSessionTemplate().selectList(getStatement("findLimitByChaIdOrgId"), paramMap);
	}

	@Override
	public Integer updateByProductId(
			ReqBMSProductAuditLimitVO productAuditLimitVO) {
		return getSessionTemplate().update(getStatement("updateByProductId"), productAuditLimitVO);
	}

	@Override
	public List<BMSOrgLimitChannel> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req) {
		return getSessionTemplate().selectList(getStatement("findOutletByAuditLimitId"), req);
	}

	@Override
	public Boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req) {
		getSessionTemplate().selectList(getStatement("updateOrgLimitByAuditId"), req);
		return true;
	}

	@Override
	public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo) {
		getSessionTemplate().selectList(getStatement("updateOrgLimitById"), channelVo);
		return true;
	}


}
