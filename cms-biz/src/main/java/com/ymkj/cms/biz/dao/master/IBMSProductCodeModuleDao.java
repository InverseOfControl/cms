package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSProductCodeModule;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSProductCodeModuleDao extends BaseDao<BMSProductCodeModule>{
	
	
	/**
	 * 通过产品ID获取枚举信息
	 * @param paramMap
	 * @return
	 */
	List<BMSProductCodeModule> findModuleCodeByProId(Map<String, Object> paramMap);
	
/*	int batchUpdate(List bmsProductCodeModules);*/
	
	/**
	 * 批量更新
	 * @param paramMap
	 * @return
	 */
	
	int batchUpdate(Map<String, Object> paramMap);
}
