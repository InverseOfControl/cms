package com.ymkj.cms.biz.dao.apply;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.LoanExtEntity;
import com.ymkj.cms.biz.entity.apply.checkNewDataEntity;

import java.util.List;
import java.util.Map;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface LoanExtDao extends BaseDao<LoanExtEntity>{
	
	public List<checkNewDataEntity> queryCheckNewData(Map<String,Object> map);
}
