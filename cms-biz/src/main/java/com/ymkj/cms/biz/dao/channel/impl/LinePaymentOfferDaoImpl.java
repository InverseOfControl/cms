package com.ymkj.cms.biz.dao.channel.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.channel.ILinePaymentOffeDao;
import com.ymkj.cms.biz.entity.channel.LinePaymentOffer;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:报盘操作dao 实现
 */
@Repository
public class LinePaymentOfferDaoImpl extends BaseDaoImpl<LinePaymentOffer> implements ILinePaymentOffeDao {

	@Override
	public LinePaymentOffer findById(long id) {
		return getSessionTemplate().selectOne("selectById", id);
	}

	@Override
	public List<LinePaymentOffer> findNoOfferOrFail(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList("findNoOfferOrFail", paramMap);
	}

	@Override
	public BMSLoanBaseEntity findLoanById(Map<String, Object> paraMap) {
		return null;
	}


}
