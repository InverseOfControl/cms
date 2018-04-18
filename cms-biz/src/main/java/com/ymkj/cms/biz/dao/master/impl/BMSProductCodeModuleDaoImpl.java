package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSProductCodeModuleDao;
import com.ymkj.cms.biz.entity.master.BMSProductCodeModule;
@Repository
public class BMSProductCodeModuleDaoImpl extends BaseDaoImpl<BMSProductCodeModule> implements IBMSProductCodeModuleDao{

	@Override
	public List<BMSProductCodeModule> findModuleCodeByProId(Map<String, Object> paramMap) {
		
		return super.getSqlSession().selectList(super.getStatement("findModuleCodeByProId"), paramMap);
	}

/*	@Override
	public int batchUpdate(List bmsProductCodeModules) {
		return super.getSqlSession().update(super.getStatement("batchUpdate"), bmsProductCodeModules);
	}
*/
	@Override
	public int batchUpdate(Map<String, Object> paramMap) {
		return super.getSqlSession().update(super.getStatement("batchUpdate"), paramMap);
	}


}
