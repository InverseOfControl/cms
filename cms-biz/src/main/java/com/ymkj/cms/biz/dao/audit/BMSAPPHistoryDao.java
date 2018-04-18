package com.ymkj.cms.biz.dao.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface BMSAPPHistoryDao extends BaseDao<BMSAPPHistoryEntity>{
	
	/**
	 * 申请件历史查找
	 * @param params
	 * @author lix YM10160
	 * @return 
	 * @date 2017年3月17日下午6:12:42
	 */
	List<BMSAPPHistoryEntity> findTmAppHistoryByParams(Map<String, Object> params);
	
}
