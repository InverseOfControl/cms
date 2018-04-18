package com.ymkj.cms.biz.service.apply;

import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;

public interface IBMSChannelBankService extends BaseService<BMSChannelBank> {
	public boolean insert(BMSChannelBank channelBank);

	public boolean update(BMSChannelBank channelBank);
	
	public boolean checkIsExits(Map<String,Object> map);
	
	public Integer checkParentIsStart(Map<String,Object> map);
	
	public Integer checkParentIsChanel(Map<String,Object> map);
}
