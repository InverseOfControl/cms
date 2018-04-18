package com.ymkj.cms.biz.dao.apply;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.ReturnedLoanBoxSearchEntity;

public interface IReturnedLoanBoxDao extends BaseDao<ReturnedLoanBoxSearchEntity>{
	
	public int getCount(Map<String,Object> paramMap); 
	
}
