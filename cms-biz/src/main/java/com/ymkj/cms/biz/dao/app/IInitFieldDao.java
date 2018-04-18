package com.ymkj.cms.biz.dao.app;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.app.BMSExtendsionAdapaterEntity;
import com.ymkj.cms.biz.entity.app.BMSExtendsionFieldEntity;

public interface IInitFieldDao extends BaseDao<BMSExtendsionFieldEntity>{

	//获取字段的最新更新时间
	public List<Map<String,Object>> getUpdateFieldsTime();

	//获取字段所属组列表信息
	public List<Map<String,Object>> getFieldGroup();

	//根据groupCode获取组下的字段信息
	public List<BMSExtendsionFieldEntity> getFieldByGroupCode(String groupCode);

	//获取隐藏组列表信息
	public List<Map<String,Object>> getHideLabelList(String groupCode);

	//获取每个隐藏组详情列表信息
	public List<BMSExtendsionFieldEntity> getHideLabelDetailList(Map<String,Object> paramMap);

	//获取下拉框数据列表
	public List<Map<String,Object>> getAdapaterList(String codeType);

	//获取省市区
	public abstract List<Map<String, Object>> getProvincesList(String paramString);

	//获取工作类型、单位性质、职业关联关系
	public abstract List<Map<String, Object>> getWorkTypeRelation(Map<String, Object> paramMap);
}
