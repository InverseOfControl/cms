package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSReason;

public interface IBMSReasonManagementDao extends BaseDao<BMSReason>{
	/**
	 * 查询原因信息
	 * @param code
	 * @return
	 */
	BMSReason findBMSReasonByCode(BMSReason bmsReason);

}
