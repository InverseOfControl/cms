package com.ymkj.cms.biz.dao.audit;

import java.util.Map;

import com.ymkj.cms.biz.entity.audit.BMSAduitPersonEntity;

public interface IAduitPersonDao {
	
	/**
	 * 审批环节，查询人员信息
	 * @param map
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月12日下午4:16:50
	 */
	BMSAduitPersonEntity findAduitPersonInfo(Map<String, Object> map);

}
