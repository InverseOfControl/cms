package com.ymkj.cms.biz.service.master;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;

public interface IBMSTmParameterService extends BaseService<BMSTmParameter>{
	
	public long insert(BMSTmParameter tmParameter);

	public void update(BMSTmParameter tmParameter);
	
	public List<BMSTmParameter> findByCode(String code);
	
	public List<BMSTmParameter> queryByCode(String code);
}
