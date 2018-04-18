package com.ymkj.cms.biz.dao.apply;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;

public interface IBMSChannelBankDao extends BaseDao<BMSChannelBank> {

	public Integer checkParentIsStart(Map<String,Object> map);
	public Integer checkParentIsChanel(Map<String,Object> map);
}
