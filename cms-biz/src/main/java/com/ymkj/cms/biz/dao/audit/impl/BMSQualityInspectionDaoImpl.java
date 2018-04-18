package com.ymkj.cms.biz.dao.audit.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.audit.BMSQualityInspectionDao;
import com.ymkj.cms.biz.entity.audit.BMSApplicationPartEntity;
import com.ymkj.cms.biz.entity.audit.BMSQualityInspectionEntity;
@Repository
public class BMSQualityInspectionDaoImpl extends BaseDaoImpl<BMSApplicationPartEntity> implements BMSQualityInspectionDao{

	@Override
	public List<BMSQualityInspectionEntity> queryQualityInsInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getStatement("queryQualityInsInfo"), map);
	}

	@Override
	public Integer checkLoanIsNowReject(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatement("checkLoanIsNowReject"),map);
	}
	
	
	public PageBean<BMSApplicationPartEntity> listPageEntrySearch(PageParam pageParam,
			Map<String, Object> paramMap) {
			if (paramMap == null)
				paramMap = new HashMap<String, Object>();

			// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
			List<BMSApplicationPartEntity> list = getSqlSession().selectList(getStatement("listPage"), paramMap,
					new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
			// 统计总记录数
			Object countObject = (Object) getSqlSession().selectOne(getStatement("countByPageParam"), paramMap);
			Long count = Long.valueOf(countObject.toString());

			return new PageBean<BMSApplicationPartEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		 
	 
	}

}
