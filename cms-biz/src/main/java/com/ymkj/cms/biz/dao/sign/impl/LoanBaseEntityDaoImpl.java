package com.ymkj.cms.biz.dao.sign.impl;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.sign.ILoanBaseEntityDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanApp;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoanBaseEntityDaoImpl extends BaseDaoImpl<BMSLoanBaseEntity> implements ILoanBaseEntityDao{
	

	@Override
	public PageBean<BMSLoanBaseEntity> donelistPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBaseEntity> list = getSqlSession().selectList(getStatement("listDonePage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("countDoneByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("countDoneByPageParam"), paramMap);
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}

	@Override
	public PageBean<BMSLoanBaseEntity> undoneContractConfirmListPage(PageParam pageParam,Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBaseEntity> list = getSqlSession().selectList(getStatement("listConfirmNoDonePage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("countConfirmNoDoneByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("countConfirmNoDoneByPageParam"), paramMap);
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}
	

	@Override
	public PageBean<BMSLoanBaseEntity> doneContractConfirmListPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBaseEntity> list = getSqlSession().selectList(getStatement("listConfirmDonePage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("countConfirmDoneByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("countConfirmDoneByPageParam"), paramMap);
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public BMSLoanBaseEntity findSignInfo(Map paramMap){	
		return super.getSqlSession().selectOne(super.getStatement("findSignInfo"), paramMap);
	}

	
	@SuppressWarnings("rawtypes")
	public BMSLoanApp findLoanEntity(Map paramMap){	
		return super.getSqlSession().selectOne(super.getStatement("findLoanEntity"), paramMap);
	}

	@Override
	public BMSLoanBaseEntity findByloanBaseId(Map<String, Object> paramMap) {
		return super.getSqlSession().selectOne(super.getStatement("findByloanBaseId"), paramMap);
	}
	
	@Override
	public List<Map<String, Object>> getManagerCodeListByOrgId(String orgId) {
		
		return  super.getSqlSession().selectList("getManagerCodeListByOrgId", orgId);
	}

	@Override
	public BMSLoanBaseEntity getLoanInfoByLoanNo(Map<String, Object> paramMap) {
		return super.getSqlSession().selectOne(super.getStatement("getLoanInfoByLoanNo"), paramMap);
	}
	
	@Override
	public long queryContractSignToDoCount(Map<String, Object> paramsMap) {
		return super.getSqlSession().selectOne(super.getStatement("countByPageParam"), paramsMap);
	}
	
	@Override
	public long queryContractConfirmToDoCount(Map<String, Object> paramsMap) {
		return getSqlSession().selectOne(getStatement("countConfirmNoDoneByPageParam"),paramsMap);
	}

	@Override
	public long queryContractPatchBoltToDoCount(Map<String, Object> paramsMap) {
		return super.getSqlSession().selectOne(super.getStatement("countPatchBoltByPageParam"), paramsMap);
	}
	
	
	@Override
	public PageBean<BMSLoanBaseEntity> undoneListPatchBoltPage(PageParam pageParam,Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBaseEntity> list = getSqlSession().selectList(getStatement("listPatchBoltPage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("countPatchBoltByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("countPatchBoltByPageParam"), paramMap);
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}

	@Override
	public PageBean<BMSLoanBaseEntity> doneListPatchBoltPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBaseEntity> list = getSqlSession().selectList(getStatement("doneListPatchBoltPage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("countPatchBoltDoneByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("countPatchBoltDoneByPageParam"), paramMap);
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}

	@Override
	public PageBean<BMSLoanBaseEntity> unclaimedContractSignListBy(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null)
		paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<BMSLoanBaseEntity> list = getSqlSession().selectList(getStatement("listSignUnclaimedPage"), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		Object countObject = (Object) getSqlSession().selectOne(getStatement("countSignUnclaimedByPageParam"),
				paramMap);
		Long count = Long.valueOf(countObject.toString());
		// 是否统计当前分页条件下的数据：1:是，其他为否
		Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = super.getSessionTemplate().selectOne(getStatement("countSignUnclaimedByPageParam"), paramMap);
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list, countResultMap);
		} else {
			return new PageBean<BMSLoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		}
	}
}
