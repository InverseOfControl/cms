package com.ymkj.cms.biz.dao.apply.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.common.mybatis.ExecutorInterceptor;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.common.util.ApplyEnterEncapsulate;
import com.ymkj.cms.biz.dao.apply.LoanBaseDao;
import com.ymkj.cms.biz.entity.apply.EntrySearchEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.BMSContractSignSearchEntity;
import com.ymkj.cms.biz.entity.audit.InformationEntity;

@Repository
public class LoanBaseDaoImpl extends BaseDaoImpl<LoanBaseEntity> implements LoanBaseDao{
	private static final Logger logger = LoggerFactory.getLogger(LoanBaseDaoImpl.class);

	@Override
	public PageBean<EntrySearchEntity> listPageEntrySearch(PageParam pageParam,
			Map<String, Object> paramMap, String sqlId) {
			if (paramMap == null)
				paramMap = new HashMap<String, Object>();

			// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
			List<EntrySearchEntity> list = getSqlSession().selectList(getStatement(sqlId), paramMap,
					new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
			// 统计总记录数
			Object countObject = (Object) getSqlSession().selectOne(getStatement("listPageEntrySearchCountByPageParam"), paramMap);
			Long count = Long.valueOf(countObject.toString());

			return new PageBean<EntrySearchEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
		 
	 
	}

	@Override
	public PageBean<BMSContractSignSearchEntity> listPageContractSignSearch(
			PageParam pageParam, Map<String, Object> paramMap, String sqlId) {
	 
			if (paramMap == null)
				paramMap = new HashMap<String, Object>();

			// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
			List<BMSContractSignSearchEntity> list = getSqlSession().selectList(getStatement(sqlId), paramMap,
					new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));

			// 统计总记录数
			Object countObject = (Object) getSqlSession().selectOne(getStatement("listPageContractSignSearchCountByPageParam"), new ExecutorInterceptor.CountParameter(paramMap));
			Long count = Long.valueOf(countObject.toString());

			return new PageBean<BMSContractSignSearchEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
	}

	@Override
	public List<LoanBaseEntity> getByMap(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList("getByMap", paramMap);
	}

	@Override
	public PageBean<LoanBaseEntity> getReassignment(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<LoanBaseEntity> list = getSqlSession().selectList(getStatement(sqlId), paramMap,
				new RowBounds((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize()));
		// 统计总记录数
		Object countObject = (Object) getSqlSession().selectOne(getStatement("getReassignmentCount"), paramMap);
		Long count = Long.valueOf(countObject.toString());

		return new PageBean<LoanBaseEntity>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);

	}

	@Override
	public InformationEntity queryInformationEntity(Map<String, Object> paramMap) {
		return getSqlSession().selectOne("getInformationVO", paramMap);
	}

	@Override
	public boolean updateOperatingStaff(Map<String, Object> paramMap) {
		int i = this.getSqlSession().update("updateOperatingStaff", paramMap);
		return i == 0?false:true;
	}

	@Override
	public long updateVersion(Map<String, Object> paramMap) {
		int i = this.getSqlSession().update("updateVersion", paramMap);
		return i;
	}
	
	@Override
	public long updateLoanBaseVersionById(Map<String, Object> paramMap) {
		int i = this.getSqlSession().update("updateLoanBaseVersionById", paramMap);
		return i;
	}

	@Override
	public Long queryWaitToDealCount(Map<String, Object> paramMap) {
		// 统计总记录数
		Object countObject = (Object) getSqlSession().selectOne(getStatement("listPageEntrySearchCountByPageParam"), paramMap);
		Long count = Long.valueOf(countObject.toString());
		return count;
	}

	@Override
	public List<LoanBaseEntity> findLoanNoByNotBelongTo(PersonHistoryLoanVO personHistoryLoanVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNo",personHistoryLoanVO.getLoanNo());
		//map.put("name", personHistoryLoanVO.getName());
		map.put("idNo", personHistoryLoanVO.getIdNo());
		return getSqlSession().selectList("findLoanNoByNotBelongTo", map);
	}

	@Override
	public Map<String, Object> findRadioByApplyType(String applyType) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("applyType", applyType);
		return getSqlSession().selectOne("findRadioByApplyType", map);
	}

	@Override
	public String createLoanNo(Date date) {
		logger.info("获取借款编号！");
		
		if(date == null) return null;
		
		int i = 1;
		
		Map<String,Object> map=new HashMap<String,Object>();
		while(true){
			String appNo = ApplyEnterEncapsulate.createAppNo(date);
			logger.info("随机生成借款编号为："+appNo);
			map.put("loanNo", appNo);
			LoanBaseEntity selectOne = getSqlSession().selectOne("queryLoanExist", map);
			logger.info("验证数据库是否存在改借款编号:"+(selectOne!=null));
			if(selectOne == null){
				return appNo;
			}
			
			//控制死循环
			if(i > 50){
				return null;
			}
			i++;
		}
	}

	@Override
	public long updateIfNewLoanNo(Map<String, Object> paramMap) {
		int i = this.getSqlSession().update("updateIfNewLoanNo", paramMap);
		return i;
	}

	@Override
	public String getOrgPrevReviewCode(Map paramMap) {

		return getSqlSession().selectOne("getOrgPrevReviewCode", paramMap);
	}

	@Override
	public List<Map<String,Object>> findDataByIdNoRefuse(String idNo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("idNo", idNo);
		return getSqlSession().selectList("findDataByIdNoRefuse", map);
	}

	@Override
	public List<Map<String, Object>> findDataByIdNoCancel(String idNo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("idNo", idNo);
		return getSqlSession().selectList("findDataByIdNoCancel", map);
	}

	@Override
	public Map<String, Object> queryContractInfo(String loanNo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNo",loanNo);
		return getSqlSession().selectOne("queryContractInfo", map);
	}
}
