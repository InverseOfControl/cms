package com.ymkj.cms.biz.dao.finance.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanConfirmationQuery;


@Repository
public class BMSLoanBaseDaoImpl extends BaseDaoImpl<BMSLoanBase> implements IBMSLoanBaseDao{

	@Override
	public PageBean<BMSLoanBase> doneListPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBase> list = getSqlSession().selectList(getStatement("doneListPage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("doneCountByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("doneCountByPageParam"), paramMap);
			return new PageBean<BMSLoanBase>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBase>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}

	@Override
	public BMSLoanConfirmationQuery auditCommit(Map<String, Object> map) {
		return super.getSqlSession().selectOne(super.getStatement("auditCommit"), map);
	}

	@Override
	public Integer findLoanIdbyFeeInfo(Integer id) {
		
		return super.getSqlSession().selectOne(super.getStatement("findLoanIdbyFeeInfo"), id);
	}
}
