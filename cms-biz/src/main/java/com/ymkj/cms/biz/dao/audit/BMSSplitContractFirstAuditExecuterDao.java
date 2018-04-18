package com.ymkj.cms.biz.dao.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
/**
 * 审核dao层
 * @author YM10161
 *
 */
public interface BMSSplitContractFirstAuditExecuterDao extends BaseDao<BMSFirstAuditEntity>{
	/**
	 * 初审查询自动派单
	 * @return
	 */
	List<BMSAutomaticDispatchEntity> automaticDispatchList(Map<String,Object> map);
}
