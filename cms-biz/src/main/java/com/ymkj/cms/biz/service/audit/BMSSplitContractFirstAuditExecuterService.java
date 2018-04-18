package com.ymkj.cms.biz.service.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;
/**
 * 审核service层
 * @author YM10161
 *
 */
public interface BMSSplitContractFirstAuditExecuterService extends BaseService<BMSFirstAuditEntity>{
	/**
	 * 初审查询自动派单
	 * @return
	 */
	List<BMSAutomaticDispatchEntity> automaticDispatchList(Map<String,Object> map);
}
