package com.ymkj.cms.biz.service.master;

import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSParameter;
/**
 *  
 * @author  
 *
 */
public interface IBMSParameterService extends BaseService<BMSParameter>{
	 
	 
	 
	public BMSParameter parameterByCondition(Map<String, Object> paramMap);
}
