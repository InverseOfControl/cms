package com.ymkj.cms.biz.dao.app.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.app.IInitFieldDao;
import com.ymkj.cms.biz.entity.app.BMSExtendsionFieldEntity;

@Repository
public class InitFieldDaoImpl extends BaseDaoImpl<BMSExtendsionFieldEntity> implements IInitFieldDao{

	@Override
	public List<Map<String, Object>> getUpdateFieldsTime() {
		return getSessionTemplate().selectList("getUpdateFieldsTime");
	}
	
	@Override
	public List<Map<String, Object>> getFieldGroup() {
		return getSessionTemplate().selectList("getFieldGroup");
	}
	
	@Override
	public List<BMSExtendsionFieldEntity> getFieldByGroupCode(String groupCode) {
		return getSessionTemplate().selectList("getFieldByGroupCode",groupCode);
	}

	@Override
	public List<Map<String, Object>> getHideLabelList(String groupCode) {
		return getSessionTemplate().selectList("getHideLabelList",groupCode);
	}
	
	@Override
	public List<BMSExtendsionFieldEntity> getHideLabelDetailList(Map<String, Object> paramMap) {
		return getSessionTemplate().selectList("getHideLabelDetailList",paramMap);
	}

	@Override
	public List<Map<String,Object>> getAdapaterList(String codeType) {
		return getSessionTemplate().selectList("getAdapaterList",codeType);
	}
	
	public List<Map<String, Object>> getProvincesList(String areaId) {
		return getSessionTemplate().selectList("getProvincesList", areaId);
	}

	public List<Map<String, Object>> getWorkTypeRelation(Map<String, Object> map) {
		return getSessionTemplate().selectList("getWorkTypeRelation", map);
	}

}
