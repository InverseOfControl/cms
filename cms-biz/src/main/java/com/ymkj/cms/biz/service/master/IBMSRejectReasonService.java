package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSProduct;
import com.ymkj.cms.biz.entity.master.BMSRejectReason;
/**
 *  
 * @author  
 *
 */
public interface IBMSRejectReasonService extends BaseService<BMSRejectReason>{
	 
	public List<BMSRejectReason> listByCondition(Map<String, Object> paramMap);
	 
	public BMSRejectReason RejectReasonByCondition(Map<String, Object> paramMap);
}
