package com.ymkj.cms.biz.dao.master;

import java.util.List;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;

public interface IBMSTmParameterDao extends BaseDao<BMSTmParameter> {
	
	public List<BMSTmParameter> findByCode(String code);
	
	public List<BMSTmParameter> queryByCode(String code);

}
