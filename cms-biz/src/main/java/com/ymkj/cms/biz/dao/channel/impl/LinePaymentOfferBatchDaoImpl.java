package com.ymkj.cms.biz.dao.channel.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.channel.ILinePaymentOfferBatchDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.channel.LinePaymentOfferBatch;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作dao 实现
 */
@Repository
public class LinePaymentOfferBatchDaoImpl extends BaseDaoImpl<LinePaymentOfferBatch> implements ILinePaymentOfferBatchDao {

	@Override
	public List<LinePaymentOfferBatch> findHaTwoOfferBatchNotExport(Map<String,Object> map) {
		return getSessionTemplate().selectList("findHaTwoOfferBatchNotExport",map);
	}

	@Override
	public List<LoanBaseEntity> getAllLoanBaseInfo(Map<String, Object> map) {
		return getSessionTemplate().selectList("getAllLoanBaseInfo", map);
	}

	@Override
	public List<LinePaymentOffer> findListByVo(Map<String, String> map) {
		return getSessionTemplate().selectList("findHaTwoOfferBatchNotExport", map);
	}

	@Override
	public Map<String, String> findLoanRelInfo(Map<String, String> map) {
		List<Map<String, String>> datas = getSessionTemplate().selectList("findLoanRelInfo", map);
		if (datas != null && datas.size() > 0) {
			return datas.get(0);
		} else {
			return null;
		}
	}

}
