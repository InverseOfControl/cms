package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSIntegratedSearchDao;
import com.ymkj.cms.biz.entity.master.BMSIntegraedSearchEntity;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;

@Repository
public class BMSIntegratedSearchDaoImpl extends BaseDaoImpl<BMSIntegraedSearchEntity> implements IBMSIntegratedSearchDao {

	@Override
	public List<BMSSysLoanLog> queryLoanLog(Map<String,Object> map) {
		return getSessionTemplate().selectList("queryLoanLog", map);
	}

	@Override
	public Map<String, Object> queryLoanDetail(String loanNo) {
		return getSessionTemplate().selectOne("queryLoanDetail", loanNo);
	}

	@Override
	public PageBean<BMSIntegraedSearchEntity> mainSelet(Map<String, Object> map) {
		int pageNum = Integer.valueOf(ObjectUtils.toString(map.get("pageNum")));
		int pageSize = Integer.valueOf(ObjectUtils.toString(map.get("pageSize")));
		
		int limitStart = (pageNum - 1) * pageSize;
		
		map.put("limitStart", limitStart);
		
		long s = System.currentTimeMillis();
		List<BMSIntegraedSearchEntity> list = getSessionTemplate().selectList("mainSelet", map);
		
		List<String> totalCountList = getSessionTemplate().selectList("mainSeletCount", map);
		int totalCount = Integer.valueOf(totalCountList.get(0));
		System.out.println("-----------------------------------------数据库处理时间:"+(System.currentTimeMillis() - s));
		return new PageBean<BMSIntegraedSearchEntity>(pageNum, pageSize, totalCount, list);
	}
}
