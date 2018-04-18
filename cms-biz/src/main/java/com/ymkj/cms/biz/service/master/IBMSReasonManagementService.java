package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSReason;

public interface IBMSReasonManagementService extends BaseService<BMSReason>{

	public long insert(BMSReason bmsReason);
	public long update(BMSReason bmsReason);
	/**
	 * 查询原因信息
	 * @param code
	 * @return
	 */
	BMSReason findBMSReasonByCode(BMSReason bmsReason);
}
