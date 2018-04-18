package com.ymkj.cms.biz.dao.channel.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.channel.IBMSAppBookDao;
import com.ymkj.cms.biz.entity.channel.BMSApplyBookInfo;

@Repository
public class BMSAppBookDaoImpl extends BaseDaoImpl<BMSApplyBookInfo> implements IBMSAppBookDao {

	@Override
	public int queryCurrentDayCount(Map<String, Object> paraMap) {
		paraMap.put("createTime", new Date());
		return getSessionTemplate().selectOne("queryCurrentDayCount", paraMap);
	}

	@Override
	public List<String> findBatchNumByFundsSources(Map<String, Object> paraMap) {
		return getSessionTemplate().selectList("findBatchNumByFundsSources", paraMap);
	}

	@Override
	public BMSApplyBookInfo queryApplyBookInfoBybatchNum(Map<String, Object> paraMap) {
		List<BMSApplyBookInfo> applyBookInfoList = getSessionTemplate().selectList("queryApplyBookInfos", paraMap);
		if (applyBookInfoList == null || applyBookInfoList.size() <= 0) {
			return null;
		}
		return applyBookInfoList.get(0);
	}

	@Override
	public List<BMSApplyBookInfo> queryDayApplyBookInfos(Map<String, Object> paraMap) {
		 return getSessionTemplate().selectList("queryApplyBookInfos",paraMap);
	}

	@Override
	public BigDecimal queryAlreadyGrantMoney(List<String> batchNums) {
		 if (batchNums==null||batchNums.size()<=0) {
	            return new BigDecimal("0.00");
	        }
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("batchNums", batchNums);
	        return getSessionTemplate().selectOne("queryAlreadyGrantMoney", param);
	    }

}
