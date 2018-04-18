package com.ymkj.cms.biz.dao.audit.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.audit.ReqLoanNumberVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResQualityInspectionSheetResultVO;
import com.ymkj.cms.biz.dao.audit.BMSQualityInspectionSheetDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
@Repository
public class BMSQualityInspectionSheetDaoImpl extends BaseDaoImpl<BMSLoanBase> implements BMSQualityInspectionSheetDao{

	@Override
	public List<ResQualityInspectionSheetResultVO> getPass(Map<String,Object> map) {
		return getSqlSession().selectList(getStatement("getPass"),map);
	}

	@Override
	public List<ResQualityInspectionSheetResultVO> getReject(Map<String,Object> map) {
		return getSqlSession().selectList(getStatement("getReject"),map);
	}

	@Override
	public ResQualityInspectionSheetResultVO findByLoanNo(ReqLoanNumberVO vo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNo", vo.getLoanNo());
		return getSqlSession().selectOne(getStatement("findByLoanNo"),map);
	}

	@Override
	public List<ResQualityInspectionSheetResultVO> getHandPass(Map<String, Object> map) {
		return getSqlSession().selectList(getStatement("getHandPass"),map);
	}

	@Override
	public List<ResQualityInspectionSheetResultVO> getHandReject(Map<String, Object> map) {
		return getSqlSession().selectList(getStatement("getHandReject"),map);
	}

	@Override
	public List<ResQualityInspectionSheetResultVO> findByLoanNoAndNameAndIdNos(List<ReqLoanNumberVO> vos) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("vos",vos);
		return getSqlSession().selectList(getStatement("findByLoanNoAndNameAndIdNos"),map);
	}

	@Override
	public List<String> findCodeByDates(Map<String, Object> map) {
		return getSqlSession().selectList(getStatement("findCodeByDates"),map);
	}

	@Override
	public Integer getCountZj(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatement("getCountZj"),map);
	}
}
